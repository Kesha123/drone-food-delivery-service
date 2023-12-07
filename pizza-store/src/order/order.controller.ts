import { Body, Controller, Get, Param, UseGuards, Post, Req, Put, Query } from '@nestjs/common';
import { OrderService } from './order.service';
import { JwtAuthGuard } from 'src/auth/jwt-auth.guard';
import { Order, OrderStatus } from './entities/order.entity';
import { CreateOrderDto } from './dto/create-order.dto';
import { Request } from 'express';


@Controller('order')
export class OrderController {
    constructor(
        private readonly orderService: OrderService
    ) {}

    // @UseGuards(JwtAuthGuard)
    @Post('/create')
    async createOrder (
        @Body() createOrderDto: CreateOrderDto,
        @Req() req: Request
    ): Promise<Order | undefined> {
        const order = await this.orderService.createOrder(createOrderDto, req['customerId']);
        return order;
    }

    @Put('/update/:id')
    async updateOrder (
        @Param('id') id: string,
        @Query('status') status: OrderStatus
    ): Promise<Order | undefined> {
        const updatedOrder = await this.orderService.updateOrder(id, status);
        return updatedOrder;
    }

}
