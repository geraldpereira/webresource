package fr.byob.game.java;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map.Entry;

import playn.core.Platform;
import playn.core.util.Callback;
import fr.byob.game.core.WebResourceImpl;

public class JavaWebResource extends WebResourceImpl {

	private static final int BUF_SIZE = 4096;

	public JavaWebResource(final Platform platform) {
		super(platform);
	}

	@Override
	public void get(final Callback<String> callback) {
		new Thread("JavaWebResource.get(" + url + ")") {
			@Override
			public void run() {
				try {
					// open url connection
					URL url = new URL(JavaWebResource.this.url);
					HttpURLConnection con = (HttpURLConnection) url
							.openConnection();

					// set up url connection to get retrieve information back
					con.setRequestMethod("GET");
					con.setDoInput(true);

					for (Entry<String, String> header : headers.entrySet()) {
						con.setRequestProperty(header.getKey(),
								header.getValue());
					}
					String result = readFully(new InputStreamReader(
							con.getInputStream()));
					con.disconnect();
					notifySuccess(callback, result);
				} catch (MalformedURLException e) {
					notifyFailure(callback, e);
				} catch (IOException e) {
					notifyFailure(callback, e);
				}
			}
		}.start();

	}

	@Override
	public void post(final Callback<String> callback) {
		new Thread("JavaWebResource.post(" + url + ")") {
			@Override
			public void run() {
				try {
					URL url = new URL(JavaWebResource.this.url);
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.setRequestMethod("POST");
					conn.setDoOutput(true);
					conn.setDoInput(true);
					conn.setAllowUserInteraction(false);

					for (Entry<String, String> header : headers.entrySet()) {
						conn.setRequestProperty(header.getKey(),
								header.getValue());
					}

					conn.connect();
					if (data != null) {
						conn.getOutputStream().write(data.getBytes("UTF-8"));
						conn.getOutputStream().close();
					}
					String result = readFully(new InputStreamReader(
							conn.getInputStream()));
					conn.disconnect();
					notifySuccess(callback, result);
				} catch (MalformedURLException e) {
					notifyFailure(callback, e);
				} catch (IOException e) {
					notifyFailure(callback, e);
				}
			}
		}.start();
	}

	private String readFully(Reader reader) throws IOException {
		StringBuffer result = new StringBuffer();
		char[] buf = new char[BUF_SIZE];
		int len = 0;
		while (-1 != (len = reader.read(buf))) {
			result.append(buf, 0, len);
		}
		return result.toString();
	}

}
