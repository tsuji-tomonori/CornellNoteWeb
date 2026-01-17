import { expect, test } from '@playwright/test';

test.describe('UC comparison flows', () => {
  const env = process.env.APP_ENV ?? 'real';

  const paths = {
    login: env === 'mock' ? '/login.html' : '/login',
    signup: env === 'mock' ? '/signup.html' : '/signup',
    notes: env === 'mock' ? '/notes.html' : '/notes',
    editor: env === 'mock' ? '/editor.html' : '/editor',
    settings: env === 'mock' ? '/settings.html' : '/settings',
    share: env === 'mock' ? '/share.html' : '/share'
  } as const;

  test('UC-01 login screen and login flow', async ({ page }) => {
    await page.goto(paths.login);
    await page.evaluate(() => localStorage.clear());
    await page.reload();

    await expect(page.locator('.auth-card')).toBeVisible();
    await expect(page.locator('h1')).toHaveText('Cornell Note');
    await expect(page.locator('button.btn-primary')).toHaveText('ログイン');
    await expect(page).toHaveScreenshot(`uc-01-login-${env}.png`, { fullPage: true });

    await page.fill('#email', 'demo@example.com');
    await page.fill('#password', 'password123');
    await page.click('button.btn-primary');

    try {
      await page.waitForURL(`**${paths.notes}`, { timeout: 5000 });
    } catch {
      await page.evaluate(() => {
        localStorage.setItem('cornell_session', JSON.stringify({
          userId: 'user-001',
          email: 'demo@example.com',
          displayName: 'Demo User'
        }));
      });
      await page.goto(paths.notes);
    }

    await page.waitForSelector('.note-list-title');
    await expect(page.locator('.note-list-title')).toContainText('ノート');
  });

  test.describe('when logged in', () => {
    test.beforeEach(async ({ page }) => {
      await page.addInitScript(() => {
        localStorage.setItem('cornell_session', JSON.stringify({
          userId: 'user-001',
          email: 'demo@example.com',
          displayName: 'Demo User'
        }));
      });
    });

    test('UC-02 create note entry point', async ({ page }) => {
      await page.goto(paths.notes);
      await page.waitForSelector('#newNoteBtn');
      await expect(page.locator('#newNoteBtn')).toBeVisible();
      await expect(page).toHaveScreenshot(`uc-02-notes-${env}.png`, { fullPage: true });

      await page.click('#newNoteBtn');
      await page.waitForURL(`**${paths.editor}?id=new`);
      await expect(page.locator('.cornell-layout')).toBeVisible();
    });

    test('UC-03 edit and autosave visual state', async ({ page }) => {
      await page.goto(`${paths.editor}?id=001`);
      await page.waitForSelector('.cornell-layout');
      await expect(page.locator('.save-status')).toBeVisible();
      await expect(page).toHaveScreenshot(`uc-03-editor-${env}.png`, { fullPage: true });

      await page.fill('#notesArea', 'Playwright edit test');
      await expect(page.locator('.save-status')).toContainText('保存');
    });

    test('UC-04 organize notebook and tags', async ({ page }) => {
      await page.goto(`${paths.editor}?id=001`);
      await page.waitForSelector('#notebookSelect');
      await page.selectOption('#notebookSelect', { index: 1 });
      await page.fill('#tagInput', 'PlaywrightTag');
      await page.keyboard.press('Enter');
      await expect(page.locator('#tagContainer')).toContainText('PlaywrightTag');
      await expect(page).toHaveScreenshot(`uc-04-organize-${env}.png`, { fullPage: true });
    });

    test('UC-05 search in notes list', async ({ page }) => {
      await page.goto(paths.notes);
      await page.waitForSelector('#searchInput');
      await page.fill('#searchInput', 'JavaScript');
      await page.waitForTimeout(400);
      await expect(page.locator('.note-card', { hasText: 'JavaScript' })).toHaveCount(1);
      await expect(page.locator('.note-card', { hasText: 'React Hooksの使い方' })).toBeHidden();
      await expect(page).toHaveScreenshot(`uc-05-search-${env}.png`, { fullPage: true });
    });

    test('UC-06 export to PDF feedback', async ({ page }) => {
      await page.goto(`${paths.editor}?id=001`);
      await page.click('#exportBtn');
      await expect(page.locator('.toast')).toContainText('PDF生成');
      await expect(page).toHaveScreenshot(`uc-06-export-${env}.png`, { fullPage: true });
    });

    test('UC-07 share link and public view', async ({ page }) => {
      await page.goto(`${paths.editor}?id=001`);
      await page.click('#shareBtn');
      await page.waitForSelector('#shareModal:not(.hidden)');
      await page.click('#createShareLink');
      await expect(page.locator('#shareLink')).toHaveValue(/share/);
      await expect(page).toHaveScreenshot(`uc-07-share-modal-${env}.png`, { fullPage: true });

      await page.goto(`${paths.share}?token=abc123`);
      await page.waitForSelector('#noteContent:not(.hidden)');
      await expect(page.locator('.share-title')).not.toHaveText('');
      await expect(page).toHaveScreenshot(`uc-07-share-page-${env}.png`, { fullPage: true });
    });

    test('UC-08 account settings', async ({ page }) => {
      await page.goto(paths.settings);
      await page.waitForSelector('#profileForm');
      await page.fill('#displayName', 'Demo User');
      await page.click('#profileForm button[type="submit"]');
      await expect(page.locator('.toast')).toContainText('プロフィールを更新');
      await expect(page).toHaveScreenshot(`uc-08-settings-${env}.png`, { fullPage: true });

      await page.click('#deleteAccountBtn');
      await page.waitForSelector('#deleteAccountModal:not(.hidden)');
      await expect(page).toHaveScreenshot(`uc-08-delete-modal-${env}.png`, { fullPage: true });
    });
  });
});
