package com.condominio.ConvivoApp.social.service;

import com.condominio.ConvivoApp.social.entity.Like;
import com.condominio.ConvivoApp.social.entity.Post;
import com.condominio.ConvivoApp.social.mapper.LikeMapper;
import com.condominio.ConvivoApp.social.repository.LikeRepository;
import com.condominio.ConvivoApp.social.repository.PostRepository;
import com.condominio.ConvivoApp.social.dto.LikeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    public void addLike(Long postId, String userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        // Verifica se il like esiste già
        boolean alreadyLiked = likeRepository.findByPostId(postId)
                .stream()
                .anyMatch(like -> like.getUser().getId().equals(userId));

        if (alreadyLiked) {
            throw new RuntimeException("User already liked this post");
        }

        Like like = new Like();
        like.setPost(post);
        // Nota: dovrai recuperare l'utente dal UserRepository
        // like.setUser(user);

        likeRepository.save(like);
    }

    public void removeLike(Long postId, String userId) {
        Like like = likeRepository.findByPostId(postId)
                .stream()
                .filter(l -> l.getUser().getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Like not found"));

        likeRepository.delete(like);
    }

    public Long getLikeCount(Long postId) {
        return (long) likeRepository.findByPostId(postId).size();
    }

    public List<LikeDTO> getLikesByPost(Long postId) {
        return likeRepository.findByPostId(postId)
                .stream()
                .map(LikeMapper::toDTO)
                .collect(Collectors.toList());
    }
}

