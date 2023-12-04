import { Body, Controller, Get, Param, UseGuards, Post, Req } from '@nestjs/common';
import { OrderService } from './order.service';
import { JwtAuthGuard } from 'src/auth/jwt-auth.guard';
import { Order } from './entities/order.entity';
import { CreateOrderDto } from './dto/create-order.dto';
import { OrderModel } from 'src/db/model/Order.model';
import { Request } from 'express';


@Controller('order')
export class OrderController {
    constructor(
        private readonly orderService: OrderService
    ) {}

    @UseGuards(JwtAuthGuard)
    @Get()
    async customerOrders(): Promise<Order[] | undefined> {
        const orders = this.orderService.customerOrders('');
        return orders;
    }

    @UseGuards(JwtAuthGuard)
    @Get(':id')
    async findOrder(@Param() id: string): Promise<Order | undefined> {
        const order = this.orderService.findOrder(id);
        return order;
    }

    @UseGuards(JwtAuthGuard)
    @Post('/create')
    async createOrder (
        @Body() createOrderDto: CreateOrderDto,
        @Req() req: Request
    ): Promise<Order | undefined> {
        const order = await this.orderService.createOrder(createOrderDto, req['customerId']);
        return order;
    }

}
