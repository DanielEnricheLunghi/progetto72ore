package com.condominio.ConvivoApp.social.mapper;

import com.condominio.ConvivoApp.social.dto.CommentDTO;
import com.condominio.ConvivoApp.social.entity.Comment;

public class CommentMapper {

    public static CommentDTO toDTO(Comment comment) {
        if (comment == null) return null;

        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setBody(comment.getBody());
        dto.setAuthorUsername(comment.getAuthor() != null ?
                comment.getAuthor().getUsername() : null);
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setPostId(comment.getPost() != null ?
                comment.getPost().getId() : null);
        return dto;
    }

    public static Comment toEntity(CommentDTO dto) {
        if (dto == null) return null;

        Comment comment = new Comment();
        comment.setId(dto.getId());
        comment.setBody(dto.getBody());
        comment.setCreatedAt(dto.getCreatedAt());
        // Post e Author vanno settati nel service
        return comment;
    }
}

