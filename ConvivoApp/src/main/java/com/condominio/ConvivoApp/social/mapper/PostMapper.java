package com.condominio.ConvivoApp.social.mapper;


import com.condominio.ConvivoApp.social.entity.Comment;
import com.condominio.ConvivoApp.social.entity.Post;
import com.condominio.ConvivoApp.social.dto.PostDTO;
import com.condominio.ConvivoApp.social.dto.CommentDTO;
import java.util.stream.Collectors;

public class PostMapper {

    public static PostDTO toDTO(Post post) {
        if (post == null) return null;
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setBody(post.getBody());
        dto.setVisibility(post.getVisibility());
        dto.setAuthorUsername(post.getAuthor().getUsername());
        dto.setCreatedAt(post.getCreatedAt());

        // Se author è un oggetto User
        if (post.getAuthor() != null) {
            dto.setAuthorUsername(post.getAuthor().getUsername());
        }


        if (post.getComments() != null) {
            dto.setComments(post.getComments()
                    .stream()
                    .map(PostMapper::commentToDTO)
                    .collect(Collectors.toList()));
        }
        return dto;
    }


    public static CommentDTO commentToDTO(Comment c) {
        if (c == null) return null;
        CommentDTO dto = new CommentDTO();
        dto.setId(c.getId());
        dto.setBody(c.getBody());
        dto.setAuthorUsername(c.getAuthor().getUsername());
        dto.setCreatedAt(c.getCreatedAt());

        if (c.getAuthor() != null) {
            dto.setAuthorUsername(c.getAuthor().getUsername());
        }


        return dto;
    }

    public static Post toEntity(PostDTO dto) {
        if (dto == null) return null;
        Post entity = new Post();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setBody(dto.getBody());
        entity.setVisibility(dto.getVisibility());
        entity.setCreatedAt(dto.getCreatedAt());

        // Se author è un oggetto User, devi fare il lookup nel service
        // entity.setAuthor(userRepository.findByUsername(dto.getAuthorUsername()));
        // Se author è una stringa, e Post ha un campo String author:
        // entity.setAuthor(dto.getAuthorUsername());

        return entity;
    }
}