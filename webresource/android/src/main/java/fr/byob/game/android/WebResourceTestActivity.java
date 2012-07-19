package fr.byob.game.android;

import playn.android.GameActivity;
import playn.core.PlayN;
import fr.byob.game.core.WebResourceTest;

public class WebResourceTestActivity extends GameActivity {

  @Override
  public void main(){
    platform().assets().setPathPrefix("fr/byob/game/resources");
		PlayN.run(new WebResourceTest(new AndroidWebResource(platform())));
  }
}
