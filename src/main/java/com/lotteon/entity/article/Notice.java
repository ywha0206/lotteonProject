package com.lotteon.entity.article;

import com.lotteon.entity.category.CategoryArticle;
import com.lotteon.entity.member.Member;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notice")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_cate1")
    private CategoryArticle cate1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_cate2")
    private CategoryArticle cate2;

    @Column(name = "notice_title")
    private String noticeTitle;

    @Column(name = "notice_rdate")
    private Timestamp noticeRdate;

    @Column(name = "notice_content", columnDefinition = "TEXT")
    private String noticeContent;

    @Column(name = "notice_views")
    private int noticeViews;

}
