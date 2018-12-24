package com.ftn.uns.payment_gateway.config;

public class JWTConstants {
    public static String HEADER = "Authorization";
    public static String PREFIX = "Bearer";
    public static Long EXPIRATION_DATE = 1000*60*30L;
    public static String SECRET = "SEP_S3cr3t";
}
