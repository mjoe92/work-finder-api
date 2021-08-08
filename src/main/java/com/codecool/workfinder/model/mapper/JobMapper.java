package com.codecool.workfinder.model.mapper;

import com.codecool.workfinder.model.dto.JobDto;
import com.codecool.workfinder.model.entity.Job;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface JobMapper extends GenericMapper<Job, JobDto> {
}