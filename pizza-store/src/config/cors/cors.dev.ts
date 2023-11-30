export const corsDev = {
  origin: 'http://localhost:3000',
  credentials: true,
  methods: ['GET, POST, PATCH, PUT, DELETE, OPTIONS'],
  optionsSuccessStatus: 200,
  allowedHeaders: [
    'Content-Type',
    'Authorization',
    'X-Requested-With',
    'device-remember-token',
    'Access-Control-Allow-Origin',
    'Origin',
    'Accept',
    'customer-id',
    'X-Portal-Cookie',
  ],
};
