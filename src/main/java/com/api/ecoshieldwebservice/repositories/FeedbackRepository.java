package com.api.ecoshieldwebservice.repositories;

import com.api.ecoshieldwebservice.entities.Feedback;
import com.api.ecoshieldwebservice.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {
    List<Feedback> findByUsuarioid(Usuario usuario);
    List<Feedback> findByFeedbacktipo(String tipo);

}
