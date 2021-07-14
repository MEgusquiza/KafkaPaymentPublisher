package com.bank.kafka.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.bank.model.CreditPayment;


@Component
public class CreditPaymentPublisher {
	
    private final Logger Logger = LoggerFactory.getLogger(CreditPaymentPublisher.class);
    

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;
	
	private String topic = "credit-payment-topic";

	public void sendCreditPaymentTransactionTopic(CreditPayment creditPayment) {
		
	   Logger.info("Payment published");
	
	   kafkaTemplate.send(topic, creditPayment);
		
	}
	
}
