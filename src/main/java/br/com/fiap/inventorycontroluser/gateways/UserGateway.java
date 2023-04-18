package br.com.fiap.inventorycontroluser.gateways;

import br.com.fiap.inventorycontroluser.domains.User;
import java.util.Optional;
import org.springframework.context.annotation.Primary;

@Primary
public interface UserGateway {

  User save(final User user);

  Optional<User> findByEmail(final User user);

  Optional<User> findByEmail(final String email);

  Optional<User> findById(final String userId);
}
