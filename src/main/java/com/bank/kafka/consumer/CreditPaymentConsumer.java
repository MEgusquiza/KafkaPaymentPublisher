package com.bank.kafka.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.bank.model.Purchase;
import com.bank.service.PurchaseService;

//@Component
public class CreditPaymentConsumer {
	
	@Autowired
	private PurchaseService purchaseService;
		
	@KafkaListener( groupId = "credit-payment-publish-yanki",topics = "credit-payment-topic")
	public Purchase retrieveCreatedPurchase(Purchase purchase) throws Exception {
	return purchaseService.update(purchase);
     }
	
}
