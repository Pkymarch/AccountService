package com.osg.springboot.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.osg.springboot.response.SmsResponse;

@FeignClient(name = "sms-service", url = "localhost:3003")
public interface SmsFeign {
    @PostMapping("/sms/status")
    public SmsResponse smsStatus(@RequestParam("id") String messageId);
}
