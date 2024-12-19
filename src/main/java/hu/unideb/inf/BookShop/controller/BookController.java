package hu.unideb.inf.BookShop.controller;

import hu.unideb.inf.BookShop.service.AuthenticationService;
import hu.unideb.inf.BookShop.service.BookService;
import hu.unideb.inf.BookShop.service.dto.BookDto;
import hu.unideb.inf.BookShop.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    AuthenticationService authenticationService;


    @PostMapping
    public BookDto createBook(@RequestBody BookDto bookDto, Authentication authentication) {
        String email = authentication.getName();
        bookDto.setCreatorEmail(email);
        return bookService.save(bookDto);
    }

    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto, Authentication authentication) {
        BookDto existingBook = bookService.findById(id);

        if (!existingBook.getCreatorEmail().equals(authentication.getName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        bookDto.setId(id);
        return ResponseEntity.ok(bookService.update(id, bookDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id, Authentication authentication) {
        BookDto existingBook = bookService.findById(id);
        String creatorEmail = existingBook.getCreatorEmail();
        String currentUserEmail = authentication.getName();

        if (!creatorEmail.equals(currentUserEmail)) {
            System.out.println("Forbidden: " + currentUserEmail + " is not the creator of this book.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
