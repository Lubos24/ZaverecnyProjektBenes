package cz.itnetwork.springproject.models.services;

/**
 * Importy
 */
import cz.itnetwork.springproject.models.dto.InsuredDTO;
import java.util.List;

/**
 * Rozhraní pro business logiku a práci s databází pojištěnce
 */
public interface InsuredService {

    // Vytvoření rozhraní pro následné použití v InsuredServiceImpl
    void create(InsuredDTO insured);

    // Metoda, která získá seznam všech pojištěnců
    List<InsuredDTO> getAll();

    // Metoda, která získá pojištěnce podle ID
    InsuredDTO getById(long insuredId);

    // Slouží k editaci pojištěnce
    void edit(InsuredDTO insured);

    // Slouží k mazání pojištěnce
    void remove(long insuredId);
}
