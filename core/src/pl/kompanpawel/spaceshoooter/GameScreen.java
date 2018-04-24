package pl.kompanpawel.spaceshoooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class GameScreen implements Screen {

    private Texture background;
    private Texture laserTexture;


    public static final float laserSpeed = 500;

    private float screenWidth = 0;
    private float screenHeight = 0;

    private Vector2 shipLocation = new Vector2(0,0);


    private ArrayList<Laser> laserManager = new ArrayList<Laser>();

    private SpaceShoooter game;
    private PlayerShip playerShip = new PlayerShip();

    public GameScreen(SpaceShoooter game) {
        this.game = game;
    }


    @Override
    public void show() {
        screenWidth = Gdx.graphics.getWidth();
        screenHeight= Gdx.graphics.getHeight();

        background = new Texture("space.gif");
    }

    @Override
    public void render(float delta) {
        playerShip.keyboard();
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(background,0,0);
        playerShip.drawShip(game.batch);

        int count = 0;
        while(count < laserManager.size()) {
            Laser currentLaser = laserManager.get(count);
            currentLaser.update();
            count++;
            if(count % 2 == 0) {
                //game.batch.draw(laserTexture, currentLaser.laserLocation1.x, currentLaser.laserLocation1.y);
            }
            else {
                //game.batch.draw(laserTexture, currentLaser.laserLocation1.x, (currentLaser.laserLocation1.y + xWing.getHeight() - 6));
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
