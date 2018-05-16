package pl.kompanpawel.spaceshoooter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.sun.org.apache.xpath.internal.operations.Or;

public class SpaceShoooter extends Game {
	public SpriteBatch batch;
    BitmapFont font;
	public static AssetManager assetManager = new AssetManager();
	private static OrthographicCamera camera = new OrthographicCamera();
	
	@Override
	public void create () {
        batch = new SpriteBatch();
		assetManager.load("uiskin.json", Skin.class);
		assetManager.load("xwing.png", Texture.class);
		assetManager.load("laserRed13.png", Texture.class);
		assetManager.load("laserGreen03.png", Texture.class);
		assetManager.load("rsz_tie_fighter.png", Texture.class);
		assetManager.finishLoading();
        font = new BitmapFont(Gdx.files.internal("default.fnt"), Gdx.files.internal("default.png"), false);
		this.setScreen(new MainMenu(this));
	}
    public static float getWidth() {
	    return camera.viewportWidth;
    }

    public static float getHeight() {
	    return camera.viewportHeight;
    }

    public static OrthographicCamera getCamera() {
	    return camera;
    }

	@Override
	public void render () {
	    super.render();
	    batch.begin();
	    //font.draw(batch, "FPS: "+ Gdx.graphics.getFramesPerSecond(), 5, 20);
	    batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
