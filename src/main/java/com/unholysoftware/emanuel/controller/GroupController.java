package com.unholysoftware.emanuel.controller;

import com.unholysoftware.emanuel.model.Group;
import com.unholysoftware.emanuel.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class GroupController {

    @Autowired
    GroupRepository groupRepository;

    @GetMapping("/groups")
    public ResponseEntity<List<Group>> getAllGroups() {
        try {
            List<Group> groups = new ArrayList<>();
            groupRepository.findAll().forEach(groups::add);
            if (groups.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(groups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/groups/{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable("id") Long id) {
        Optional<Group> groupData = groupRepository.findById(id);
        if (groupData.isPresent()) {
            return new ResponseEntity<>(groupData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/groups")
    public ResponseEntity<Group> createGroup(@RequestBody Group group) {
        try {
            Group newGroup = groupRepository
                    .save(new Group(
                            group.getSchool(),
                            group.getCareer(),
                            group.getName()
                    ));
            return new ResponseEntity<>(newGroup, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/groups/{id}")
    public ResponseEntity<Group> updateGroup(@PathVariable("id") Long id, @RequestBody Group group) {
        Optional<Group> groupData = groupRepository.findById(id);
        if (groupData.isPresent()) {
            Group updatedGroup = groupData.get();
            updatedGroup.setSchool(group.getSchool());
            updatedGroup.setCareer(group.getCareer());
            updatedGroup.setName(group.getName());
            return  new ResponseEntity<>(groupRepository.save(updatedGroup), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/groups/{id}")
    public ResponseEntity<HttpStatus> deleteGroup(@PathVariable("id") Long id) {
        try {
            groupRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/groups")
    public ResponseEntity<HttpStatus> deleteAllGroups() {
        try {
            groupRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
