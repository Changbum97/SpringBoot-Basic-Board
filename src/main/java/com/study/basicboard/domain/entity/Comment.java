package com.study.basicboard.domain.entity;

import com.study.basicboard.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Comment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;      // 작성자

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;    // 댓글이 달린 게시판

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent; // 대댓글이 달린 부모 댓글

    @OneToMany(mappedBy = "parent")
    private List<Comment> children = new ArrayList<>(); // 댓글에 달린 대댓글들(자식)

    public void update(String newBody) {
        this.body = newBody;
    }
}
