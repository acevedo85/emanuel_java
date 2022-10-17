package com.unholysoftware.emanuel.controller;

import com.unholysoftware.emanuel.model.Ring;
import com.unholysoftware.emanuel.repository.RingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RingController {

    @Autowired
    RingRepository ringRepository;

    @GetMapping("/rings")
    public ResponseEntity<List<Ring>> getAllRings() {
        try {
            List<Ring> rings = new ArrayList<>();
            ringRepository.findAll().forEach(rings::add);
            if (rings.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(rings, HttpStatus.OK);
            }
        } catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rings/{id}")
    public ResponseEntity<Ring> getRingById(@PathVariable("id") Long id) {
        Optional<Ring> ringData = ringRepository.findById(id);
        if (ringData.isPresent()) {
            return new ResponseEntity<>(ringData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/rings")
    public ResponseEntity<Ring> createRing(@RequestBody Ring ring) {
        try {
            Ring newRing = ringRepository
                    .save(new Ring(
                            ring.getType(),
                            ring.getModel(),
                            ring.getWeight(),
                            ring.getSize(),
                            ring.getCost(),
                            ring.getContract()
                    ));
            return new ResponseEntity<>(newRing, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/rings/{id}")
    public ResponseEntity<Ring> updateRing(@PathVariable("id") Long id, @RequestBody Ring ring) {
        Optional<Ring> ringData = ringRepository.findById(id);
        if (ringData.isPresent()) {
            Ring updatedRing = ringData.get();
            updatedRing.setType(ring.getType());
            updatedRing.setModel(ring.getModel());
            updatedRing.setWeight(ring.getWeight());
            updatedRing.setSize(ring.getSize());
            updatedRing.setCost(ring.getCost());
            updatedRing.setContract(ring.getContract());
            return new ResponseEntity<>(ringRepository.save(updatedRing), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("rings/{id}")
    public ResponseEntity<HttpStatus> deleteRing(@PathVariable("id") Long id) {
        try {
            ringRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/rings")
    public ResponseEntity<HttpStatus> deleteAllRequests() {
        try {
            ringRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
