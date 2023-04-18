package br.com.fiap.inventorycontroluser.gateways.repositories;

import br.com.fiap.inventorycontroluser.domains.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

  Optional<User> findByEmail(String email);
}
