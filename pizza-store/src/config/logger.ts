import { Logger } from 'winston';
import { env } from './env';
import { NODE_ENV } from './types';
const winston = require('winston');


export const applicationLogger = (): Logger => {
  const logger = winston.createLogger({
    level: 'info',
    format: winston.format.combine(
      winston.format.timestamp(),
      winston.format.json(),
    ),
    defaultMeta: { service: env.APP_NAME },
    transports: [
      // new winston.transports.MongoDB({
      //   level: 'info',
      //   db: env.MONGO_CONNECTION_STRING,
      // }),
      new winston.transports.File({
        filename: 'combined.log',
        level: 'info'
      }),
      new winston.transports.File({
        filename: 'errors.log',
        level: 'error'
      })
    ],
  });

  if (env.NODE_ENV === NODE_ENV.Development) {
    logger.add(
      new winston.transports.Console({
        format: winston.format.simple(),
      }),
    );
  }
  return logger;
};

export type ILogger = typeof Logger;
