package cz.itnetwork.springproject.data.repositories;

/**
 * Importy
 */
import cz.itnetwork.springproject.data.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

/**
 * CRUD rozhraní pro práci s uživatelem - vytvvořit, číst, měnit, mazat
 */
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
}
