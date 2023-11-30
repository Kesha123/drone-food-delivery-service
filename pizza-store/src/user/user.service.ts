import { ClassSerializerInterceptor, Injectable, UseInterceptors, UsePipes, ValidationPipe } from '@nestjs/common';
import { CreateUserDto } from './dto/create-user.dto';
import { InjectModel } from '@nestjs/mongoose';
import { IUserModel } from 'src/db/model/User.model';
import { SerializedUser, User } from './entities/user.entity';
import { encodePassword } from '../utils/bcrypt';


@Injectable()
export class UserService {

  constructor(
    @InjectModel('User') private userModel: IUserModel
  ) {}

  @UsePipes(ValidationPipe)
  async create(createUserDto: CreateUserDto): Promise<User | undefined> {
    const encryptedPassword = await encodePassword(createUserDto.password);
    const newUser = await this.userModel.create({
      ...createUserDto,
      password: encryptedPassword,
    });
    return newUser;
  }

  async findByEmail(email: string): Promise<User | undefined> {
    const user = await this.userModel.findByEmail(email);
    return user;
  }

  @UseInterceptors(ClassSerializerInterceptor)
  async findOne(id: string): Promise<any | undefined> {
    let user = await this.userModel.findById(id).lean();
    const { password, ...rest } = user
    return rest;
  }

  async remove(id: number): Promise<void | undefined> {
    await this.userModel.findByIdAndDelete(id);
  }
}
