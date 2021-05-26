package com.osg.springboot.service;

import com.osg.springboot.model.User;
import com.osg.springboot.response.CustomResponse;
import com.osg.springboot.response.SignInResponse;
import com.osg.springboot.response.SmsResponse;

public interface AccountService {
    CustomResponse userSignUp(User user);

    SignInResponse userSignIn(User user);

    SmsResponse getMessageStatus(String id, String token);
}
