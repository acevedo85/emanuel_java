package com.unholysoftware.emanuel.controller;

import com.unholysoftware.emanuel.model.Bundle;
import com.unholysoftware.emanuel.repository.BundleRepository;
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
public class BundleController {

    @Autowired
    BundleRepository bundleRepository;

    @GetMapping("/bundles")
    public ResponseEntity<List<Bundle>> getAllBundles() {
        try {
            List<Bundle> bundles = new ArrayList<Bundle>();
            bundleRepository.findAll().forEach(bundles::add);
            if (bundles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(bundles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bundles/{id}")
    public ResponseEntity<Bundle> getBundleById(@PathParam("id") long id) {
        Optional<Bundle> bundleData = bundleRepository.findById(id);

        if (bundleData.isPresent()) {
            return new ResponseEntity<>(bundleData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/bundles")
    public ResponseEntity<Bundle> createBundle(@RequestBody Bundle bundle) {
        try {
            Bundle newBundle = bundleRepository
                    .save(new Bundle(
                            bundle.getName(),
                            bundle.getDescription(),
                            bundle.isWantsSecond(),
                            bundle.getCost()
                    ));
            return new ResponseEntity<>(newBundle, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/bundles/{id}")
    public ResponseEntity<Bundle> updateBundle(@PathVariable("id") Long id, @RequestBody Bundle bundle) {
        Optional<Bundle> bundleData = bundleRepository.findById(id);

        if (bundleData.isPresent()) {
            Bundle updatedBundle = bundleData.get();
            updatedBundle.setName(bundle.getName());
            updatedBundle.setDescription(bundle.getDescription());
            updatedBundle.setWantsSecond(bundle.isWantsSecond());
            updatedBundle.setCost(bundle.getCost());
            return new ResponseEntity<>(bundleRepository.save(updatedBundle), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/bundles/{id}")
    public ResponseEntity<HttpStatus> deleteBundle(@PathVariable("id") Long id) {
        try {
            bundleRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/bundles")
    public ResponseEntity<HttpStatus> deleteAllBundles() {
        try {
            bundleRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
