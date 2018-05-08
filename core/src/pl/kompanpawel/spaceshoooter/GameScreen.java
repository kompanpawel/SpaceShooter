package pl.kompanpawel.spaceshoooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class GameScreen implements Screen {

    private Texture background;

    private float screenWidth = 0;
    private float screenHeight = 0;

    private SpaceShoooter game;
    private PlayerShip playerShip = new PlayerShip();
    private Enemy enemy1 = (Enemy) EntityFactory.factorTIE1();
    private Enemy enemy2 = (Enemy) EntityFactory.factorTIE2();
    private EntityManager entityManager = EntityManager.getInstance();

    GameScreen(SpaceShoooter game) {
        this.game = game;
        entityManager.addEntity(playerShip);
        entityManager.addEntity(enemy1);
        entityManager.addEntity(enemy2);
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
        enemy1.fire();
        enemy2.fire();
        enemy1.update();
        enemy2.update();
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MainMenu(game));
        }
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        for(Entity e: entityManager.getEntities()) {
            if(e instanceof Laser)
                Space.getInstance().laserCollisions((Laser)e);
        }
        game.batch.begin();
        game.batch.draw(background,0,0);
        entityManager.draw(game.batch, delta);
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
