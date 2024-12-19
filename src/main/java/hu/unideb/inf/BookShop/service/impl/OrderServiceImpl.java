package hu.unideb.inf.BookShop.service.impl;

import hu.unideb.inf.BookShop.data.entity.BookEntity;
import hu.unideb.inf.BookShop.data.entity.OrderEntity;
import hu.unideb.inf.BookShop.data.entity.UsersEntity;
import hu.unideb.inf.BookShop.data.repository.BookRepository;
import hu.unideb.inf.BookShop.data.repository.OrderRepository;
import hu.unideb.inf.BookShop.data.repository.UsersRepository;
import hu.unideb.inf.BookShop.service.OrderService;
import hu.unideb.inf.BookShop.service.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            BookRepository bookRepository,
                            UsersRepository usersRepository) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public OrderDto save(OrderDto orderDto) {
        OrderEntity orderEntity = convertToEntity(orderDto);
        OrderEntity savedEntity = orderRepository.save(orderEntity);
        return convertToDto(savedEntity);
    }

    @Override
    public OrderDto findById(Long id) {
        Optional<OrderEntity> optionalOrder = orderRepository.findById(id);
        return optionalOrder.map(this::convertToDto)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    @Override
    public List<OrderDto> findAll() {
        return orderRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cannot delete. Order not found with id: " + id);
        }
    }

    @Override
    public OrderDto update(Long id, OrderDto orderDto) {
        OrderEntity orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        orderEntity.setDate(orderDto.getDate());
        orderEntity.setStatus(orderDto.getStatus());

        if (orderDto.getUserId() != null) {
            UsersEntity user = usersRepository.findById(orderDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            orderEntity.setUsers(user);
        }

        if (orderDto.getBookIds() != null) {
            Set<BookEntity> books = orderDto.getBookIds().stream()
                    .map(bookId -> bookRepository.findById(bookId)
                            .orElseThrow(() -> new RuntimeException("Book not found")))
                    .collect(Collectors.toSet());
            orderEntity.setBooks(books);
        }

        OrderEntity updatedEntity = orderRepository.save(orderEntity);
        return convertToDto(updatedEntity);
    }

    private OrderDto convertToDto(OrderEntity orderEntity) {
        return new OrderDto(
                orderEntity.getId(),
                orderEntity.getDate(),
                orderEntity.getStatus(),
                orderEntity.getUsers() != null ? orderEntity.getUsers().getId() : null,
                orderEntity.getBooks().stream()
                        .map(BookEntity::getId)
                        .collect(Collectors.toSet())
        );
    }

    private OrderEntity convertToEntity(OrderDto orderDto) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setDate(orderDto.getDate());
        orderEntity.setStatus(orderDto.getStatus());

        if (orderDto.getUserId() != null) {
            UsersEntity user = usersRepository.findById(orderDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            orderEntity.setUsers(user);
        }

        if (orderDto.getBookIds() != null) {
            Set<BookEntity> books = orderDto.getBookIds().stream()
                    .map(bookId -> bookRepository.findById(bookId)
                            .orElseThrow(() -> new RuntimeException("Book not found")))
                    .collect(Collectors.toSet());
            orderEntity.setBooks(books);
        }

        return orderEntity;
    }
}
