package com.lotteon.entity.category;

import com.lotteon.dto.responseDto.TestResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category_product")
public class CategoryProduct {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long category_id;
    @Column(name = "category_name")
    private String category_name;

    @Column(name = "category_level")
    private int category_level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private CategoryProduct parent;

    @ToString.Exclude
    @OneToMany(mappedBy = "parent")
    private List<CategoryProduct> children;

    public TestResponseDto toDto() {
        return TestResponseDto.builder()
                .name(category_name)
                .level(category_level)
                .parent(parent.getCategory_name())
                .build();
    }
}
