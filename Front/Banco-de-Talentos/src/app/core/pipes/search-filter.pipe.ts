import { Pipe, PipeTransform } from '@angular/core';
import { UserList } from 'src/app/shared/models/interfaces/userList.interfaces';

@Pipe({
  name: 'searchFilter',
})
export class SearchFilterPipe implements PipeTransform {
  transform(value: UserList[], search: string): UserList[] {
    if (search.length === 0) return value;

    const filteredFavorites = value.filter((fav) =>
      fav.listName.includes(search)
    );
    return filteredFavorites;
  }
}
