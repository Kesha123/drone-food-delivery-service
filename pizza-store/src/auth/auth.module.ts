import { Module } from '@nestjs/common';
import { AuthService } from './auth.service';
import { UserModule } from '../user/user.module';
import { PassportModule } from '@nestjs/passport';
import { LoclaStrategy } from './local.strategy';
import { JwtModule } from '@nestjs/jwt';
import { env } from '../config/env';
import { Algorithm } from 'jsonwebtoken';
import { JwtStrategy } from './jwt.startegy';

@Module({
  providers: [AuthService, LoclaStrategy, JwtStrategy],
  exports: [AuthService],
  imports: [
    UserModule,
    PassportModule,
    JwtModule.register({
      secret: env.JWT_PRIVATE_KEY,
      signOptions: {
        expiresIn: '43200s',
        algorithm: env.JWT_ALGORITHM as Algorithm
      }
    })
  ]
})
export class AuthModule {}
