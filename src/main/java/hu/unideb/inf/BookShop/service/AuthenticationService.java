package hu.unideb.inf.BookShop.service;


import hu.unideb.inf.BookShop.service.dto.RegistrationDto;
import hu.unideb.inf.BookShop.service.dto.LoginDto;

public interface AuthenticationService {
    String registration(RegistrationDto dto);

    String login(LoginDto dto);
}
