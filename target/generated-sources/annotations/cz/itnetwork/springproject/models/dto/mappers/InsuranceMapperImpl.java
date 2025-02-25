package cz.itnetwork.springproject.models.dto.mappers;

import cz.itnetwork.springproject.data.entities.InsuranceEntity;
import cz.itnetwork.springproject.models.dto.InsuranceDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-24T10:38:00+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.13 (Amazon.com Inc.)"
)
@Component
public class InsuranceMapperImpl implements InsuranceMapper {

    @Override
    public InsuranceEntity toEntity(InsuranceDTO source) {
        if ( source == null ) {
            return null;
        }

        InsuranceEntity insuranceEntity = new InsuranceEntity();

        insuranceEntity.setExpiration( source.getExpiration() );
        insuranceEntity.setInformation( source.getInformation() );
        if ( source.getInsuranceId() != null ) {
            insuranceEntity.setInsuranceId( source.getInsuranceId() );
        }
        insuranceEntity.setName( source.getName() );
        insuranceEntity.setPrice( source.getPrice() );
        insuranceEntity.setSubject( source.getSubject() );
        insuranceEntity.setValid( source.getValid() );

        return insuranceEntity;
    }

    @Override
    public InsuranceDTO toDTO(InsuranceEntity source) {
        if ( source == null ) {
            return null;
        }

        InsuranceDTO insuranceDTO = new InsuranceDTO();

        insuranceDTO.setExpiration( source.getExpiration() );
        insuranceDTO.setInformation( source.getInformation() );
        insuranceDTO.setInsuranceId( source.getInsuranceId() );
        insuranceDTO.setName( source.getName() );
        insuranceDTO.setPrice( source.getPrice() );
        insuranceDTO.setSubject( source.getSubject() );
        insuranceDTO.setValid( source.getValid() );

        return insuranceDTO;
    }

    @Override
    public void updateInsuranceDTO(InsuranceDTO source, InsuranceDTO target) {
        if ( source == null ) {
            return;
        }

        target.setExpiration( source.getExpiration() );
        target.setInformation( source.getInformation() );
        target.setInsuranceId( source.getInsuranceId() );
        target.setName( source.getName() );
        target.setPrice( source.getPrice() );
        target.setSubject( source.getSubject() );
        target.setValid( source.getValid() );
    }

    @Override
    public void updateInsuranceEntity(InsuranceDTO source, InsuranceEntity target) {
        if ( source == null ) {
            return;
        }

        target.setExpiration( source.getExpiration() );
        target.setInformation( source.getInformation() );
        if ( source.getInsuranceId() != null ) {
            target.setInsuranceId( source.getInsuranceId() );
        }
        target.setName( source.getName() );
        target.setPrice( source.getPrice() );
        target.setSubject( source.getSubject() );
        target.setValid( source.getValid() );
    }
}
