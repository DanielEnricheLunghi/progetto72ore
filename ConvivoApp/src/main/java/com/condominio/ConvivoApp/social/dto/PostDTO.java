package com.condominio.ConvivoApp.social.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PostDTO {

    private Long id; // deve esistere
    private String title;
    private String body;
    private String visibility;
    private LocalDateTime createdAt;
    private String authorUsername;
    private Long condominiumId; // ID del condominio associato al post
    private List<CommentDTO> comments; // ✅ nuovo campo


    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }


    // getter e setter corretti
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public Long getCondominiumId() {
        return condominiumId;
    }

    public void setCondominiumId(Long condominiumId) {
        this.condominiumId = condominiumId;
    }
}
