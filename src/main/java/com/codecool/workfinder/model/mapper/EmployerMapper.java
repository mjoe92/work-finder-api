package com.codecool.workfinder.model.mapper;

import com.codecool.workfinder.model.dto.EmployerDto;
import com.codecool.workfinder.model.entity.Employer;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface EmployerMapper extends GenericMapper<Employer, EmployerDto> {
}