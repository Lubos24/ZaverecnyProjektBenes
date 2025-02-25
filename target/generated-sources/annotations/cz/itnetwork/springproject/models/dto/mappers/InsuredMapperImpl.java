package cz.itnetwork.springproject.models.dto.mappers;

import cz.itnetwork.springproject.data.entities.InsuredEntity;
import cz.itnetwork.springproject.models.dto.InsuredDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-20T14:41:24+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.13 (Amazon.com Inc.)"
)
@Component
public class InsuredMapperImpl implements InsuredMapper {

    @Override
    public InsuredEntity toEntity(InsuredDTO source) {
        if ( source == null ) {
            return null;
        }

        InsuredEntity insuredEntity = new InsuredEntity();

        insuredEntity.setInsuredId( source.getInsuredId() );
        insuredEntity.setName( source.getName() );
        insuredEntity.setSurname( source.getSurname() );
        insuredEntity.setEmail( source.getEmail() );
        insuredEntity.setPhone( source.getPhone() );
        insuredEntity.setAddress( source.getAddress() );
        insuredEntity.setCity( source.getCity() );
        insuredEntity.setPostcode( source.getPostcode() );

        return insuredEntity;
    }

    @Override
    public InsuredDTO toDTO(InsuredEntity source) {
        if ( source == null ) {
            return null;
        }

        InsuredDTO insuredDTO = new InsuredDTO();

        insuredDTO.setName( source.getName() );
        insuredDTO.setSurname( source.getSurname() );
        insuredDTO.setEmail( source.getEmail() );
        insuredDTO.setPhone( source.getPhone() );
        insuredDTO.setAddress( source.getAddress() );
        insuredDTO.setCity( source.getCity() );
        insuredDTO.setPostcode( source.getPostcode() );
        insuredDTO.setInsuredId( source.getInsuredId() );

        return insuredDTO;
    }

    @Override
    public void updateInsuredDTO(InsuredDTO source, InsuredDTO target) {
        if ( source == null ) {
            return;
        }

        target.setName( source.getName() );
        target.setSurname( source.getSurname() );
        target.setEmail( source.getEmail() );
        target.setPhone( source.getPhone() );
        target.setAddress( source.getAddress() );
        target.setCity( source.getCity() );
        target.setPostcode( source.getPostcode() );
        target.setInsuredId( source.getInsuredId() );
    }

    @Override
    public void updateInsuredEntity(InsuredDTO source, InsuredEntity target) {
        if ( source == null ) {
            return;
        }

        target.setInsuredId( source.getInsuredId() );
        target.setName( source.getName() );
        target.setSurname( source.getSurname() );
        target.setEmail( source.getEmail() );
        target.setPhone( source.getPhone() );
        target.setAddress( source.getAddress() );
        target.setCity( source.getCity() );
        target.setPostcode( source.getPostcode() );
    }
}
