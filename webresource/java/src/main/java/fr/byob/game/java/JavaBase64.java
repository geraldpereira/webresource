package fr.byob.game.java;

import fr.byob.game.core.Base64;


public class JavaBase64 implements Base64 {

	@Override
	public String encode(String value) {
		return new String(com.sun.jersey.core.util.Base64.encode(value));
	}

	@Override
	public String decode(String value) {
		return com.sun.jersey.core.util.Base64.base64Decode(value);
	}

}
