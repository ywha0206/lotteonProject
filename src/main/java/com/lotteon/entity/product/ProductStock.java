package com.lotteon.entity.product;

import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_stock")
public class ProductStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_id")
    @ToString.Exclude
    private Product product;

    @Column(name = "prod_stock")
    private Integer prodOptionStock;

    @Column(name = "prod_stock_name")
    private String prodOptionName;

    @Column(name = "prod_stock_value")
    private String prodOptionValue;

}
