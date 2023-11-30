import { Module } from '@nestjs/common';
import { MenuService } from './menu.service';
import { MenuController } from './menu.controller';
import { MongooseModule } from '@nestjs/mongoose';
import { MenuItemModel } from '../db/model/MenuItem.model';

@Module({
  providers: [MenuService],
  controllers: [MenuController],
  imports: [
    MongooseModule.forFeature([{ name: 'MenuItem', schema: MenuItemModel.schema }])
  ],
  exports: [MenuService]
})
export class MenuModule {}
