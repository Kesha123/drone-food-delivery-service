import { Injectable } from '@nestjs/common';
import { InjectModel } from '@nestjs/mongoose';
import { IOrderModel } from '../db/model/Order.model';
import { Order } from './entities/order.entity';
import { CreateOrderDto } from './dto/create-order.dto';

@Injectable()
export class OrderService {
    constructor(
        @InjectModel('Order') private orderModel: IOrderModel
    ) {}

    async customerOrders(userId: string): Promise<Order[] | undefined> {
        const orders = this.orderModel.findByCustomer(userId);
        return orders;
    }

    async findOrder(id: string): Promise<Order | undefined> {
        const order = this.orderModel.findById(id).lean();
        return order;
    }

    async createOrder(createOrderDto: CreateOrderDto): Promise<Order | undefined> {
        const order = await this.orderModel.create(createOrderDto);
        return order;
    }
}
