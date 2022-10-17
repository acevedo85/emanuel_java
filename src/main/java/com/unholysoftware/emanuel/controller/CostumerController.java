package com.unholysoftware.emanuel.controller;

import com.unholysoftware.emanuel.model.Costumer;
import com.unholysoftware.emanuel.repository.CostumerRepository;
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
public class CostumerController {

    @Autowired
    CostumerRepository costumerRepository;

    @GetMapping("/costumers")
    public ResponseEntity<List<Costumer>> getAllCostumers() {
        try {
            List<Costumer> costumers = new ArrayList<>();
            costumerRepository.findAll().forEach(costumers::add);
            if (costumers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(costumers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/costumers/{id}")
    public ResponseEntity<Costumer> getCostumerById(@PathParam("id") long id) {
        Optional<Costumer> costumerData = costumerRepository.findById(id);

        if (costumerData.isPresent()) {
            return new ResponseEntity<>(costumerData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/costumers")
    public ResponseEntity<Costumer> createCostumer(@RequestBody Costumer costumer) {
        try {
            Costumer newCostumer = costumerRepository
                    .save(new Costumer(
                            costumer.getName(),
                            costumer.getMiddleName(),
                            costumer.getLastName(),
                            costumer.getAddress(),
                            costumer.getGeneration(),
                            costumer.getPhone(),
                            costumer.getEmail(),
                            costumer.getGroups()
                    ));
            return new ResponseEntity<>(newCostumer, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/costumers/{id}")
    public ResponseEntity<Costumer> updateCostumer(@PathVariable("id") Long id, @RequestBody Costumer costumer) {
        Optional<Costumer> costumerData = costumerRepository.findById(id);

        if (costumerData.isPresent()) {
            Costumer updatedCostumer = costumerData.get();
            updatedCostumer.setName(costumer.getName());
            updatedCostumer.setMiddleName(costumer.getMiddleName());
            updatedCostumer.setLastName(costumer.getLastName());
            updatedCostumer.setAddress(costumer.getAddress());
            updatedCostumer.setGeneration(costumer.getGeneration());
            updatedCostumer.setPhone(costumer.getPhone());
            updatedCostumer.setEmail(costumer.getEmail());
            costumer.setGroups(costumer.getGroups());
            return new ResponseEntity<>(costumerRepository.save(updatedCostumer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/costumers/{id}")
    public ResponseEntity<HttpStatus> deleteCostumer(@PathVariable("id") Long id) {
        try {
            costumerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/costumers")
    public ResponseEntity<HttpStatus> deleteAllCostumers() {
        try {
            costumerRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
