package jpabook.jpashop6.domain.service;

import jpabook.jpashop6.domain.Delivery;
import jpabook.jpashop6.domain.Member;
import jpabook.jpashop6.domain.Order;
import jpabook.jpashop6.domain.OrderItem;
import jpabook.jpashop6.domain.item.Item;
import jpabook.jpashop6.domain.repository.ItemRepository;
import jpabook.jpashop6.domain.repository.MemberRepository;
import jpabook.jpashop6.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }


}
