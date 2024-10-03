package org.srm.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.srm.blog.model.T;
import org.srm.blog.model.User;
import org.srm.blog.repository.TRepository;
import org.srm.blog.repository.UserRepository;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/t")
public class TController {

    @Autowired
    private TRepository tRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<String> createT(@RequestBody T t, @RequestHeader("username") String username) {
        User author = userRepository.findByUsername(username).orElseThrow();
        t.setAuthorId(author.getId());
        t.setCreatedAt(LocalDateTime.now());
        tRepository.save(t);
        return ResponseEntity.ok("T created successfully");
    }

    @PostMapping("/{tId}/like")
    public ResponseEntity<String> likeT(@PathVariable String tId, @RequestHeader("username") String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        T t = tRepository.findById(tId).orElseThrow();
        t.getLikes().add(user.getId());
        tRepository.save(t);
        return ResponseEntity.ok("T liked");
    }
}

