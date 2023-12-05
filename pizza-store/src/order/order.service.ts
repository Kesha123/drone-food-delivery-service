import { Injectable } from '@nestjs/common';
import { InjectModel } from '@nestjs/mongoose';
import { IOrderModel } from '../db/model/Order.model';
import { FleetServiceOrder, Order, OrderStatus } from './entities/order.entity';
import { CreateOrderDto } from './dto/create-order.dto';
import { postFlightToFleet, updateOrder as updateOrderFleet } from '../services/fleet.service';
import { env } from '../config/env';

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

    async createOrder(createOrderDto: CreateOrderDto, customerId: string): Promise<Order | undefined> {
        const newOrder: Order = {
            ...createOrderDto,
            status: OrderStatus.Waiting,
            drone: '',
            customer: customerId,
        }

        const fleetServiceFlight = await postFlightToFleet(newOrder);

        newOrder.drone = fleetServiceFlight.drone;
        newOrder.status = fleetServiceFlight.status;

        return fleetServiceFlight;
    }

    async updateOrder(orderId: string, status: OrderStatus): Promise<Order | undefined> {
        await this.orderModel.findByIdAndUpdate(orderId, { status: status })
        const updatedOrder = await this.orderModel.findById(orderId);
        await updateOrderFleet(updatedOrder);
        return updatedOrder;
    }
}
