package pl.kompanpawel.spaceshoooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class GameScreen implements Screen {

    Texture xWing;
    Texture background;
    Texture laserTexture;

    public static final float speed = 200;
    public static final float laserSpeed = 500;

    float screenWidth = 0;
    float screenHeight = 0;

    Vector2 shipLocation = new Vector2(0,0);


    ArrayList<Laser> laserManager = new ArrayList<Laser>();

    SpaceShoooter game;

    public GameScreen(SpaceShoooter game) {
        this.game = game;
    }

    @Override
    public void show() {
        screenWidth = Gdx.graphics.getWidth();
        screenHeight= Gdx.graphics.getHeight();

        xWing = new Texture("xwing.png");
        background = new Texture("space.gif");
        laserTexture = new Texture("laserGreen03.png");

        shipLocation = new Vector2(20,screenHeight / 2);
    }

    @Override
    public void render(float delta) {
        if((Gdx.input.isKeyPressed(Input.Keys.UP)) && (shipLocation.y < (screenHeight - xWing.getHeight()-5))) {
            shipLocation.y += speed * Gdx.graphics.getDeltaTime();
        }

        if((Gdx.input.isKeyPressed(Input.Keys.DOWN)) && (shipLocation.y > 5)) {
            shipLocation.y -= speed * Gdx.graphics.getDeltaTime();
        }

        if((Gdx.input.isKeyPressed(Input.Keys.LEFT)) && (shipLocation.x > 5)) {
            shipLocation.x -= speed * Gdx.graphics.getDeltaTime();
        }

        if((Gdx.input.isKeyPressed(Input.Keys.RIGHT)) && (shipLocation.x < (screenWidth - xWing.getWidth()-5))) {
            shipLocation.x += speed * Gdx.graphics.getDeltaTime();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            Laser laser = new Laser(shipLocation, new Vector2(laserSpeed * Gdx.graphics.getDeltaTime(),0));
            laserManager.add(laser);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            /* exit to menu */
        }
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(background,0,0);
        game.batch.draw(xWing,shipLocation.x, shipLocation.y);

        int count = 0;
        while(count < laserManager.size()) {
            Laser currentLaser = laserManager.get(count);
            currentLaser.update();
            count++;
            if(count % 2 == 0) {
                game.batch.draw(laserTexture, currentLaser.laserLocation1.x, currentLaser.laserLocation1.y);
            }
            else {
                game.batch.draw(laserTexture, currentLaser.laserLocation1.x, (currentLaser.laserLocation1.y + xWing.getHeight() - 6));
            }
        }
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
