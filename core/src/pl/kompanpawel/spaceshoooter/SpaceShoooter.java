package pl.kompanpawel.spaceshoooter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class SpaceShoooter extends Game {
	public SpriteBatch batch;
	public static AssetManager assetManager = new AssetManager();
	
	@Override
	public void create () {
        batch = new SpriteBatch();
		assetManager.load("uiskin.json", Skin.class);
		assetManager.load("xwing.png", Texture.class);
		assetManager.load("laserGreen03.png", Texture.class);
		assetManager.finishLoading();
		this.setScreen(new MainMenu(this));
	}

	@Override
	public void render () {
	    super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
