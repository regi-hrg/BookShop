package hu.unideb.inf.BookShop.service.impl;

import hu.unideb.inf.BookShop.data.entity.BookEntity;
import hu.unideb.inf.BookShop.data.repository.BookRepository;
import hu.unideb.inf.BookShop.service.BookService;
import hu.unideb.inf.BookShop.service.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto save(BookDto bookDto) {
        BookEntity bookEntity = convertToEntity(bookDto);
        BookEntity savedEntity = bookRepository.save(bookEntity);
        return convertToDto(savedEntity);
    }

    @Override
    public BookDto findById(Long id) {
        Optional<BookEntity> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            return convertToDto(optionalBook.get());
        } else {
            throw new RuntimeException("Book not found with id: " + id);
        }
    }

    @Override
    public List<BookDto> findAll() {
        List<BookEntity> books = bookRepository.findAll();
        return books.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cannot delete. Book not found with id: " + id);
        }
    }

    @Override
    public BookDto update(Long id, BookDto bookDto) {
        Optional<BookEntity> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            BookEntity existingBook = optionalBook.get();
            existingBook.setTitle(bookDto.getTitle());
            existingBook.setPrice(bookDto.getPrice());
            existingBook.setAuthor(bookDto.getAuthor());
            existingBook.setCategory(bookDto.getCategory());
            existingBook.setPublishYear(bookDto.getPublishYear());
            BookEntity updatedBook = bookRepository.save(existingBook);
            return convertToDto(updatedBook);
        } else {
            throw new RuntimeException("Book not found with id: " + id);
        }
    }

    private BookEntity convertToEntity(BookDto bookDto) {
        return new BookEntity(
                bookDto.getTitle(),
                bookDto.getPrice(),
                bookDto.getAuthor(),
                bookDto.getCategory(),
                bookDto.getPublishYear()
        );
    }

    private BookDto convertToDto(BookEntity bookEntity) {
        return new BookDto(
                bookEntity.getId(),
                bookEntity.getTitle(),
                bookEntity.getPrice(),
                bookEntity.getAuthor(),
                bookEntity.getCategory(),
                bookEntity.getPublishYear()
        );
    }
}
