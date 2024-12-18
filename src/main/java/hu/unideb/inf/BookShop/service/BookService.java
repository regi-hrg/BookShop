package hu.unideb.inf.BookShop.service;

import hu.unideb.inf.BookShop.service.dto.BookDto;

import java.util.List;

public interface BookService {

    BookDto save(BookDto bookDto);

    BookDto findById(Long id);

    List<BookDto> findAll();

    void delete(Long id);

    BookDto update(Long id, BookDto bookDto);
}
