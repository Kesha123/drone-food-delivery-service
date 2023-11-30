import { Controller, Post, Req, Res, UseGuards } from '@nestjs/common';
import { AuthService } from './auth/auth.service';
import { LocalAuthGuard } from './auth/local-auth.guard';
import { Request, Response } from 'express';
import { User } from './user/entities/user.entity';


@Controller()
export class AppController {
  constructor(
      private readonly authService: AuthService
    ) {}

    @UseGuards(LocalAuthGuard)
    @Post('/user/login')
    async login(
      @Req() req: Request,
      @Res({ passthrough: true }) res: Response,
    ): Promise<void> {
      const { access_token } = await this.authService.login(req.user as User);
      res
        .cookie('access_token', access_token, {
          httpOnly: true,
          secure: false,
          sameSite: 'lax',
          expires: new Date(Date.now() + 1 * 24 * 60 * 1000),
        })
        .send({ status: 'ok' });
    }
}
