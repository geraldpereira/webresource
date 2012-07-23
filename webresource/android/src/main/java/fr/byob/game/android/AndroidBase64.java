package fr.byob.game.android;

import fr.byob.game.core.Base64;

public class AndroidBase64 implements Base64 {

	@Override
	public String encode(String value) {
		return android.util.Base64.encodeToString(value.getBytes(),
				android.util.Base64.DEFAULT);
	}

	@Override
	public String decode(String value) {
		return new String(android.util.Base64.decode(value,
				android.util.Base64.DEFAULT));
	}

}
