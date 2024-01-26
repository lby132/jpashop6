package jpabook.jpashop6;

import jakarta.annotation.PostConstruct;
import jpabook.jpashop6.domain.Address;
import jpabook.jpashop6.domain.Member;
import jpabook.jpashop6.domain.item.Book;
import jpabook.jpashop6.domain.service.ItemService;
import jpabook.jpashop6.domain.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Jpashop6Application {

	public static void main(String[] args) {
		SpringApplication.run(Jpashop6Application.class, args);
	}


	@Autowired
	private MemberService memberService;

	@Autowired
	private ItemService itemService;


	@PostConstruct
	public void s() {
		Address address = new Address("ss", "ddff", "dfsdf");

		Member member = new Member();
		member.setName("nap");
		member.setAddress(address);

		memberService.join(member);

		final Book book = new Book();
		book.setName("aa");
		book.setPrice(111);
		book.setStockQuantity(100);
		book.setAuthor("fds");
		book.setIsbn("sdfsd");

		itemService.saveItem(book);
	}
}
