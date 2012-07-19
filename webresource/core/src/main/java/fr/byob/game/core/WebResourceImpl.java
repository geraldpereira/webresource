package fr.byob.game.core;

import java.util.HashMap;
import java.util.Map;

import playn.core.Platform;
import playn.core.util.Callback;

/**
 * Default WebResource implementation
 * 
 * @author Gérald Pereira
 * 
 */
public abstract class WebResourceImpl implements WebResource {

	private final static String CONTENT_TYPE = "Content-type";

	protected final Platform platform;
	protected String url;
	protected final Map<String, String> headers;
	protected String data;

	public WebResourceImpl(final Platform platform) {
		this.platform = platform;
		this.headers = new HashMap<String, String>();
		contentType(ContentType.APPLICATION_JSON);
	}

	@Override
	public WebResource contentType(ContentType type) {
		headers.put(CONTENT_TYPE, type.getType());
		return this;
	}

	@Override
	public WebResource url(String url) {
		this.url = url;
		return this;
	}

	@Override
	public WebResource data(String data) {
		this.data = data;
		return this;
	}

	@Override
	public WebResource header(String key, String value) {
		headers.put(key, value);
		return this;
	}

	protected void notifySuccess(final Callback<String> callback,
			final String result) {
		platform.invokeLater(new Runnable() {
			@Override
			public void run() {
				callback.onSuccess(result);
			}
		});
	}

	protected void notifyFailure(final Callback<String> callback,
			final Throwable cause) {
		platform.invokeLater(new Runnable() {
			@Override
			public void run() {
				callback.onFailure(cause);
			}
		});
	}

}
