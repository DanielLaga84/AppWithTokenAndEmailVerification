package com.example.amigoscode.registration;

import com.example.amigoscode.appuser.AppUser;
import com.example.amigoscode.appuser.AppUserRole;
import com.example.amigoscode.appuser.AppUserService;
import com.example.amigoscode.email.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final AppUserService appUserService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
       boolean isValidEmail = emailValidator.test(request.getEmail());
       if (!isValidEmail) {
           throw new IllegalStateException("email not valid");
       }
        return appUserService.singUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );
    }
}
