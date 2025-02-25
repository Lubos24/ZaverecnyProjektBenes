package cz.itnetwork.springproject.models.services;

/**
 * Importy
 */
import cz.itnetwork.springproject.models.dto.InsuranceDTO;
import java.util.List;

/**
 * Rozhraní pro business logiku a práci s databází pojištění
 */
public interface InsuranceService {

    // Vytvoření rozhraní pro následné použití v InsuranceServiceImpl
    void create (InsuranceDTO insurance);

    // Metoda, která získá seznam všech pojištění
    List<InsuranceDTO> getAll();

    // Metoda, která získá pojištění podle ID
    InsuranceDTO getById(long insuranceId);

    // Metoda k editaci pojištění
    void edit (InsuranceDTO insurance);

    // Metoda k mazání pojištění
    void remove (long insuranceId);
}
