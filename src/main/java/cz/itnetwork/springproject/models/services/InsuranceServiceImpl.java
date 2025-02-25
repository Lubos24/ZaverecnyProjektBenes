package cz.itnetwork.springproject.models.services;

/**
 * Importy
 */
import cz.itnetwork.springproject.data.entities.InsuranceEntity;
import cz.itnetwork.springproject.data.repositories.InsuranceRepository;
import cz.itnetwork.springproject.models.dto.InsuranceDTO;
import cz.itnetwork.springproject.models.dto.mappers.InsuranceMapper;
import cz.itnetwork.springproject.models.exceptions.InsuranceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 * Tato třída implementuje rozhraní InsuranceService a následně pracuje s logikou
 */
@Service
public class InsuranceServiceImpl implements InsuranceService {

    /**
     * Získání instance služby InsuranceRepository
     */
    @Autowired
    private InsuranceRepository insuranceRepository;

    /**
     * Získání instance služby InsuranceMapper
     */
    @Autowired
    private InsuranceMapper insuranceMapper;

    /**
     * Vytvoří instanci třídy InsuranceEntity
     * @param insurance data pojištění
     */
    public void create(InsuranceDTO insurance) {
        InsuranceEntity newInsurance = insuranceMapper.toEntity(insurance);

        // Uložení do databáze
        insuranceRepository.save(newInsurance);
    }

    /**
     * Vypíše seznam všech pojištění
     * @return namapovaný seznam pojištění
     */
    @Override
    public List<InsuranceDTO> getAll() {
        return StreamSupport.stream(insuranceRepository.findAll().spliterator(), false)
            .map(i -> insuranceMapper.toDTO(i))
            .toList();
    }

    /**
     * Vyvolá výjimku. Používá se v jednotlivých metodách.
     * @param insuranceId unikátní klíč pojištění
     * @return pojištění dle ID nebo vyjímku
     */
    private InsuranceEntity getInsuranceOrThrow(long insuranceId) {
        return insuranceRepository
                .findById(insuranceId)
                .orElseThrow(InsuranceNotFoundException::new);
    }

    /**
     * Vypíše pojištění podle ID
     * @param insuranceId unikátní klíč pojištění
     * @return namapovaná data do přepravky
     */
    @Override
    public InsuranceDTO getById(long insuranceId) {
        InsuranceEntity fetchedInsurance = getInsuranceOrThrow(insuranceId);

        return insuranceMapper.toDTO(fetchedInsurance);
    }

    /**
     * Edituje pojištění
     * @param insurance data pojištění
     */
    @Override
    public void edit (InsuranceDTO insurance) {
        InsuranceEntity fetchedInsurance = getInsuranceOrThrow(insurance.getInsuranceId());

        insuranceMapper.updateInsuranceEntity(insurance, fetchedInsurance);
        insuranceRepository.save(fetchedInsurance);
    }

    /**
     * Smaže pojištění
     * @param insuranceId unikátní klíč pojištění
     */
    @Override
    public void remove (long insuranceId) {
        InsuranceEntity fetchedInsurance = getInsuranceOrThrow(insuranceId);
        insuranceRepository.delete(fetchedInsurance);
    }
}
