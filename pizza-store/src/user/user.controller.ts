import { Controller, Get, Post, Body, Param, Delete, UseGuards } from '@nestjs/common';
import { UserService } from './user.service';
import { CreateUserDto } from './dto/create-user.dto';
import { JwtAuthGuard } from 'src/auth/jwt-auth.guard';


@Controller('user')
export class UserController {
  constructor(private readonly userService: UserService) {}

  @Post('/create')
  createProfile(@Body() createUserDto: CreateUserDto) {
    return this.userService.create(createUserDto);
  }

  @UseGuards(JwtAuthGuard)
  @Get(':id')
  getProfile(@Param('id') id: string) {
    return this.userService.findOne(id);
  }


  @UseGuards(JwtAuthGuard)
  @Delete(':id')
  deleteProfile(@Param('id') id: string) {
    return this.userService.remove(+id);
  }
}
