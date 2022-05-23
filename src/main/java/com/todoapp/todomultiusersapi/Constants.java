package com.todoapp.todomultiusersapi;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
public class Constants {
    private static String API_SECRET_KEY = "ARSmhLm4ydk53qqwFrcG8btVn2fMH1gDx90oFgFg5WfDAdizcUHSVIhdA85VjyJEgTorHcU3blJTHgQS";
    public static  final SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(API_SECRET_KEY));

    public static final  long TOKEN_VALIDITY = 2 * 68 * 68 * 1000;
}
