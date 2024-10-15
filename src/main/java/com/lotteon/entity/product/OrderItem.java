package com.lotteon.entity.product;

import com.lotteon.entity.member.Seller;
import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sell_id")
    private Seller seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "order_item_quantity")
    private int quantity;

    @Column(name = "order_item_total")
    private int total;

    @Column(name = "order_item_state1")
    private Boolean state1;

    @Column(name = "order_item_state2")
    private int state2;

    @Column(name = "order_item_discount")
    private int discount;

    @Column(name = "order_item_req" ,columnDefinition = "TEXT")
    private String req;
}
