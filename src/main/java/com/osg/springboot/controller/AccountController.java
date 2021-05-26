package com.osg.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.osg.springboot.model.User;
import com.osg.springboot.response.CustomResponse;
import com.osg.springboot.response.SignInResponse;
import com.osg.springboot.response.SmsResponse;
import com.osg.springboot.service.AccountService;

@RequestMapping("/account")
@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/signup")
    public CustomResponse signUp(@RequestBody User user) {
    	System.out.println("Prakash");
        CustomResponse response = accountService.userSignUp(user);
        return response;
    }

    @PostMapping("/signin")
    public SignInResponse signIn(@RequestBody User user) {
        SignInResponse response = accountService.userSignIn(user);
        return response;
    }

    @PostMapping("/status")
    public SmsResponse messageStatus(@RequestParam("id") String id, @RequestHeader("token") String token) {
        return accountService.getMessageStatus(id, token);
    }
}
