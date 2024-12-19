package hu.unideb.inf.BookShop.service.impl;

import hu.unideb.inf.BookShop.data.entity.UsersEntity;
import hu.unideb.inf.BookShop.data.repository.UsersRepository;
import hu.unideb.inf.BookShop.service.UsersService;
import hu.unideb.inf.BookShop.service.dto.UsersDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public UsersDto findByEmail(String email) {
        UsersEntity entity = usersRepository.findByEmail(email);
        return modelMapper.map(entity, UsersDto.class);
    }

}
