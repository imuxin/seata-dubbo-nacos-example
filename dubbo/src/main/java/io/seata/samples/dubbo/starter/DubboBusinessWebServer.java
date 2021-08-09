package io.seata.samples.dubbo.starter;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.seata.samples.dubbo.Account;
import io.seata.samples.dubbo.Order;
import io.seata.samples.dubbo.Storage;
import io.seata.samples.dubbo.service.BusinessService;

@RestController
public class DubboBusinessWebServer {

	private ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
			new String[]{"spring/dubbo-business.xml"});
    private final BusinessService business = (BusinessService) context.getBean("business");

	@GetMapping("/api/v1/accounts")
	public List<Account> listAccount() {
		return business.listAccount();
	}

	@GetMapping("/api/v1/orders")
	public List<Order> listOrder() {
		return business.listOrder();
	}

	@GetMapping("/api/v1/storages")
	public List<Storage> listStorage() {
		return business.listStorage();
	}

	@PostMapping("/api/v1/doBusiness")
	public void doBusiness(@RequestParam(value = "userId") String userId,
			@RequestParam(value = "commodityCode") String commodityCode,
			@RequestParam(value = "orderCount") int orderCount) {
		business.purchase(userId, commodityCode, orderCount);
	}

}
