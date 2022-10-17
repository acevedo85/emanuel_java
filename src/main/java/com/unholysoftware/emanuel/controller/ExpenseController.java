package com.unholysoftware.emanuel.controller;

import com.unholysoftware.emanuel.model.Expense;
import com.unholysoftware.emanuel.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ExpenseController {

    @Autowired
    ExpenseRepository expenseRepository;

    @GetMapping("/expenses")
    public ResponseEntity<List<Expense>> getAllExpenses() {
        try {
            List<Expense> expenses = new ArrayList<>();
            expenseRepository.findAll().forEach(expenses::add);
            if (expenses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(expenses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/expenses/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathParam("id") long id) {
        Optional<Expense> expenseData = expenseRepository.findById(id);

        if (expenseData.isPresent()) {
            return new ResponseEntity<>(expenseData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/expenses")
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        try {
            Expense newExpense = expenseRepository
                    .save( new Expense(
                            expense.getDate(),
                            expense.getDescription(),
                            expense.getAmount(),
                            expense.getUser()
            ));
            return new ResponseEntity<>(newExpense, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/expenses/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable("id") Long id, @RequestBody Expense expense) {
        Optional<Expense> expenseData = expenseRepository.findById(id);

        if (expenseData.isPresent()) {
            Expense updatedExpense = expenseData.get();
            updatedExpense.setDate(expense.getDate());
            updatedExpense.setDescription(expense.getDescription());
            updatedExpense.setAmount(expense.getAmount());
            updatedExpense.setUser(expense.getUser());
            return new ResponseEntity<>(expenseRepository.save(updatedExpense), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/expenses/{id}")
    public ResponseEntity<HttpStatus> deleteExpense(@PathVariable("id") Long id) {
        try {
            expenseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/expenses")
    public ResponseEntity<HttpStatus> deleteAllExpenses() {
        try {
            expenseRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
