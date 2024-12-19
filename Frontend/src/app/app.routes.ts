import { Routes } from '@angular/router';
import {RegistrationComponent} from './registration/registration.component';
import {BooksComponent} from './books/books.component';
import {LoginComponent} from './login/login.component';

export const routes: Routes = [
  { path: 'registration', component: RegistrationComponent },
  { path: 'login', component: LoginComponent },
  { path: 'books', component: BooksComponent },
  { path: '', redirectTo: '/registration', pathMatch: 'full' },
];
