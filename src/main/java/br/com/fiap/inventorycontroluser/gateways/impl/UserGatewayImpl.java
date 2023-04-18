package br.com.fiap.inventorycontroluser.gateways.impl;

import br.com.fiap.inventorycontroluser.domains.User;
import br.com.fiap.inventorycontroluser.gateways.UserGateway;
import br.com.fiap.inventorycontroluser.gateways.repositories.UserRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class UserGatewayImpl implements UserGateway {

  private final UserRepository userRepository;

  @Override
  public User save(final User user) {
    log.info("Saving user: {}", user.getName());
    return userRepository.save(user);
  }

  @Override
  public Optional<User> findByEmail(final User user) {
    log.info("Searching email: {}", user.getEmail());
    return userRepository.findByEmail(user.getEmail());
  }

  @Override
  public Optional<User> findByEmail(final String email) {
    log.info("Searching email: {}", email);
    return userRepository.findByEmail(email);
  }

  @Override
  public Optional<User> findById(final String userId) {
    return userRepository.findById(userId);
  }
}
