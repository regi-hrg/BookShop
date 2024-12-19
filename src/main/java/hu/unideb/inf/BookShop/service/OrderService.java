package hu.unideb.inf.BookShop.service;

import hu.unideb.inf.BookShop.service.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto save(OrderDto orderDto);

    OrderDto findById(Long id);

    List<OrderDto> findAll();

    void delete(Long id);

    OrderDto update(Long id, OrderDto orderDto);
}
