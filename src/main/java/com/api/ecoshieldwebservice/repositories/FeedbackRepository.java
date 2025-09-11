package com.api.ecoshieldwebservice.repositories;

import com.api.ecoshieldwebservice.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {
    List<Feedback> findByUsuarioid_Id(Integer usuarioId);
    List<Feedback> findByFeedbacktipo(String tipo);

}
