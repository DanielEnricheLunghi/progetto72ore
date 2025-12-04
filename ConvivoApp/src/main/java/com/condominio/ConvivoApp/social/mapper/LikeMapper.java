package com.condominio.ConvivoApp.social.mapper;

import com.condominio.ConvivoApp.social.dto.LikeDTO;
import com.condominio.ConvivoApp.social.entity.Like;

public class LikeMapper {

    public static LikeDTO toDTO(Like like) {
        if (like == null) return null;

        LikeDTO dto = new LikeDTO();
        dto.setId(like.getId());
        dto.setPostId(like.getPost() != null ? like.getPost().getId() : null);
        dto.setUserId(like.getUser() != null ? String.valueOf(like.getUser().getId()) : null);
        dto.setUsername(like.getUser() != null ? like.getUser().getUsername() : null);
        return dto;
    }

    public static Like toEntity(LikeDTO dto) {
        if (dto == null) return null;

        Like like = new Like();
        like.setId(dto.getId());
        // Post e User vanno settati nel service
        return like;
    }
}
