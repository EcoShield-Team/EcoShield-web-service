package com.api.ecoshieldwebservice.repositories;

import com.api.ecoshieldwebservice.entities.Blog;
import com.api.ecoshieldwebservice.enums.BlogTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
    Blog findTipDelDia(BlogTipo tipo);

    @Query("SELECT b FROM Blog b WHERE b.blogtipo = 'NEWS' ORDER BY b.blogfechapublicacion DESC")
    List<Blog> findAllNews();
}
