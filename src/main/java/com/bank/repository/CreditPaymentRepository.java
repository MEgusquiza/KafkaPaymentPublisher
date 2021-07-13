package com.bank.repository;


import com.bank.model.Purchase;


public interface CreditPaymentRepository {

   Purchase findByCardNumber(String nroOparation);
}
