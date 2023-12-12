package com.lolz404.server;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class Endpoints {
    
    private Map<String, User> users;
    
    @Value("${jwt.secret}")
    private String secret;

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    public Endpoints() {
        this.users = new HashMap<>();
        var user = new User();
        user.setPasswordHash(DigestUtils.md5DigestAsHex("test1234".getBytes()));
        user.setEmail("coury.richards@gmail.com");
        user.setPhone("3305694274");
        user.setToken("985139847");
        user.setLastName("Richards");
        user.setFirstName("Coury");


        this.users.put("couryrr", user);
    }

    @PostMapping("auth")
    public String authenticate(@RequestBody AuthRequest request){
        var userName = request.getUserName();
        if(users.containsKey(userName)){
            var user = users.get(userName);

            if(user.getPasswordHash().equals(DigestUtils.md5DigestAsHex(request.getPassword().getBytes()))){
                return user.getToken();
            }
        }
        return "fail";
    }

    @PostMapping("jwt")
    public String jwt(@RequestBody JwtRequest request) {
        var token = request.getToken();
        var userName = request.getUserName();

        var user = users.get(userName); 

        var claims = new HashMap<String, Object>();
        var subject = "test";

        if(user.getToken().equals(token)) {
            var jwt = Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret)
                .compact();
            
            return jwt;

        }

        return "fail";

    }

}
