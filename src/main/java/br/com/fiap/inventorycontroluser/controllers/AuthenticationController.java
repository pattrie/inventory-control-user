package br.com.fiap.inventorycontroluser.controllers;

import br.com.fiap.inventorycontroluser.controllers.jsons.request.UserRequestJson;
import br.com.fiap.inventorycontroluser.controllers.jsons.response.TokenResponseJson;
import br.com.fiap.inventorycontroluser.controllers.jsons.response.UserResponseJson;
import br.com.fiap.inventorycontroluser.domains.User;
import br.com.fiap.inventorycontroluser.usecases.TokenService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationManager authManager;

  private final TokenService tokenService;

  @PostMapping
  public ResponseEntity<TokenResponseJson> authenticate(@RequestBody @Valid UserRequestJson login) {
    final UsernamePasswordAuthenticationToken loginData = login.converter();

    try {
      final Authentication authentication = authManager.authenticate(loginData);
      final String token = tokenService.generateToken(authentication);

      final User user = (User) authentication.getPrincipal();
      final UserResponseJson userResponseJson = UserResponseJson.builder()
          .id(user.getId())
          .name(user.getName())
          .email(user.getEmail())
          .build();

      final TokenResponseJson tokenResponseJson =
          TokenResponseJson.builder()
              .user(userResponseJson)
              .token(token)
              .type("Bearer")
              .build();

      return ResponseEntity.ok(tokenResponseJson);
    } catch (AuthenticationException e) {
      return ResponseEntity.badRequest().build();
    }
  }
}
