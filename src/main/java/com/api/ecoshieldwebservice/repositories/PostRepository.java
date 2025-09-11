package com.api.ecoshieldwebservice.repositories;

import com.api.ecoshieldwebservice.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {

}
