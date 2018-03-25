package pl.kompanpawel.spaceshoooter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class SpaceShoooter extends Game {
	public SpriteBatch batch;
	public AssetManager assetManager;
	
	@Override
	public void create () {
        batch = new SpriteBatch();
		assetManager = new AssetManager();
		assetManager.load("uiskin.json", Skin.class);
		assetManager.finishLoading();
		this.setScreen(new MainMenu(this));
	}
/*
	public void update() {
	    this.update();
	}
*/
	@Override
	public void render () {
	    super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
