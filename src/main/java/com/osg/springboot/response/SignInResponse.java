package com.osg.springboot.response;

public class SignInResponse {
    private String status;
    private String message;
    private String token;
    
    
	public SignInResponse(String status, String message, String token) {
		this.status = status;
		this.message = message;
		this.token = token;
	}
	public SignInResponse() {}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "SignInResponse [status=" + status + ", message=" + message + ", token=" + token + "]";
	}
	
}
