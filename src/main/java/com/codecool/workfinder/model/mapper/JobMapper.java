package com.codecool.workfinder.model.mapper;

import com.codecool.workfinder.model.dto.JobDto;
import com.codecool.workfinder.model.entity.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Mapper(componentModel = "spring")
public interface JobMapper extends GenericMapper<Job, JobDto> {

    @Override
    @Mapping(source = "id", target = "id", qualifiedByName = "fromStringToUUIDString")
    Job toEntity(JobDto jobDto);

    @Named("fromStringToUUIDString")
    static String toUUIDString(String id) {
        return id == null ? UUID.randomUUID().toString() : id;
    }
}