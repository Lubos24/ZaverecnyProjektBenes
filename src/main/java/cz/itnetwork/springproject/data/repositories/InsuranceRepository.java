package cz.itnetwork.springproject.data.repositories;

/**
 * Importy
 */
import cz.itnetwork.springproject.data.entities.InsuranceEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * CRUD rozhraní pro práci s tabulkou - vytvvořit, číst, měnit, mazat
 */
public interface InsuranceRepository extends CrudRepository<InsuranceEntity, Long> {
}
