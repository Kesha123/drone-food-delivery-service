import { Injectable } from '@nestjs/common';
import { InjectModel } from '@nestjs/mongoose';
import { IMenuItemModel } from 'src/db/model/MenuItem.model';
import { MenuItem } from './entities/menu-item.entity';

@Injectable()
export class MenuService {
    constructor(
        @InjectModel('MenuItem') private menuItemModel: IMenuItemModel
    ) {}

    async fetchMenu(): Promise<MenuItem[] | undefined> {
        const items = await this.menuItemModel.find().lean();
        return items;
    }
}
