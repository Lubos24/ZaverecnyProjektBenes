package cz.itnetwork.springproject.models.services;

/**
 * Importy
 */
import cz.itnetwork.springproject.data.entities.InsuredEntity;
import cz.itnetwork.springproject.data.repositories.InsuredRepository;
import cz.itnetwork.springproject.models.dto.InsuredDTO;
import cz.itnetwork.springproject.models.dto.mappers.InsuredMapper;
import cz.itnetwork.springproject.models.exceptions.InsuredNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 * Tato třída implementuje rozhraní InsuredService a následně pracuje s logikou
 */
@Service
public class InsuredServiceImpl implements InsuredService {

    /**
     * Získání instance služby InsuredRepository
     */
    @Autowired
    private InsuredRepository insuredRepository;

    /**
     * Získání instance služby InsuredMapper
     */
    @Autowired
    private InsuredMapper insuredMapper;

    /**
     * Vytvoří novou instanci třídy InsuredEntity
     * @param insured data pojištěnce
     */
    @Override
    public void create(InsuredDTO insured) {
        InsuredEntity newInsured = insuredMapper.toEntity(insured);

        // Uložení do databáze
        insuredRepository.save(newInsured);
    }

    /**
     * Vypíše seznam všech pojištěnců
     * @return namapovaný seznam pojištěnců
     */
    @Override
    public List<InsuredDTO> getAll() {
        return StreamSupport.stream(insuredRepository.findAll().spliterator(), false)
                .map(i -> insuredMapper.toDTO(i))
                .toList();
        }

    /**
     * Vypíše pojištěnce podle ID
     * @param insuredId unikátní klíč pojištěnce
     * @return namapovaná data do přepravky
     */
    @Override
    public InsuredDTO getById(long insuredId) {
        InsuredEntity fetchedInsured = getInsuredOrThrow(insuredId);

        return insuredMapper.toDTO(fetchedInsured);
    }

    /**
     * Edituje pojištěnce
     * @param insured pojištěnec
     */
    @Override
    public void edit(InsuredDTO insured) {
        InsuredEntity fetchedInsured = getInsuredOrThrow(insured.getInsuredId());

        insuredMapper.updateInsuredEntity(insured, fetchedInsured);
        insuredRepository.save(fetchedInsured);
    }

    /**
     * Vyvolá výjimku. Používá se v jednotlivých metodách.
     * @param insuredId unikátní klíč pojištěnce
     * @return pojištěnce dle ID nebo vyjímku
     */
    private InsuredEntity getInsuredOrThrow(long insuredId) {
        return insuredRepository
                .findById(insuredId)
                .orElseThrow(InsuredNotFoundException::new);
    }

    /**
     * Smaže pojištěnce
     * @param insuredId unikátní klíč pojištěnce
     */
    @Override
    public void remove(long insuredId) {
        InsuredEntity fetchedEntity = getInsuredOrThrow(insuredId);
        insuredRepository.delete(fetchedEntity);
    }
}