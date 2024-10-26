package com.lotteon.repository.custom;

import com.lotteon.entity.product.OrderItem;

import java.util.List;

public interface OrderItemRepositoryCustom {
    public List<OrderItem> selectOrderItemsByOrderId(List<Long> orderIds);
}
