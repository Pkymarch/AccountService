package com.osg.springboot.service.impl;

import com.osg.springboot.feign.SmsFeign;
import com.osg.springboot.model.User;
import com.osg.springboot.repo.UserRepository;
import com.osg.springboot.response.CustomResponse;
import com.osg.springboot.response.SignInResponse;
import com.osg.springboot.response.SmsResponse;
import com.osg.springboot.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SmsFeign smsFeignProxy;

    @Override
    public CustomResponse userSignUp(User user) {
        CustomResponse response = new CustomResponse();

        if (userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword()) == null) {
            String token = generateRandomToken();
            user.setToken(user.getUserName() + "_" + token);
            User save = userRepository.save(user);
            if (save != null) {
                response.setStatus("ok");
                response.setMessage("Account Created");
            } else {
                response.setStatus("error");
                response.setMessage("Account Creation Failed");
            }
        } else {
            response.setStatus("error");
            response.setMessage("Account Already Exist");
        }

        return response;
    }

    @Override
    public SignInResponse userSignIn(User user) {
        SignInResponse response = new SignInResponse();
        User existingUser = userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
        if (existingUser == null) {
            if (userRepository.findByUserName(user.getUserName()) != null) {
                response.setStatus("error");
                response.setMessage("Username Password Mismatch");
            } else {
                response.setStatus("error");
                response.setMessage("Account Doesn't Exist");
            }
        } else {
            response.setStatus("ok");
            response.setMessage("Sign In Successful");
            response.setToken(existingUser.getToken());
        }
        return response;
    }

    @Override
    public SmsResponse getMessageStatus(String id, String token) {
        SmsResponse smsResponse = new SmsResponse();
        String userName = token.split("_")[0];
        if (userRepository.findByUserName(userName) != null) {
            smsResponse = smsFeignProxy.smsStatus(id);
            if (smsResponse != null) {
                return smsResponse;
            } else {
                smsResponse.setStatus("error");
                smsResponse.setMessage("Wrong Message Id");
            }
        } else {
            smsResponse.setStatus("error");
            smsResponse.setMessage("User is Not Authorized to access");
        }
        return smsResponse;
    }

    static String generateRandomToken() {
        int tokenLength = 8;
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(tokenLength);
        for (int i = 0; i < tokenLength; i++) {
            int index = (int) (AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }
}
