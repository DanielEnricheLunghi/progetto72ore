package com.condominio.ConvivoApp.social.service;

import com.condominio.ConvivoApp.social.dto.CommentDTO;
import com.condominio.ConvivoApp.social.entity.Comment;
import com.condominio.ConvivoApp.social.entity.Post;
import com.condominio.ConvivoApp.social.mapper.CommentMapper;
import com.condominio.ConvivoApp.social.repository.CommentRepository;
import com.condominio.ConvivoApp.social.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentDTO createComment(CommentDTO commentDTO) {
        Comment comment = CommentMapper.toEntity(commentDTO);

        // Recupera il post dal database
        Post post = postRepository.findById(commentDTO.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));
        comment.setPost(post);

        Comment saved = commentRepository.save(comment);
        return CommentMapper.toDTO(saved);
    }

    public List<CommentDTO> getCommentsByPost(Long postId) {
        return commentRepository.findByPostId(postId)
                .stream()
                .map(CommentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}

