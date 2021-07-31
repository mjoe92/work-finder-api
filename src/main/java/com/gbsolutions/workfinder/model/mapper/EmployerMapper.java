package com.gbsolutions.workfinder.model.mapper;

import com.gbsolutions.workfinder.model.dto.EmployerDto;
import com.gbsolutions.workfinder.model.entity.Employer;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface EmployerMapper extends GenericMapper<Employer, EmployerDto> {
}