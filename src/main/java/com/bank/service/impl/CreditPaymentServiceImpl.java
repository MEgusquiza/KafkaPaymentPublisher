package com.bank.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import com.bank.model.CreditPayment;
import com.bank.repository.CreditPaymentRepository;
import com.bank.service.CreditPaymentService;


@Repository
public class CreditPaymentServiceImpl implements CreditPaymentService{
      
 public static final String HASH_KEY = "CreditPayment";       
       
         @Autowired
         private RedisTemplate template;
         
      //   @Autowired
      //   private CreditPaymentRepository repository;
         
 	     @Override
	     public CreditPayment findById(int id) {
	     System.out.println("called findPaymenttById() from DB");
	     return (CreditPayment) template.opsForHash().get(HASH_KEY,id);
         }

	    @Override
	    public List<CreditPayment> findAll() {
	      return template.opsForHash().values(HASH_KEY);
	    }

	    @Override
	    public CreditPayment update(CreditPayment creditPayment) {
	     return save(creditPayment);
	    }

	    @Override
	    public CreditPayment save(CreditPayment creditPayment) {
	      template.opsForHash().put(HASH_KEY,creditPayment.getId(),creditPayment);
	        return creditPayment;
	    }
	    
	    @Override
	    public String delete(int id) {
	      template.opsForHash().delete(HASH_KEY,id);
	        return "payment removed !!";
	    }

}
