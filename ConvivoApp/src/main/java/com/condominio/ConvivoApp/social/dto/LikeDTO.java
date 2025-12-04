package com.condominio.ConvivoApp.social.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class LikeDTO {
    private Long id;
    private Long postId;
    private String userId;
    private String username;
    private LocalDateTime createdAt;
}

