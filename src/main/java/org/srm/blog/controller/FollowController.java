package org.srm.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.srm.blog.model.User;
import org.srm.blog.repository.UserRepository;

@RestController
@RequestMapping("/follow")
public class FollowController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/{username}")
    public ResponseEntity<String> followUser(@PathVariable String username, @RequestHeader("follower") String followerUsername) {
        User currentUser = userRepository.findByUsername(followerUsername).orElseThrow();
        User userToFollow = userRepository.findByUsername(username).orElseThrow();
        currentUser.getFollowing().add(userToFollow.getId());
        userToFollow.getFollowers().add(currentUser.getId());
        userRepository.save(currentUser);
        userRepository.save(userToFollow);
        return ResponseEntity.ok("Following " + username);
    }
}
