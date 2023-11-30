import { Injectable } from '@nestjs/common';
import { UserService } from '../user/user.service';
import { comparePassword } from '../utils/bcrypt';
import { User } from '../user/entities/user.entity';
import { JwtService } from '@nestjs/jwt';

@Injectable()
export class AuthService {

    constructor(
        private userService: UserService,
        private jwtService: JwtService
    ) {}

    async validateUser(email: string, pass: string): Promise<any> {
        const user = await this.userService.findByEmail(email);
        if (!user) return null;
        const validPassword = await comparePassword(pass, user.password);
        if (!validPassword) return null;
        const { password, ...rest } = user;
        return rest;
    }

    async login(user: User | any) {
        const payload = { email: user.email, sub: user._id }
        return {
            access_token: this.jwtService.sign(payload)
        }
    }

}
