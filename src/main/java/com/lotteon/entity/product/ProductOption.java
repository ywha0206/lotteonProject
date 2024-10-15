package com.lotteon.entity.product;

import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_option")
public class ProductOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prod_id")
    private Long productId;

    @Column(name = "option_deli")
    private Boolean optionDeliver;

    @Column(name = "option_installment")
    private Boolean optionInstallment;

    @Column(name = "option_card")
    private Boolean optionCard;
}
