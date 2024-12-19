package hu.unideb.inf.BookShop.service;

import hu.unideb.inf.BookShop.service.dto.UsersDto;

import java.util.List;

public interface UsersService {

    UsersDto findByEmail(String email);
}
