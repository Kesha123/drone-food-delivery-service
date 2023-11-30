import { Module } from '@nestjs/common';
import { UserService } from './user.service';
import { UserController } from './user.controller';
import { MongooseModule } from '@nestjs/mongoose';
import { UserModel } from '../db/model/User.model';

@Module({
  imports:[
    MongooseModule.forFeature([{ name: 'User', schema: UserModel.schema }])
  ],
  controllers: [UserController],
  providers: [UserService],
  exports: [UserService]
})
export class UserModule {}
