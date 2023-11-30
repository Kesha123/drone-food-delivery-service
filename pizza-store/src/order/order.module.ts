import { Module } from '@nestjs/common';
import { OrderService } from './order.service';
import { OrderController } from './order.controller';
import { MongooseModule } from '@nestjs/mongoose';
import { OrderModel } from '../db/model/Order.model';

@Module({
  providers: [OrderService],
  controllers: [OrderController],
  exports: [OrderService],
  imports: [
    MongooseModule.forFeature([{ name: 'Order', schema: OrderModel.schema }])
  ]
})
export class OrderModule {}
