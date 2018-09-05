package com.chrisshayan.example.springretry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {


    @Autowired
    private CommandAndRetry commandAndRetry;

    @GetMapping
    public void exec() throws TypeOneException {
        commandAndRetry.retryWhenException();
    }
}
