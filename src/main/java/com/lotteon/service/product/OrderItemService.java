package com.lotteon.service.product;

import com.lotteon.dto.requestDto.cartOrder.OrderDto;
import com.lotteon.dto.requestDto.cartOrder.OrderItemDto;
import com.lotteon.entity.member.Seller;
import com.lotteon.entity.product.Order;
import com.lotteon.entity.product.OrderItem;
import com.lotteon.entity.product.Product;
import com.lotteon.repository.member.SellerRepository;
import com.lotteon.repository.product.OrderItemRepository;
import com.lotteon.repository.product.OrderRepository;
import com.lotteon.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class OrderItemService {

    private final OrderService orderService;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;

    public ResponseEntity insertOrderItem(List<OrderItemDto> orderItemDto, OrderDto orderDto) {
        log.info("오더아이템 서비스 들어옴 ");

        Order order = orderService.insertOrder(orderDto);
        log.info("오더 저장 성공 : "+order);

        if(order==null){
            ResponseEntity.badRequest().body("로그인 해야 함");
        }

        for(OrderItemDto orderItem :orderItemDto){
            Optional<Product> optProduct = productRepository.findById(orderItem.getProductId());

            if(!optProduct.isPresent()){
                return ResponseEntity.ok().body(false);
            }
            log.info("프로덕트 조회 : "+ optProduct.get());

            Long sellId = optProduct.get().getSellId();

            log.info("셀러 아이디 가지고 왔나요? : "+sellId);

            Optional<Seller> optSeller = sellerRepository.findById(sellId);

            if(!optSeller.isPresent()){
                return ResponseEntity.ok().body(false);
            }
            log.info("셀러 조회: "+optSeller.get());

            OrderItem savedorderItem = OrderItem.builder()
                    .order(order)
                    .product(optProduct.get())
                    .seller(optSeller.get())
                    .quantity(orderItem.getQuantity())
                    .total(orderItem.getTotalPrice())
                    .discount(orderItem.getDiscount())
                    .state1(false)
                    .deli(orderItem.getDeliver())
                    .build();

            log.info("오더아이템디티오 엔티티로 변환 : "+savedorderItem);

            OrderItem returnorderItem= orderItemRepository.save(savedorderItem);

            log.info("오더아이템 저장 성공 : "+returnorderItem);

            if(returnorderItem==null){
                return ResponseEntity.ok().body(false);
            }
        };
        return ResponseEntity.ok().body(true);
    }
}
