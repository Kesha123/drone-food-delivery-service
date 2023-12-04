import { Injectable, NestMiddleware } from '@nestjs/common';
import { Response, Request, NextFunction } from 'express';
import { verify } from 'jsonwebtoken';
import { env } from '../config/env';

@Injectable()
export class OrderMiddleware implements NestMiddleware {
  use(req: Request, res: Response, next: NextFunction) {
    const token = req.cookies.access_token;
    const decoded = validate(token);
    const data = decoded as JwtPayload;
    req['customerId'] = data.sub;
    next();
  }
}


export const validate = (token: string) => {
  const key = env.JWT_PUBLIC_KEY ?? null;
  try {
    return verify(token, key ?? "default");
  } catch (e) {
    return false;
  }
};

export interface JwtPayload {
  sub: string
  email: string
}