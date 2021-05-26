
package com.osg.springboot.response;


public class CustomResponse {
    private String status;
    private String message;
    
    
    
	public CustomResponse(String status, String message) {
		this.status = status;
		this.message = message;
	}
	public CustomResponse() {}
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
	@Override
	public String toString() {
		return "CustomResponse [status=" + status + ", message=" + message + "]";
	}
	
}
