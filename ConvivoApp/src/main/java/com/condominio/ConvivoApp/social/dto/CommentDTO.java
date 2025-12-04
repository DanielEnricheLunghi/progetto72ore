package com.condominio.ConvivoApp.social.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private Long id;
    private String body;
    private String authorUsername;
    private LocalDateTime createdAt;
    private Long postId;


}