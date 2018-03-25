package pl.kompanpawel.spaceshoooter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
public class SpaceShoooter extends Game {
	public SpriteBatch batch;
	
	@Override
	public void create () {
        batch = new SpriteBatch();
        this.setScreen(new GameScreen(this));
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
