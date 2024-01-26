package jpabook.jpashop6;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jpabook.jpashop6.domain.*;
import jpabook.jpashop6.domain.item.Book;
import jpabook.jpashop6.domain.service.ItemService;
import jpabook.jpashop6.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            final Member member = createMember("userA", "서울", "1", "1111");
            em.persist(member);

            final Book book1 = createBook("JPA1 BOOK", 10000, 100);
            em.persist(book1);

            final Book book2 = createBook("JPA2 BOOK", 20000, 100);
            em.persist(book2);

            final OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            final OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            final Delivery delivery = createDelivery(member);
            final Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        private Delivery createDelivery(Member member) {
            final Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }

        private Book createBook(String name, int price, int stockQuantity) {
            final Book book = new Book();
            book.setName(name);
            book.setPrice(price);
            book.setStockQuantity(stockQuantity);
            return book;
        }

        private Member createMember(String name, String city, String street, String zipcode) {
            final Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }

        public void dbInit2() {
            final Member member = createMember("userB", "부산", "2", "2222");
            em.persist(member);

            final Book book1 = createBook("SPRING1 BOOK", 20000, 200);
            em.persist(book1);

            final Book book2 = createBook("SPRING2 BOOK", 40000, 300);
            em.persist(book2);

            final OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
            final OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);

            final Delivery delivery = createDelivery(member);
            final Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }
    }
}
