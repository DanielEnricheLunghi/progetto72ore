package com.condominio.ConvivoApp.document.mapper;

import com.condominio.ConvivoApp.document.dto.DocumentVersionDto;
import com.condominio.ConvivoApp.document.entity.DocumentVersion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VersionMapper {

    @Mapping(source = "document.id", target = "id", ignore = true) // document.id not mapped directly
    DocumentVersionDto toDto(DocumentVersion version);
}
