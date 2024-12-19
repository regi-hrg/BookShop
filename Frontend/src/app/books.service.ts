import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';

export interface Book {
  id: number;
  title: string;
  price: number;
  author: string;
  category: string;
  publishYear: number;
  creatorEmail: string;
}

@Injectable({
  providedIn: 'root'
})
export class BooksService {
  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient, private snackBar: MatSnackBar) {}

  private getHeaders(): HttpHeaders {
    return new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('token')}`,
      'Access-Control-Allow-Origin': '*'
    });
  }

  getAllBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.baseUrl}/books`, { headers: this.getHeaders() });
  }

  saveBook(book: Book): Observable<Book> {
    return this.http.post<Book>(`${this.baseUrl}/books`, book, { headers: this.getHeaders() });
  }

  updateBook(book: Book): Observable<Book> {
    return this.http.put<Book>(`${this.baseUrl}/books/${book.id}`, book, { headers: this.getHeaders() });
  }

  deleteBook(bookId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/books/${bookId}`, { headers: this.getHeaders() });
  }
}
