package com.condominio.ConvivoApp.notification.mapper;

import com.condominio.ConvivoApp.notification.dto.NotificationDto;
import com.condominio.ConvivoApp.notification.entity.Notification;
import com.condominio.ConvivoApp.notification.dto.CreateNotificationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationDto toDto(Notification entity);

    @Mapping(target = "id", ignore = true)
    Notification toEntity(CreateNotificationRequest req);
}
