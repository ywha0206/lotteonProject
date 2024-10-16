package com.lotteon.entity.product;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_item_option")
public class OrderItemOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_item_id", nullable = false)
    @ToString.Exclude
    private OrderItem orderItem; // 소속 주문 항목

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_option_id", nullable = false)
    private ProductOption productOption; // 선택된 옵션
}
