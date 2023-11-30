import { corsDev } from './cors.dev';

export const corsProd = {
  ...corsDev,
  origin: [
    'http://localhost:3000',
  ],
};
