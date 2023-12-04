// import got from 'got';
import { Flight } from "./types";
import { env } from '../config/env';
import { CreateOrderDto } from '../order/dto/create-order.dto';
import { applicationLogger } from '../config/logger';
import { Order } from "../order/entities/order.entity";


export const postFlightToFleet = async (flight: Flight): Promise<Order> => {
    try {
        const options = {
            headers: {
              ...{"Content-Type": "application/json"},
            },
            body: JSON.stringify(flight),
            method: "POST"
        };
        const response = await fetch(`${env.FLEET_SERVICE}/flights`, options);
        const data = await response.json();
        return data;
    } catch (e: any) {
        applicationLogger().error(e);
    }
}