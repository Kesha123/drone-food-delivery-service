import { MiddlewareConsumer, Module, NestModule } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { UserModule } from './user/user.module';
import { MongooseModule } from '@nestjs/mongoose';
import { env } from './config/env';
import { AuthModule } from './auth/auth.module';
import { MenuModule } from './menu/menu.module';
import { OrderModule } from './order/order.module';
import { OrderMiddleware } from './middleware/order.middleware';
import { OrderController } from './order/order.controller';

@Module({
  imports: [
    UserModule,
    MongooseModule.forRoot(
      env.MONGO_CONNECTION_STRING,
      {
        dbName: "dronora",
      }
    ),
    AuthModule,
    MenuModule,
    OrderModule
  ],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule implements NestModule {
  configure(consumer: MiddlewareConsumer) {
    consumer
      .apply(OrderMiddleware)
      .forRoutes(OrderController)
  }

}
