import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../services/user.service';
import { AccountType } from '../../../model/accountType';
import { User } from '../../../model/user';
import {
  AbstractControl,
  ValidationErrors,
  AsyncValidatorFn,
} from '@angular/forms';
import { Observable } from 'rxjs';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { debounceTime, map } from 'rxjs/operators';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  form: FormGroup;
  accountTypes = AccountType;

  constructor(private userService: UserService) {
    this.form = new FormGroup({
      username: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z]{1,}((\s|-)[a-zA-Z]{2,})*$/),
        Validators.maxLength(200),
      ]),
      login: new FormControl('', Validators.required, this.checkLogin()),
      accountType: new FormControl(''),
      passwordGroup: new FormGroup(
        {
          password: new FormControl('', [
            Validators.required,
            Validators.pattern(
              /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])([a-zA-Z0-9$@#_-]{8,25})$/
            ),
          ]),
          confirm: new FormControl(''),
        },
        this.checkNotEquals
      ),
    });
  }

  checkLogin(): AsyncValidatorFn {
    return (control: AbstractControl): Observable<ValidationErrors | null> => {
      return this.userService.checkLogin(control.value).pipe(
        debounceTime(1000),
        map((res: boolean) => {
          return res ? { loginUsed: true } : null;
        })
      );
    };
  }

  checkNotEquals(group: AbstractControl): ValidationErrors | null {
    let formGroup: FormGroup = group as FormGroup;
    console.log(formGroup);
    if (formGroup.controls['password'].errors) {
      return null;
    }
    return formGroup.controls['password'].value !=
      formGroup.controls['confirm'].value
      ? { checkNotEquals: true }
      : null;
  }

  save() {
    this.userService
      .insert(
        new User(
          undefined,
          this.form.controls['username'].value,
          this.form.controls['login'].value,
          this.form.controls['accountType'].value,
          this.form.get('passwordGroup.password')!.value
        )
      )
      .subscribe((user) => {
        console.log(user);
      });
  }

  ngOnInit(): void {}

  loginError() {
    if (this.form.get('login')!.hasError('required')) {
      return 'Login needed';
    } else if (this.form.get('login')!.hasError('pattern')) {
      return 'Incorrect login pattern';
    }
    return '200 characters maximum';
  }

  passwordError() {
    if (this.form.get('password')!.hasError('required')) {
      return 'Password needed';
    } else if (this.form.get('password')!.hasError('pattern')) {
      return 'Incorrect password pattern';
    }
    return '200 characters maximum';
  }

  confirmPasswordError() {
    if (
      this.form.controls['password'].value !=
      this.form.controls['confirm'].value
    ) {
      return 'Confirmation different from password';
    }
    return '';
  }
}
