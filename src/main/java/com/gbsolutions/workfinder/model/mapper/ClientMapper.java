package com.gbsolutions.workfinder.model.mapper;

import com.gbsolutions.workfinder.model.dto.ClientDto;
import com.gbsolutions.workfinder.model.entity.Client;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ClientMapper extends GenericMapper<Client, ClientDto> {
}