package com.unholysoftware.emanuel.controller;

import com.unholysoftware.emanuel.model.Contract;
import com.unholysoftware.emanuel.repository.ContractRepository;
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
public class ContractController {

    @Autowired
    ContractRepository contractRepository;

    @GetMapping("/contracts")
    public ResponseEntity<List<Contract>> getAllContracts() {
        try {
            List<Contract> contracts = new ArrayList<>();
            contractRepository.findAll().forEach(contracts::add);
            if (contracts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(contracts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/contracts/{id}")
    public ResponseEntity<Contract> getContractById(@PathParam("id") long id) {
        Optional<Contract> contractData = contractRepository.findById(id);

        if (contractData.isPresent()) {
            return new ResponseEntity<>(contractData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/contracts")
    public ResponseEntity<Contract> createContract(@RequestBody Contract contract) {
        try {
            Contract newContract = contractRepository
                    .save(new Contract(
                            contract.getCreatedAt(),
                            contract.getDeliveredAt(),
                            contract.getStatus(),
                            contract.getCostumer()
                    ));
            return new ResponseEntity<>(newContract, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/contracts/{id}")
    public ResponseEntity<Contract> updateContract(@PathVariable Long id, @RequestBody Contract contract) {
        Optional<Contract> contractData = contractRepository.findById(id);

        if (contractData.isPresent()) {
            Contract updatedContract = contractData.get();
            updatedContract.setDeliveredAt(contract.getDeliveredAt());
            updatedContract.setCostumer(contract.getCostumer());
            updatedContract.setCreatedAt(contract.getCreatedAt());
            updatedContract.setStatus(contract.getStatus());
            return new ResponseEntity<>(contractRepository.save(updatedContract), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/contracts/{id}")
    public ResponseEntity<HttpStatus> deleteContract(@PathVariable("id") Long id) {
        try {
            contractRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/contracts")
    public ResponseEntity<HttpStatus> deleteAllContracts() {
        try {
            contractRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
