package cz.itnetwork.springproject.models.dto.mappers;

/**
 * Importy
 */
import cz.itnetwork.springproject.data.entities.InsuranceEntity;
import cz.itnetwork.springproject.models.dto.InsuranceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Rozhraní pro Mapper - převod dat z DTO na Entity
 */
@Mapper(componentModel = "spring")// Generování implementace pro Spring Boot
public interface InsuranceMapper {

    // Cesta z DTO na Entity
    InsuranceEntity toEntity(InsuranceDTO source);

    // Cesta z Entity na DTO
    InsuranceDTO toDTO(InsuranceEntity source);

    // Zkopírování dat z jedné přepravky do existující přepravky (slouží pro editaci pojištění)
    void updateInsuranceDTO (InsuranceDTO source, @MappingTarget InsuranceDTO target);

    // Zkopírování dat z přepravky do existující entity (slouží pro editaci pojištění)
    void updateInsuranceEntity (InsuranceDTO source, @MappingTarget InsuranceEntity target);
}
