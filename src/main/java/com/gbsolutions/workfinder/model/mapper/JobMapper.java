package com.gbsolutions.workfinder.model.mapper;

import com.gbsolutions.workfinder.model.dto.JobDto;
import com.gbsolutions.workfinder.model.entity.Job;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface JobMapper extends GenericMapper<Job, JobDto> {
}