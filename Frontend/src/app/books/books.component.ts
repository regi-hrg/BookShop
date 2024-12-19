import {Component, OnInit} from '@angular/core';
import { BooksService, Book } from '../books.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule } from '@angular/forms';
import { MatCardModule, MatCardContent, MatCardActions } from '@angular/material/card';
import { CurrencyPipe, CommonModule } from '@angular/common';
import {AuthService} from '../auth.service';
import {MatIcon} from '@angular/material/icon';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css'],
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    FormsModule,
    MatCardModule,
    CommonModule,
    MatCardContent,
    MatCardActions,
    CurrencyPipe,
    MatIcon
  ],
  standalone: true
})
export class BooksComponent implements OnInit {
  books: Book[] = [];

  filteredBooks: Book[] = [];
  filterTitle: string = '';
  filterAuthor: string = '';
  filterCategory: string = '';

  minPrice?: number;
  maxPrice?: number;
  minYear?: number;
  maxYear?: number;

  showModal: boolean = false;
  isEditMode: boolean = false;

  currentBook: Book = {
    id: 0,
    title: '',
    author: '',
    category: '',
    price: 0,
    publishYear: new Date().getFullYear(),
    creatorEmail: ''
  };
  currentUserEmail: string = '';
  constructor(private booksService: BooksService, private authService: AuthService) {}

  ngOnInit(): void {
    this.loadBooks();
    this.getCurrentUserEmail();
  }

  loadBooks(): void {
    this.booksService.getAllBooks().subscribe(data => {
      this.books = data;
      this.filterBooks();
    });
  }

  filterBooks(): void {
    this.filteredBooks = this.books.filter(book => {
      const matchesTitle = book.title.toLowerCase().includes(this.filterTitle.toLowerCase());
      const matchesAuthor = book.author.toLowerCase().includes(this.filterAuthor.toLowerCase());
      const matchesCategory = book.category.toLowerCase().includes(this.filterCategory.toLowerCase());
      const matchesPrice =
        (!this.minPrice || book.price >= this.minPrice) &&
        (!this.maxPrice || book.price <= this.maxPrice);
      const matchesYear =
        (!this.minYear || book.publishYear >= this.minYear) &&
        (!this.maxYear || book.publishYear <= this.maxYear);
      return matchesTitle && matchesAuthor && matchesCategory && matchesPrice && matchesYear;
    });
  }

  resetFilters(): void {
    this.filterTitle = '';
    this.filterAuthor = '';
    this.filterCategory = '';
    this.minPrice = undefined;
    this.maxPrice = undefined;
    this.minYear = undefined;
    this.maxYear = undefined;
    this.filterBooks();
  }

  openCreateForm(): void {
    this.isEditMode = false;
    this.currentBook = {id: 0, title: '', author: '', category: '', price: 3333, publishYear: new Date().getFullYear(), creatorEmail: '',};
    this.showModal = true;
  }

  openEditForm(book: Book): void {
    if (book.creatorEmail === this.currentUserEmail) {
      this.isEditMode = true;
      this.currentBook = {...book};
      this.showModal = true;
    }
  }

  saveBook(): void {
    if (this.isEditMode) {
      this.booksService.updateBook(this.currentBook).subscribe(() => this.loadBooks());
    } else {
      this.booksService.saveBook(this.currentBook).subscribe(() => this.loadBooks());
    }
    this.closeModal();
  }

  deleteBook(bookId: number): void {
    this.booksService.deleteBook(bookId).subscribe(() => this.loadBooks());
  }

  addToCart(book: Book): void {
    console.log(`Adding ${book.title} to cart`);
  }

  closeModal(): void {
    this.showModal = false;
  }

  getCurrentUserEmail(): void {
    this.authService.getUserEmail().subscribe({
      next: (email) => {
        this.currentUserEmail = email;
      },
      error: (err) => {
        console.error('Failed to fetch User email:', err);
        alert('Unable to fetch email. Please try log in again.');
      }
    });
  }

  logout(): void {
    this.authService.logout();
  }
}
