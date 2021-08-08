package com.codecool.workfinder.model.mapper;

import com.codecool.workfinder.model.dto.ClientDto;
import com.codecool.workfinder.model.entity.Client;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ClientMapper extends GenericMapper<Client, ClientDto> {
}