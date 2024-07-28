package com.example.spring_mini.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Service;

import com.example.spring_mini.DTO.Request.RequestCreateUser;
import com.example.spring_mini.DTO.Request.RequestLogin;
import com.example.spring_mini.DTO.Request.RequestLoginToken;
import com.example.spring_mini.DTO.Response.ResponseData;
import com.example.spring_mini.DTO.Response.UserCreateResponse;
import com.example.spring_mini.Entity.UserEntity;
import com.example.spring_mini.Repository.UserRepository;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private String key = "6dk5jxch5pwcqdw3bagw5z7pd7g7zxu40n7ndeb8tpz8hld0j5dtkdzb6xv8jxb3";
    @Autowired
    private AuthenticationManager authenticationManager;



    public ResponseData createUser(RequestCreateUser requestCreateUser) {
        UserEntity userEntity = UserEntity.builder().email(requestCreateUser.getEmail())
                .password(bCryptPasswordEncoder.encode(requestCreateUser.getPassword()))
                .avatar(requestCreateUser.getAvatar()).build();

        UserEntity userNew = userRepository.save(userEntity);
        return new ResponseData(HttpStatus.OK, "tao user thanh cong", convertToUserCreateResponse(userNew));
    }

    public UserCreateResponse convertToUserCreateResponse(UserEntity userEntity) {
        return UserCreateResponse.builder().email(userEntity.getEmail()).avatar(userEntity.getAvatar()).build();
    }

    public String createToken(RequestLogin requestLogin) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(requestLogin.getEmail())
                .issueTime(new Date())
                .expirationTime(
                        new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("user_id", 1)
                .build();

        Payload payload = new Payload(claimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(key));
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return jwsObject.serialize();
    }

    public boolean verifyToken(RequestLoginToken requestLoginToken) {
        try {

            SignedJWT signedJWT = SignedJWT.parse(requestLoginToken.getToken());

            MACVerifier macVerifier = new MACVerifier(key.getBytes());
            var verified = signedJWT.verify(macVerifier);
            return verified;
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return false;
    }

    public UserEntity loginSecurity(RequestLogin requestLogin) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestLogin.getEmail(), requestLogin.getPassword()));
        return userRepository.findByEmail(requestLogin.getEmail());
    }



}
