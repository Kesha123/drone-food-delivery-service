import got from 'got';
import { Flight } from "./types";
import { env } from '../config/env';
import { CreateOrderDto } from '../order/dto/create-order.dto';
import { applicationLogger } from 'src/config/logger';


const postFlightToFleet = async (flight: Flight): Promise<void> => {
    try {
        const options = {
            headers: {
              ...{"Content-Type": "application/json"},
            },
            json: flight,
        };
        const response = await got
            .post(`${env.FLEET_SERVICE}/flights`, options)
            // .json<CreateOrderDto>();

        console.log(response)
        // return response
    } catch (e: any) {
        applicationLogger().error(e);
    }
}