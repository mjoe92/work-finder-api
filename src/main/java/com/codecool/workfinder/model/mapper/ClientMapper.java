package com.codecool.workfinder.model.mapper;

import com.codecool.workfinder.model.dto.ClientDto;
import com.codecool.workfinder.model.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Mapper(componentModel = "spring")
public interface ClientMapper extends GenericMapper<Client, ClientDto> {

    @Override
    @Mapping(source = "id", target = "id", qualifiedByName = "toUUIDString")
    Client toEntity(ClientDto clientDto);

    @Named("toUUIDString")
    static String toUUIDString(String id) {
        return id == null ? UUID.randomUUID().toString() : id;
    }
}