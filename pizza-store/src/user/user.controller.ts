import { Controller, Get, Post, Body, Param, Delete, UseGuards, UsePipes, ValidationPipe, ClassSerializerInterceptor, UseInterceptors } from '@nestjs/common';
import { UserService } from './user.service';
import { CreateUserDto } from './dto/create-user.dto';
import { JwtAuthGuard } from 'src/auth/jwt-auth.guard';
import { SerializedUser, User } from './entities/user.entity';


@Controller('user')
export class UserController {
  constructor(private readonly userService: UserService) {}

  @UsePipes(ValidationPipe)
  @Post('/create')
  async createProfile(@Body() createUserDto: CreateUserDto): Promise<User | undefined> {
    const user = await this.userService.create(createUserDto);
    return user;
  }

  @UseInterceptors(ClassSerializerInterceptor)
  @UseGuards(JwtAuthGuard)
  @Get(':id')
  async getProfile(@Param('id') id: string): Promise<SerializedUser | undefined> {
    const user = await this.userService.findOne(id);
    return new SerializedUser(user);
  }

  @UseGuards(JwtAuthGuard)
  @Delete(':id')
  async deleteProfile(@Param('id') id: string): Promise<void> {
    await this.userService.remove(+id);
  }
}
