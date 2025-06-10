package com.kwilkinson.springcart.controllers;

import com.kwilkinson.springcart.entities.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @RequestMapping("/hello")
    public Message sayHello() {
    return new Message("Hello World");
    }
}
