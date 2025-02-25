package cz.itnetwork.springproject.data.repositories;

/**
 * Importy
 */
import cz.itnetwork.springproject.data.entities.InsuredEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * CRUD rozhraní pro práci s tabulkou - vytvvořit, číst, měnit, mazat
 */
public interface InsuredRepository extends CrudRepository<InsuredEntity, Long> {
}
