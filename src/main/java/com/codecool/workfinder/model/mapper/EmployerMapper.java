package com.codecool.workfinder.model.mapper;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.codecool.workfinder.model.dto.EmployerDto;
import com.codecool.workfinder.model.entity.Employer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface EmployerMapper extends GenericMapper<Employer, EmployerDto> {

    @Override
    @Mapping(source = "id", target = "id", qualifiedByName = "stringToNanoid")
    Employer toEntity(EmployerDto employerDto);

    @Named("stringToNanoid")
    static String stringToNanoid(String id) {
        return id == null ? NanoIdUtils.randomNanoId() : id;
    }
}