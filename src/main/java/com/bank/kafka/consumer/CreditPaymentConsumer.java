package com.bank.kafka.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.bank.model.Purchase;
import com.bank.service.PurchaseService;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.Disposable;

@Component
public class CreditPaymentConsumer {
	
	@Autowired
	private PurchaseService purchaseService;
		
	ObjectMapper objectMapper = new ObjectMapper();
	
	@KafkaListener( groupId = "credit-consumer-group",topics = "purchase-topic")
	public Disposable retrieveCreatedPurchase(String data) throws Exception {
		
		Purchase purchase = objectMapper.readValue(data, Purchase.class);
		
		return (Disposable)purchaseService.update(purchase);
				
      }
	
}
