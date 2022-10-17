package com.unholysoftware.emanuel.controller;

import com.unholysoftware.emanuel.model.Request;
import com.unholysoftware.emanuel.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RequestController {

    @Autowired
    RequestRepository requestRepository;

    @GetMapping("/requests")
    public ResponseEntity<List<Request>> getAllRequests() {
        try {
            List<Request> requests = new ArrayList<>();
            requestRepository.findAll().forEach(requests::add);
            if (requests.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(requests, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/requests/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable("id") Long id) {
        Optional<Request> requestData = requestRepository.findById(id);
        if (requestData.isPresent()) {
            return new ResponseEntity<>(requestData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/requests")
    public ResponseEntity<Request> createRequest(@RequestBody Request request) {
        try {
            Request newRequest = requestRepository
                    .save(new Request(
                            request.getBundle(),
                            request.getModel(),
                            request.getBaseColor(),
                            request.getPlaqueColor(),
                            request.getPlaqueModel(),
                            request.getPicture(),
                            request.getTextNumber(),
                            request.getDedication(),
                            request.getAttachment(),
                            request.getCost(),
                            request.getContract()
                    ));
            return new ResponseEntity<>(newRequest, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/requests/{id}")
    public ResponseEntity<Request> updateRequest(@PathVariable("id") Long id, @RequestBody Request request) {
        Optional<Request> requestData = requestRepository.findById(id);
        if (requestData.isPresent()) {
            Request updatedRequest = requestData.get();
            updatedRequest.setAttachment(request.getAttachment());
            updatedRequest.setContract(request.getContract());
            updatedRequest.setBundle(request.getBundle());
            updatedRequest.setCost(request.getCost());
            updatedRequest.setDedication(request.getDedication());
            updatedRequest.setBaseColor(request.getBaseColor());
            updatedRequest.setPlaqueColor(request.getPlaqueColor());
            updatedRequest.setPlaqueModel(request.getPlaqueModel());
            updatedRequest.setPicture(request.getPicture());
            updatedRequest.setTextNumber(request.getTextNumber());
            updatedRequest.setModel(request.getModel());
            return  new ResponseEntity<>(requestRepository.save(updatedRequest), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/requests/{id}")
    public ResponseEntity<HttpStatus> deleteRequest(@PathVariable("id") Long id) {
        try {
            requestRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/requests")
    public ResponseEntity<HttpStatus> deleteAllRequests() {
        try {
            requestRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
