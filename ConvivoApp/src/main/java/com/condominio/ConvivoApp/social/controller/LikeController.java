package com.condominio.ConvivoApp.social.controller;

import com.condominio.ConvivoApp.social.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public void addLike(@RequestParam Long postId, @RequestParam String userId) {
        likeService.addLike(postId, userId);
    }

    @DeleteMapping
    public void removeLike(@RequestParam Long postId, @RequestParam String userId) {
        likeService.removeLike(postId, userId);
    }

    @GetMapping("/post/{postId}/count")
    public Long getLikeCount(@PathVariable Long postId) {
        return likeService.getLikeCount(postId);
    }
}

