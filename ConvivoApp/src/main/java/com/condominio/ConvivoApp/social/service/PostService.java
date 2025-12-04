package com.condominio.ConvivoApp.social.service;


import com.condominio.ConvivoApp.social.dto.PostDTO;
import com.condominio.ConvivoApp.social.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.condominio.ConvivoApp.social.entity.Post;
import com.condominio.ConvivoApp.social.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public PostDTO createPost(PostDTO dto) {
        // DTO → Entity
        Post entity = PostMapper.toEntity(dto);

        // Salvataggio
        Post saved = postRepository.save(entity);

        // Entity → DTO
        return PostMapper.toDTO(saved);
    }




    /**
     * Restituisce la lista dei post di un determinato condominio ordinati per data di creazione decrescente.
     *
     * Flusso:
     * 1. Chiama il repository per ottenere tutte le entity Post filtrate per condominiumId,
     *    ordinate dal più recente al più vecchio.
     * 2. Converte ogni entity Post in un PostDTO tramite il PostMapper (Entity → DTO).
     * 3. Colleziona i DTO in una lista e la restituisce al chiamante.
     *
     * @param condoId l'identificativo del condominio (Integer, convertito in Long per la query)
     * @return lista di PostDTO corrispondenti ai post del condominio, ordinati per createdAt discendente
     */


    public List<PostDTO> getPostsByCondominium(Integer condoId) {
        return postRepository.findByCondominiumIdOrderByCreatedAtDesc(Long.valueOf(condoId))
                .stream()
                .map(PostMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Recupera un singolo post per ID.
     * @param id identificativo del post
     * @return PostDTO corrispondente, oppure null se non trovato
     */
    public PostDTO getPostById(Long id) {
        return postRepository.findById(id)
                .map(PostMapper::toDTO)
                .orElse(null);
    }

    /**
     * Elimina un post per ID.
     * @param id identificativo del post da eliminare
     */
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

}
