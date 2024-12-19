package hu.unideb.inf.BookShop.service.impl;

import hu.unideb.inf.BookShop.data.entity.EligibilityEntity;
import hu.unideb.inf.BookShop.data.entity.UsersEntity;
import hu.unideb.inf.BookShop.data.repository.EligibilityRepository;
import hu.unideb.inf.BookShop.data.repository.UsersRepository;
import hu.unideb.inf.BookShop.service.AuthenticationService;
import hu.unideb.inf.BookShop.service.JwtService;
import hu.unideb.inf.BookShop.service.dto.LoginDto;
import hu.unideb.inf.BookShop.service.dto.RegistrationDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    EligibilityRepository eligibilityRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    JwtService jwtService;

    @Override
    public String registration(RegistrationDto dto) {
        UsersEntity usersEntity = modelMapper.map(dto, UsersEntity.class);
        usersEntity.setPassword(encoder.encode(usersEntity.getPassword()));
        EligibilityEntity eligibility = eligibilityRepository.findByName("USER");
        if (eligibility != null) {
            usersEntity.setEligibilities(Set.of(eligibility));
        } else {
            eligibility = new EligibilityEntity(null, "USER");
            eligibility = eligibilityRepository.save(eligibility);
            usersEntity.setEligibilities(Set.of(eligibility));
        }

        usersEntity = usersRepository.save(usersEntity);

        return jwtService.generateToken(usersEntity);

    }

    @Override
    public String login(LoginDto dto) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(),dto.getPassword())
        );
        var user = usersRepository.findByEmail(dto.getEmail());
        return jwtService.generateToken(user);
    }

    @Override
    public Long getUsersIdByEmail(String email) {
        UsersEntity users = usersRepository.findByEmail(email);
        return users.getId();
    }
}
