package com.vti.blogapp.form;

import com.vti.blogapp.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PostFilterForm {
    private String search;
    private LocalDate minCreatedDate;
    private LocalDate maxCreatedDate;
    private Post.Status status;
}