import { config } from 'dotenv';
import * as process from 'process';

config();

type IEnvironment = {
  BACKEND_PORT: string;
  NODE_ENV: string;
  APP_NAME: string;
  SERVER_TO_SERVER_AUTHENTICATION_KEY: string;
  MONGO_CONNECTION_STRING: string;
  JWT_PRIVATE_KEY: string;
  JWT_PUBLIC_KEY: string;
  JWT_ALGORITHM: string;
  BCRYPT_SALT: string;
};

export const env: IEnvironment = {
  BACKEND_PORT: <string>process.env.BACKEND_PORT,
  NODE_ENV: <string>process.env.NODE_ENV,
  APP_NAME: <string>process.env.APP_NAME,
  SERVER_TO_SERVER_AUTHENTICATION_KEY: <string>(
    process.env.SERVER_TO_SERVER_AUTHENTICATION_KEY
  ),
  MONGO_CONNECTION_STRING: <string>process.env.MONGO_CONNECTION_STRING,
  JWT_PRIVATE_KEY: <string>process.env.JWT_PRIVATE_KEY,
  JWT_PUBLIC_KEY: <string>process.env.JWT_PUBLIC_KEY,
  JWT_ALGORITHM: <string>process.env.JWT_ALGORITHM,
  BCRYPT_SALT: <string>process.env.BCRYPT_SALT,
};
