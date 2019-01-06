package com.dasolute.simons.springdata;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    // 이 연관 관계에 있는 데이터를 지금 가져올래?(Eager) 아니면 나중에 가져올래? (Lazy)
    // OneToMany 는 기본적으로 Lazy 이다.
    // ManyToOne 은 기본적으로 Eager 이다.
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    public void addComment(Comment comment) {
        this.getComments().add(comment);
        comment.setPost(this);
    }
}
