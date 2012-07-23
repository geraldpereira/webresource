package fr.byob.game.html;

import playn.core.PlayN;
import playn.html.HtmlGame;
import playn.html.HtmlPlatform;
import fr.byob.game.core.WebResourceTest;

public class WebResourceTestHtml extends HtmlGame {

  @Override
  public void start() {
    HtmlPlatform platform = HtmlPlatform.register();
    platform.assets().setPathPrefix("webresource/");
		PlayN.run(new WebResourceTest(new HtmlWebResource(platform),
				new HtmlBase64()));
  }
}
