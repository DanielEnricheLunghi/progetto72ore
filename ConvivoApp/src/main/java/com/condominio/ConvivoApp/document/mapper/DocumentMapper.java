package com.condominio.ConvivoApp.document.mapper;

import com.condominio.ConvivoApp.document.dto.DocumentDto;
import com.condominio.ConvivoApp.document.entity.Document;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring", uses = { VersionMapper.class })
public interface DocumentMapper {

    DocumentDto toDto(Document entity);

    Document toEntity(DocumentDto dto);

    List<DocumentDto> toDtoList(List<Document> entities);
}
