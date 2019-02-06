package com.ftn.uns.payment_gateway.bitcoin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.payment_gateway.model.Order;
import com.ftn.uns.payment_gateway.model.PaymentType;
import com.ftn.uns.payment_gateway.service.MagazineService;
import com.ftn.uns.payment_gateway.service.PaymentTypeGatewayFactory;

@RestController
@CrossOrigin(origins = { "https://localhost:4200" })
@RequestMapping("/api/bitcoinOrder")
public class BitcoinController {

	@Autowired
	private PaymentTypeGatewayFactory paymentTypeGatewayFactory;

	@Autowired
	private MagazineService magazineService;
	
	private static final Logger logger = LoggerFactory.getLogger(BitcoinController.class); // svaka aktrivnost

	@RequestMapping(value = "/{paymentType}", method = RequestMethod.POST, produces = "application/json")
	public String getType(@PathVariable("paymentType") String paymentType, @RequestBody BitcoinDto bitcoinDto) {
		System.out.println("*********************" + bitcoinDto.getDescription() + " " + bitcoinDto.getPrice());
		System.out.println("\nPayment type: " + paymentType);

		BitcoinPaymentTypeGatewayImpl paymentTypeGateway = (BitcoinPaymentTypeGatewayImpl) paymentTypeGatewayFactory
				.getGateway(PaymentType.BITCOIN);

		String pgr = paymentTypeGateway.createOrder(createOrderBitcoin(bitcoinDto));
		String split[] = pgr.split(",");

		System.out.println("split 0 " + split[0]);
		System.out.println("split 1 " + split[1]);
		String status = "\"" + split[0] + "\"";

		System.out.println("****" + status);
		

		logger.info(status);
		return status;

	}
	
	public Order createOrderBitcoin(BitcoinDto b) {
		Order order = new Order();
		order.setBuyerEmail(b.getBuyerEmail());
		order.setBuyerFirstName(b.getName());
		order.setBuyerLastName(b.getBuyerSurname());
		order.setCurrency(b.getCurrency());
		order.setExecuted(false);
		order.setMagazine(magazineService.findById(b.getMerchantId()));
		order.setMerchandise(b.getName());
		order.setMerchantId(b.getMerchantId());
		//order.setMerchantTimestamp(merchantTimestamp); kreiranje vremena
		order.setProductId(b.getProductId());
		order.setPayerId(b.getBuyerEmail());
		order.setPrice(b.getPrice());
		order.setQuantity(b.getQuantity());
		order.setStatus("new");
		order.setType(PaymentType.BITCOIN);
		order.setProductType(b.getType());
		
		return order;
		
	}
}
