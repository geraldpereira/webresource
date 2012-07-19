package fr.byob.game.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import playn.core.Game;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.PlayN;
import playn.core.util.Callback;

public class WebResourceTest implements Game {

	protected final WebResource webResource;

	public WebResourceTest(final WebResource webResource) {
		this.webResource = webResource;
	}

	@Override
	public void init() {
		// create and add background image layer
		Image bgImage = assets().getImage("images/bg.png");
		ImageLayer bgLayer = graphics().createImageLayer(bgImage);
		graphics().rootLayer().add(bgLayer);

		// webResource.url(
		// "http://gae-server-rest.appspot.com/api/v1.0/car/get/4001")
		// .get(new Callback<String>() {
		//
		// @Override
		// public void onSuccess(String result) {
		// PlayN.log().info(result);
		// }
		//
		// @Override
		// public void onFailure(Throwable cause) {
		// PlayN.log().info("Chier !", cause);
		// }
		// });

		webResource.url("http://gae-server-rest.appspot.com/api/v1.0/car/add")
				.data("{\"vin\":\"vinvinTest\",\"color\":20}")
				.post(new Callback<String>() {

					@Override
					public void onSuccess(String result) {
						PlayN.log().info(result);
					}

					@Override
					public void onFailure(Throwable cause) {
						PlayN.log().info("Chier !", cause);
					}
				});



// PlayN.net().get(
		// "http://gae-server-rest.appspot.com/api/v1.0/car/get/4001",
		// new Callback<String>() {
		//
		// @Override
		// public void onSuccess(String result) {
		// PlayN.log().info(result);
		// }
		//
		// @Override
		// public void onFailure(Throwable cause) {
		// PlayN.log().info("Chier !", cause);
		// }
		// });

	}

	@Override
	public void paint(float alpha) {
		// the background automatically paints itself, so no need to do anything
		// here!
	}

	@Override
	public void update(float delta) {
	}

	@Override
	public int updateRate() {
		return 25;
	}
}
