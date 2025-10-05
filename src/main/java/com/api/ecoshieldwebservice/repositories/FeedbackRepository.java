package com.api.ecoshieldwebservice.repositories;

import com.api.ecoshieldwebservice.entities.Feedback;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.enums.FeedbackTipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback,Long> {
    List<Feedback> findByUsuario(Usuario usuario);
    List<Feedback> findByFeedbackTipo(FeedbackTipo tipo);
}
