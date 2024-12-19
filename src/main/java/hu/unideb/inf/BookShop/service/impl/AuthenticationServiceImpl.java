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
        UsersEntity felhasznaloEntity = modelMapper.map(dto, UsersEntity.class);
        felhasznaloEntity.setPassword(encoder.encode(felhasznaloEntity.getPassword()));
        EligibilityEntity jog = eligibilityRepository.findByName("FELHASZNALO");
        if (jog != null) {
            felhasznaloEntity.setEligibilities(Set.of(jog));
        } else {
            jog = new EligibilityEntity(null, "FELHASZNALO");
            jog = eligibilityRepository.save(jog);
            felhasznaloEntity.setEligibilities(Set.of(jog));
        }

        felhasznaloEntity = usersRepository.save(felhasznaloEntity);

        return jwtService.generateToken(felhasznaloEntity);

    }

    @Override
    public String login(LoginDto dto) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(),dto.getPassword())
        );
        var user = usersRepository.findByEmail(dto.getEmail());
        return jwtService.generateToken(user);
    }
}
