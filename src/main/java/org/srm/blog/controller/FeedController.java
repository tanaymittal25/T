package org.srm.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.srm.blog.model.T;
import org.srm.blog.model.User;
import org.srm.blog.repository.TRepository;
import org.srm.blog.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/feed")
public class FeedController {

    @Autowired
    private TRepository tRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<T> getFeed(@RequestHeader("username") String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        List<T> feed = new ArrayList<>();
        for (String followingId : user.getFollowing()) {
            feed.addAll(tRepository.findAllByAuthorId(followingId));
        }
        return feed;
    }
}