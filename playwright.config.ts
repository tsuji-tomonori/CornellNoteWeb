import { defineConfig } from '@playwright/test';
import path from 'node:path';

const env = process.env.APP_ENV ?? 'real';
const defaultBaseURL = env === 'mock' ? 'http://localhost:4001' : 'http://localhost:3000';
const baseURL = process.env.APP_BASE_URL ?? defaultBaseURL;
const snapshotRoot = env === 'mock'
  ? path.resolve('mock/screenshots')
  : path.resolve('tests/e2e/visual');

export default defineConfig({
  testDir: './tests/e2e',
  timeout: 60_000,
  snapshotPathTemplate: `${snapshotRoot}/{testFileDir}/{testFileName}-snapshots/{arg}{ext}`,
  expect: {
    toMatchSnapshot: {
      maxDiffPixelRatio: 0.01
    }
  },
  use: {
    baseURL,
    locale: 'ja-JP',
    timezoneId: 'Asia/Tokyo',
    viewport: { width: 1280, height: 720 },
    deviceScaleFactor: 1,
    colorScheme: 'light',
    screenshot: 'only-on-failure',
    trace: 'retain-on-failure'
  },
  reporter: [['html', { open: 'never' }]]
});
