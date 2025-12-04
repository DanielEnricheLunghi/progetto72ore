package com.condominio.ConvivoApp.social.controller;

import com.condominio.ConvivoApp.social.dto.PostDTO;
import com.condominio.ConvivoApp.social.entity.Post;
import com.condominio.ConvivoApp.social.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public PostDTO createPost(@RequestBody PostDTO postDTO) {
        return postService.createPost(postDTO);
    }

    @GetMapping("/condominium/{condoId}")
    public List<PostDTO> getPostsByCondominium(@PathVariable Integer condoId) {
        return postService.getPostsByCondominium(condoId);
    }

    @GetMapping("/{id}")
    public PostDTO getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}

