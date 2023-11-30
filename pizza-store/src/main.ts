import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { ValidationPipe } from '@nestjs/common';
import * as cookieParser from 'cookie-parser';
import { env } from './config/env';
import { corsProd } from './config/cors/cors.prod';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);
  app.useGlobalPipes(new ValidationPipe({}));
  app.setGlobalPrefix('api');
  app.enableCors(corsProd);
  app.use(cookieParser());

  await app.listen(env.BACKEND_PORT);

  console.log('--------------------------');
  console.log(`Backend is running on ${env.BACKEND_PORT}`);
  console.log('--------------------------');


}
bootstrap();
