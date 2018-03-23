package pl.kompanpawel.spaceshoooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class SpaceShoooter extends ApplicationAdapter {
	SpriteBatch batch;
	Texture xWing;
	Texture background;
	Texture laserTexture;

	float screenWidth = 0;
	float screenHeight = 0;

	Vector2 shipLocation = new Vector2(0,0);


	ArrayList<Laser> laserManager = new ArrayList<Laser>();
	
	@Override
	public void create () {
		screenWidth = Gdx.graphics.getWidth();
		screenHeight= Gdx.graphics.getHeight();

		batch = new SpriteBatch();
		xWing = new Texture("xwing.png");
		background = new Texture("space.gif");
		laserTexture = new Texture("laserGreen03.png");

		shipLocation = new Vector2(20,screenHeight / 2);
	}

	public void update() {
		if((Gdx.input.isKeyPressed(Keys.UP)) && (shipLocation.y < (screenHeight - xWing.getHeight()-5))) {
			shipLocation.y += 5;
		}

		if((Gdx.input.isKeyPressed(Keys.DOWN)) && (shipLocation.y > 5)) {
			shipLocation.y -= 5;
		}

		if((Gdx.input.isKeyPressed(Keys.LEFT)) && (shipLocation.x > 5)) {
			shipLocation.x -= 5;
		}

		if((Gdx.input.isKeyPressed(Keys.RIGHT)) && (shipLocation.x < (screenWidth - xWing.getWidth()-5))) {
			shipLocation.x += 5;
		}

		if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			Laser laser = new Laser(shipLocation, new Vector2(10,0));
			laserManager.add(laser);
		}

		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			/* exit to menu */
		}
	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(1,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(background,0,0);
		batch.draw(xWing,shipLocation.x, shipLocation.y);

		int count = 0;
		while(count < laserManager.size()) {
			Laser currentLaser = laserManager.get(count);
			currentLaser.update();
			count++;
			if(count % 2 == 0) {
				batch.draw(laserTexture, currentLaser.laserLocation1.x, currentLaser.laserLocation1.y);
			}
			else {
				batch.draw(laserTexture, currentLaser.laserLocation1.x, (currentLaser.laserLocation1.y + xWing.getHeight() - 6));
			}
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
