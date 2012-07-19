package fr.byob.game.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;
import fr.byob.game.core.WebResourceTest;

public class WebResourceTestJava {

  public static void main(String[] args) {
    JavaPlatform platform = JavaPlatform.register();
    platform.assets().setPathPrefix("fr/byob/game/resources");
		PlayN.run(new WebResourceTest(new JavaWebResource(platform)));
  }
}
