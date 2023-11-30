import { Injectable } from "@nestjs/common";
import { PassportStrategy } from '@nestjs/passport';
import { ExtractJwt, Strategy } from "passport-jwt";
import { env } from "../config/env";
import { Request } from "express";


@Injectable()
export class JwtStrategy extends PassportStrategy(Strategy) {
    constructor() {
        super({
            jwtFromRequest: ExtractJwt.fromExtractors([
                JwtStrategy.extractJWTFromCookie,
              ]),
            ignoneExpiration: false,
            secretOrKey: env.JWT_PUBLIC_KEY,
            algorithms: [env.JWT_ALGORITHM]
        })
    }

    private static extractJWTFromCookie(req: Request): string | null {
        if (req.cookies && req.cookies.access_token) return req.cookies.access_token;
        return null;
      }

    async validate(payload: any) {
        return {
            id: payload.sub, email: payload.email
        }
    }
}
