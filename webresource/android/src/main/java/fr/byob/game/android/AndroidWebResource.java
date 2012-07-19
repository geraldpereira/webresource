package fr.byob.game.android;

import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import playn.core.Platform;
import playn.core.util.Callback;
import fr.byob.game.core.WebResourceImpl;

public class AndroidWebResource extends WebResourceImpl {

	public AndroidWebResource(Platform platform) {
		super(platform);
	}

	@Override
	public void get(final Callback<String> callback) {
		new Thread("AndroidWebResource.call (" + url + ")") {
			@Override
			public void run() {
				HttpClient httpclient = new DefaultHttpClient();
				HttpRequestBase req = new HttpGet(url);
				try {
					HttpResponse response = httpclient.execute(req);
					notifySuccess(callback,
							EntityUtils.toString(response.getEntity()));
				} catch (Exception e) {
					notifyFailure(callback, e);
				}
			}
		}.start();

	}

	@Override
	public void post(final Callback<String> callback) {
		new Thread("AndroidWebResource.call (" + url + ")") {
			@Override
			public void run() {
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost req = new HttpPost(url);
				if (data != null) {
					try {
						req.setEntity(new StringEntity(data));
					} catch (UnsupportedEncodingException e) {
						notifyFailure(callback, e);
					}
				}
				try {
					HttpResponse response = httpclient.execute(req);
					notifySuccess(callback,
							EntityUtils.toString(response.getEntity()));
				} catch (Exception e) {
					notifyFailure(callback, e);
				}
			}
		}.start();

	}

}
