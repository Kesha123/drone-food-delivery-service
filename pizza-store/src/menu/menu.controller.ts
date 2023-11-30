import { ClassSerializerInterceptor, Controller, Get, UseGuards, UseInterceptors } from '@nestjs/common';
import { MenuService } from './menu.service';
import { MenuItem } from './entities/menu-item.entity';

@Controller('menu')
export class MenuController {
    constructor(
        private readonly menuService: MenuService
    ) {}

    @UseInterceptors(ClassSerializerInterceptor)
    @Get()
    async fetchMenu(): Promise<MenuItem[] | undefined> {
        const items = await this.menuService.fetchMenu();
        return items;
    }
}
