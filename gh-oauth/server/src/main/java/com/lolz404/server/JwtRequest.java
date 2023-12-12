package com.lolz404.server;

public class JwtRequest {
    
    private String userName;
    private String token;
    
    public String getToken() { return token; }
    public void getToken(String token) { this.token = token; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

}
