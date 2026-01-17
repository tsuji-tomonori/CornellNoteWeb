(function() {
const API_BASE = '/api';

const api = {
  async _fetch(path, options) {
    try {
      const response = await window.fetch(`${API_BASE}${path}`, options);
      if (!response.ok) throw new Error('API Error');
      if (response.status === 204) return null;
      return await response.json();
    } catch (error) {
      console.error(`API Error: ${path}`, error);
      return null;
    }
  },

  async getNotes() {
    const data = await this._fetch('/notes');
    if (data && data.items) {
      return { notes: data.items };
    }
    return data;
  },

  async getNote(id) {
    return this._fetch(`/notes/${id}`);
  },

  async getNotebooks() {
    const data = await this._fetch('/notebooks');
    if (Array.isArray(data)) {
      return { notebooks: data };
    }
    return data;
  },

  async getTags() {
    const data = await this._fetch('/tags');
    if (Array.isArray(data)) {
      return { tags: data };
    }
    return data;
  },

  async getSharedNote(token) {
    const data = await this._fetch(`/share/${token}`);
    if (data && data.noteId) {
      return { note: data };
    }
    return data;
  }
};

const storage = {
  setSession(session) {
    localStorage.setItem('cornell_session', JSON.stringify(session));
  },

  getSession() {
    const data = localStorage.getItem('cornell_session');
    return data ? JSON.parse(data) : null;
  },

  clearSession() {
    localStorage.removeItem('cornell_session');
  },

  isLoggedIn() {
    return this.getSession() !== null;
  }
};

const ui = {
  openModal(overlay, focusTarget) {
    if (!overlay) return;
    const activeElement = document.activeElement;
    overlay.dataset.lastFocusId = activeElement && activeElement.id ? activeElement.id : '';
    overlay.classList.remove('hidden');
    const modal = overlay.querySelector('.modal');
    const focusableSelectors = 'a[href], button:not([disabled]), textarea, input, select, [tabindex]:not([tabindex="-1"])';
    const focusable = Array.from(overlay.querySelectorAll(focusableSelectors));
    const first = focusTarget || focusable[0] || modal;
    const last = focusable[focusable.length - 1] || modal;

    const trap = (event) => {
      if (event.key === 'Escape') {
        event.preventDefault();
        ui.closeModal(overlay);
        return;
      }

      if (event.key !== 'Tab') return;
      if (focusable.length === 0) {
        event.preventDefault();
        modal?.focus();
        return;
      }

      if (event.shiftKey && document.activeElement === first) {
        event.preventDefault();
        last.focus();
      } else if (!event.shiftKey && document.activeElement === last) {
        event.preventDefault();
        first.focus();
      }
    };

    overlay.dataset.trap = 'true';
    overlay._trapHandler = trap;
    overlay.addEventListener('keydown', trap);
    setTimeout(() => {
      first?.focus();
    }, 0);
  },

  closeModal(overlay) {
    const modalOverlay = overlay || document.querySelector('.modal-overlay');
    if (!modalOverlay) return;
    modalOverlay.classList.add('hidden');
    if (modalOverlay._trapHandler) {
      modalOverlay.removeEventListener('keydown', modalOverlay._trapHandler);
      modalOverlay._trapHandler = null;
    }
    const lastFocusId = modalOverlay.dataset.lastFocusId;
    if (lastFocusId) {
      document.getElementById(lastFocusId)?.focus();
    } else {
      document.activeElement?.blur();
    }
  },

  showToast(message, type = 'info') {
    const toast = document.createElement('div');
    toast.className = `toast toast-${type}`;
    toast.textContent = message;
    toast.setAttribute('role', type === 'error' ? 'alert' : 'status');
    toast.setAttribute('aria-live', type === 'error' ? 'assertive' : 'polite');
    toast.style.cssText = `
      position: fixed;
      bottom: 20px;
      right: 20px;
      padding: 12px 20px;
      background: ${type === 'error' ? '#b91c1c' : type === 'success' ? '#15803d' : '#2563eb'};
      color: white;
      border-radius: 6px;
      z-index: 300;
    `;
    document.body.appendChild(toast);

    const regionId = type === 'error' ? 'alertRegion' : 'statusRegion';
    const region = document.getElementById(regionId);
    if (region) {
      region.textContent = message;
    }

    setTimeout(() => toast.remove(), 3000);
  },

  announceStatus(message, type = 'status') {
    const regionId = type === 'alert' ? 'alertRegion' : 'statusRegion';
    const region = document.getElementById(regionId);
    if (region) {
      region.textContent = message;
    }
  },

  formatDate(dateString) {
    const date = new Date(dateString);
    return date.toLocaleDateString('ja-JP', {
      year: 'numeric',
      month: 'short',
      day: 'numeric'
    });
  },

  formatDateTime(dateString) {
    const date = new Date(dateString);
    return date.toLocaleString('ja-JP', {
      year: 'numeric',
      month: 'short',
      day: 'numeric',
      hour: '2-digit',
      minute: '2-digit'
    });
  }
};

class AutoSave {
  constructor(onSave, debounceMs = 2000) {
    this.onSave = onSave;
    this.debounceMs = debounceMs;
    this.timer = null;
    this.status = 'saved';
  }

  trigger() {
    this.status = 'saving';
    this.updateStatusUI();

    if (this.timer) clearTimeout(this.timer);

    this.timer = setTimeout(async () => {
      try {
        await this.onSave();
        this.status = 'saved';
      } catch (error) {
        this.status = 'error';
        console.error('Auto-save failed:', error);
      }
      this.updateStatusUI();
    }, this.debounceMs);
  }

  updateStatusUI() {
    const statusEl = document.querySelector('.save-status');
    if (!statusEl) return;

    statusEl.className = `save-status ${this.status}`;
    const labels = {
      saved: '保存済み',
      saving: '保存中...',
      error: '保存エラー'
    };
    const statusText = labels[this.status] || labels.saved;
    statusEl.innerHTML = `<span>${statusText}</span>`;

    const regionId = this.status === 'error' ? 'alertRegion' : 'statusRegion';
    const region = document.getElementById(regionId);
    if (region) {
      region.textContent = statusText;
    }
  }
}

function initSearch() {
  const searchInput = document.querySelector('.header-search input');
  if (!searchInput) return;

  searchInput.addEventListener('input', debounce((e) => {
    const query = e.target.value.toLowerCase();
    filterNotes(query);
  }, 300));
}

function filterNotes(query) {
  const noteCards = document.querySelectorAll('.note-card');
  noteCards.forEach(card => {
    const title = card.querySelector('.note-card-title')?.textContent.toLowerCase() || '';
    const preview = card.querySelector('.note-card-preview')?.textContent.toLowerCase() || '';
    const visible = !query || title.includes(query) || preview.includes(query);
    card.style.display = visible ? '' : 'none';
  });
}

function debounce(func, wait) {
  let timeout;
  return function executedFunction(...args) {
    const later = () => {
      clearTimeout(timeout);
      func(...args);
    };
    clearTimeout(timeout);
    timeout = setTimeout(later, wait);
  };
}

function getUrlParam(name) {
  const params = new URLSearchParams(window.location.search);
  return params.get(name);
}

function navigateTo(page, params = {}) {
  const queryString = Object.keys(params).length
    ? '?' + new URLSearchParams(params).toString()
    : '';
  window.location.href = page + queryString;
}

function requireAuth() {
  if (!storage.isLoggedIn()) {
    navigateTo('/login');
    return false;
  }
  return true;
}

function logout() {
  storage.clearSession();
  navigateTo('/login');
}

window.CornellApp = {
  api,
  storage,
  ui,
  AutoSave,
  initSearch,
  getUrlParam,
  navigateTo,
  requireAuth,
  logout,
  debounce
};
})();
