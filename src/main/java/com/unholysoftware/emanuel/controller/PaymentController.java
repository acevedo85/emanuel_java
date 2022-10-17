package com.unholysoftware.emanuel.controller;

import com.unholysoftware.emanuel.model.Payment;
import com.unholysoftware.emanuel.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    PaymentRepository paymentRepository;

    @GetMapping("payments")
    public ResponseEntity<List<Payment>> getAllPayments() {
        try {
            List<Payment> payments = new ArrayList<>();
            paymentRepository.findAll().forEach(payments::add);
            if (payments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(payments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("payments/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable("id") Long id) {
        Optional<Payment> paymentData = paymentRepository.findById(id);
        if (paymentData.isPresent()) {
            return new ResponseEntity<>(paymentData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("payments")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        try {
            Payment newPayment = paymentRepository
                    .save(new Payment(
                            payment.getAmount(),
                            payment.getPaymentDate(),
                            payment.getContract()
                    ));
            return new ResponseEntity<>(newPayment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("payments/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable("id") Long id, @RequestBody Payment payment) {
        Optional<Payment> paymentData = paymentRepository.findById(id);
        if (paymentData.isPresent()) {
            Payment updatedPayment = paymentData.get();
            updatedPayment.setPaymentDate(payment.getPaymentDate());
            updatedPayment.setAmount(payment.getAmount());
            updatedPayment.setContract(payment.getContract());
            return  new ResponseEntity<>(paymentRepository.save(updatedPayment), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("payments/{id}")
    public ResponseEntity<HttpStatus> deletePayment(@PathVariable("id") Long id) {
        try {
            paymentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("payments")
    public ResponseEntity<HttpStatus> deleteAllPayments() {
        try {
            paymentRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
