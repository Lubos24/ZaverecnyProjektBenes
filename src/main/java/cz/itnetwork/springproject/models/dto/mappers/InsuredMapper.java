package cz.itnetwork.springproject.models.dto.mappers;

/**
 * Importy
 */
import cz.itnetwork.springproject.data.entities.InsuredEntity;
import cz.itnetwork.springproject.models.dto.InsuredDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Rozhraní pro Mapper - převod dat z DTO na Entity
 */
@Mapper(componentModel = "spring") // Generování implementace pro Spring Boot
public interface InsuredMapper {

    // Cesta z DTO na Entity
    InsuredEntity toEntity(InsuredDTO source);

    // Cesta z Entity na DTO
    InsuredDTO toDTO(InsuredEntity source);

    // Zkopírování dat z jedné přepravky do existující přepravky (slouží pro editaci pojištěnce)
    void updateInsuredDTO(InsuredDTO source, @MappingTarget InsuredDTO target);

    // Zkopírování dat z přepravky do existující entity (slouží pro editaci pojištěnce)
    void updateInsuredEntity(InsuredDTO source, @MappingTarget InsuredEntity target);
}
