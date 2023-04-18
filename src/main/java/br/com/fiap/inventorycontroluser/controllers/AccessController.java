package br.com.fiap.inventorycontroluser.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import br.com.fiap.inventorycontroluser.controllers.jsons.request.UserRequestJson;
import br.com.fiap.inventorycontroluser.controllers.jsons.response.UserResponseJson;
import br.com.fiap.inventorycontroluser.domains.User;
import br.com.fiap.inventorycontroluser.usecases.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/access")
@RequiredArgsConstructor
public class AccessController {

  private final UserService userService;

  @PostMapping(consumes = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<UserResponseJson> createLogin(
      @RequestBody @Valid final UserRequestJson userRequestJson) {
    log.info("Creation of login :: {}", userRequestJson.getEmail());
    final User user =
        User.builder()
            .name(userRequestJson.getName())
            .email(userRequestJson.getEmail())
            .password(new BCryptPasswordEncoder().encode(userRequestJson.getPassword()))
            .build();
    return userService.create(user);
  }

  @GetMapping(value = "/email/{email}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<UserResponseJson> findByEmail(
      @PathVariable("email") final String email) {
    log.info("Find email :: {}", email);
    return userService.findByEmail(email);
  }

  @GetMapping(value = "/user/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<UserResponseJson> findByUserId(
      @PathVariable("id") final String id) {
    log.info("Find userId :: {}", id);
    return userService.findByUserId(id);
  }
}
