package com.sparta.baedallegend.domains.auth.utils.jwt;

import static java.nio.charset.StandardCharsets.UTF_8;

import io.jsonwebtoken.security.Keys;
import java.util.Base64;
import javax.crypto.SecretKey;

public class JwtUtils {

	public static final String ID = "id";
	public static final String ROLE = "role";
	public static final String EMAIL = "email";

	public static SecretKey generateSigningKey(String plainSecretKey) {
		byte[] base64SecretKey = encodeToBase64(plainSecretKey);
		return Keys.hmacShaKeyFor(base64SecretKey);
	}

	private static byte[] encodeToBase64(String base64SecretKey) {
		return Base64.getEncoder()
			.withoutPadding()
			.encode(base64SecretKey.getBytes(UTF_8));
	}

}
