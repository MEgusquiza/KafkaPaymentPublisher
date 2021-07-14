package com.bank.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.kafka.publisher.CreditPaymentPublisher;
import com.bank.model.CreditPayment;
import com.bank.service.CreditPaymentService;


@RestController
@RequestMapping("/credit")
@EnableCaching
public class CreditPaymentController {

  private final Logger logger = LoggerFactory.getLogger(CreditPaymentPublisher.class);
  
      @Autowired
      private CreditPaymentPublisher creditPaymentPublisher;
     
	  @Autowired
      private CreditPaymentService service;

	     @PostMapping
	     public CreditPayment saveCreditPayment(@RequestBody CreditPayment creditPayment) {
	     logger.info("Send credit payment to topic.");
	     
	     creditPaymentPublisher.sendCreditPaymentTransactionTopic(creditPayment);
	     return service.save(creditPayment);
	     }

	    @GetMapping
	    public List<CreditPayment> getAllCreditPayments() {
	        return service.findAll();
	    }

	    @GetMapping("/{id}")
	    @Cacheable(key = "#id",value = "CreditPayment",unless = "#result.amount > 100")
	    public CreditPayment findByIdCreditPayment(@PathVariable int id) {
	        return service.findById(id);
	    }

	    @DeleteMapping("/{id}")
	    @CacheEvict(key = "#id",value = "CreditPayment")
	    public String removeCreditPayment(@PathVariable int id) {
	        return service.delete(id);
	    }
    
}
