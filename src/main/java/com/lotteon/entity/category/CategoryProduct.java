package com.lotteon.entity.category;

import com.lotteon.dto.responseDto.GetCategoryDto;
import com.lotteon.dto.responseDto.TestResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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
    private Long categoryId;
    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_level")
    private int categoryLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private CategoryProduct parent;

    @ToString.Exclude
    @OneToMany(mappedBy = "parent")
    private List<CategoryProduct> children;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<CategoryProductMapper> productMappings = new ArrayList<>();

    public TestResponseDto toDto() {
        return TestResponseDto.builder()
                .name(categoryName)
                .level(categoryLevel)
                .parent(parent.getCategoryName())
                .build();
    }

    public GetCategoryDto toGetCategoryDto(){
        return GetCategoryDto.builder()
                .name(categoryName)
                .id(categoryId)
                .build();
    }


}
