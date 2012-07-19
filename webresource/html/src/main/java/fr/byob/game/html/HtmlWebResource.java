package fr.byob.game.html;

import java.util.Map.Entry;

import playn.core.Platform;
import playn.core.PlayN;
import playn.core.util.Callback;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestBuilder.Method;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;

import fr.byob.game.core.WebResourceImpl;

/**
 * 
 * Note : To call an HTTP url from localhost (HTML application deployed for
 * test), you must add the following headers in the response :
 * Access-Control-Allow-Origin : * Access-Control-Allow-Methods : PUT, GET,
 * POST, DELETE, OPTIONS Access-Control-Allow-Headers : Content-Type
 * 
 * @author Gérald Pereira
 * 
 */
public class HtmlWebResource extends WebResourceImpl {

	public HtmlWebResource(Platform platform) {
		super(platform);
	}

	@Override
	public void get(Callback<String> callback) {
		call(RequestBuilder.GET, callback);
	}

	@Override
	public void post(Callback<String> callback) {
		call(RequestBuilder.POST, callback);
	}

	public void call(final Method methodType, final Callback<String> callback) {
		try {
			PlayN.log().info(methodType.toString());

			RequestBuilder builder = new RequestBuilder(methodType, url);
			for (Entry<String, String> header : headers.entrySet()) {
				builder.setHeader(header.getKey(), header.getValue());
			}

			builder.setCallback(new RequestCallback() {

				@Override
				public void onResponseReceived(Request request,
						Response response) {
					callback.onSuccess(response.getText());
				}

				@Override
				public void onError(Request request, Throwable exception) {
					callback.onFailure(exception);
				}
			});
			
			if (data != null) {
				builder.setRequestData(data);
			}

			builder.send();
		} catch (Exception e) {
			callback.onFailure(e);
		}
	}

}
