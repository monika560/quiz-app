package com.model;

public class Response {

	private Integer id;
	private String userResponse;
	public Response(Integer id, String userUesponse) {
		super();
		this.id = id;
		this.userResponse = userUesponse;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserUesponse() {
		return userResponse;
	}
	public void setUserResponse(String userResponse) {
		this.userResponse = userResponse;
	}
	
	
	
}
