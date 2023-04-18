package br.com.fiap.inventorycontroluser.usecases;

import br.com.fiap.inventorycontroluser.controllers.jsons.response.UserResponseJson;
import br.com.fiap.inventorycontroluser.domains.User;
import br.com.fiap.inventorycontroluser.gateways.UserGateway;
import java.net.URI;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

  public static final String URL_HTTP = "http://localhost:8081";
  private final UserGateway userGateway;

  public ResponseEntity<UserResponseJson> create(final User user) {

    final Optional<User> email = userGateway.findByEmail(user);
    if (email.isPresent()) {
      return ResponseEntity.badRequest().build();
    }

    final User userSaved = userGateway.save(user);

    final UserResponseJson userResponseJson =
        UserResponseJson.builder()
            .id(userSaved.getId())
            .name(userSaved.getName())
            .email(userSaved.getEmail())
            .build();

    return ResponseEntity.created(
            URI.create(URL_HTTP + "/login" + userResponseJson.getId()))
        .body(userResponseJson);
  }

  public ResponseEntity<UserResponseJson> findByEmail(final String email) {
    return userGateway.findByEmail(email).stream()
        .filter(Objects::nonNull)
        .map(user -> ResponseEntity.ok().body(
            UserResponseJson.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build()))
        .findFirst().orElse(ResponseEntity.badRequest().body(null));
  }

  public ResponseEntity<UserResponseJson> findByUserId(final String id) {
    return userGateway.findById(id).stream()
        .filter(Objects::nonNull)
        .map(user -> ResponseEntity.ok().body(
            UserResponseJson.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build()))
        .findFirst().orElse(ResponseEntity.badRequest().body(null));
  }
}
