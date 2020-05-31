package com.kelelas.germes.controller;

import com.kelelas.germes.config.Regex;
import com.kelelas.germes.dto.UserDTO;
import com.kelelas.germes.entity.RoleType;
import com.kelelas.germes.entity.User;
import com.kelelas.germes.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@RestController
public class RegistrationController {
    UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/registration")
    public RedirectView RegFormController(@RequestParam(value = "error", required = false) String error, UserDTO user) {
        RedirectView redirectView = new RedirectView();
        if (verify(user)) {
            boolean resultOfSave = userService.saveNewUser(User.builder()
                    .nameUkr(user.getNameUkr())
                    .nameEng(user.getNameEng())
                    .email(user.getEmail())
                    .password(new BCryptPasswordEncoder().encode(user.getPassword()))
                    .role(RoleType.USER)
                    .isActive(true)
                    .balance(5000)
                    .build());
            if (resultOfSave) {
                log.info("{}", user);
                redirectView.setUrl("/login");
            } else {
                redirectView.setUrl("/registration?error=userAlreadyExist");
            }
        }else
            redirectView.setUrl("/registration?regex=error");
        return redirectView;
    }
    public boolean verify(UserDTO user){
        return user.getEmail().matches(Regex.EMAIL_REGEX)
                && user.getNameEng().matches(Regex.NAME_REGEX)
                && user.getNameUkr().matches(Regex.NAME_UKR_REGEX)
                && user.getPassword().matches(Regex.PASSWORD_REGEX);

    }
}
