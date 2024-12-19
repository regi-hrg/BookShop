package hu.unideb.inf.BookShop.controller;

import hu.unideb.inf.BookShop.service.AuthenticationService;
import hu.unideb.inf.BookShop.service.dto.LoginDto;
import hu.unideb.inf.BookShop.service.dto.RegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationService service;

    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> handleOptions(){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/registration")
    public String regisztracio(@RequestBody RegistrationDto dto){
        return service.registration(dto);
    }

    @PostMapping("/login")
    public String bejelentkezes(@RequestBody LoginDto dto){
        return service.login(dto);
    }

    @GetMapping("/user-email")
    public String getUserEmail(Authentication authentication) {
        return authentication.getName();
    }
}
