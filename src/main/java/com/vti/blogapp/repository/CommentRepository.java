package com.vti.blogapp.repository;

import com.vti.blogapp.dto.CommentDto;
import com.vti.blogapp.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository
        extends JpaRepository<Comment, Long> {
    // 1. Method name
    // Tiền tố: findBy, existsBy, countBy, deleteBy
    // VD: Lấy ra tất cả comment theo name
    List<Comment> findByName(String name);
    // VD: Lấy ra tất cả comment có body chứa "search"
    List<Comment> findByBodyContaining(String search);
    // VD: Lấy ra tất cả comment theo name hoặc email
    List<Comment> findByNameOrEmail(String name, String email);
    // VD: Lấy ra tất cả comment theo post id
    Page<Comment> findByPostId(Long postId, Pageable pageable);

    // 2. @Query
    @Query("DELETE FROM Comment WHERE email = :email")
    @Modifying
    void deleteByEmail(@Param("email") String email);

    @Modifying
    @Query("DELETE FROM Comment WHERE name = ?1 AND email = ?2")
    void deleteByNameAndEmail(String name, String email);

    @Query(value = "SELECT * FROM comment WHERE id > ?1", nativeQuery = true)
    Page<CommentDto> findByIdGreaterThan(Long id, Pageable pageable);
}
