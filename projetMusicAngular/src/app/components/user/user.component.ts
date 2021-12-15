import { Component, OnInit } from '@angular/core';

import { User } from './../../model/user';
import { UserService } from './../../services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
})
export class UserComponent implements OnInit {
  users: User[] = [];
  login: string | null = '';

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.initUsers();
    this.login = this.getLogin();
  }

  getLogin(): string | null {
    if (sessionStorage.getItem('login') != null) {
      return sessionStorage.getItem('login');
    }
    return 'lol';
  }

  initUsers() {
    this.userService.allUser().subscribe((result: any[]) => {
      this.users = [];
      for (let value of result) {
        this.users.push(
          new User(
            value['id'],
            value['username'],
            value['login'],
            value['password'],
            value['accountType']
          )
        );
      }
    });
  }
}
