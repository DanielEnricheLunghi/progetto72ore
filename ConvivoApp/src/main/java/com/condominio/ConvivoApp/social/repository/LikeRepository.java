package com.condominio.ConvivoApp.social.repository;

import com.condominio.ConvivoApp.social.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByPostId(Long postId);
}
