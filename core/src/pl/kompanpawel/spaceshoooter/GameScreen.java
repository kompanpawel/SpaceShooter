package pl.kompanpawel.spaceshoooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen implements Screen {

    private Texture background1;
    private Texture background2;

    private float screenWidth = 0;
    private float screenHeight = 0;

    private boolean isCoop = false;

    private int bgx1 = 0;
    private int bgx2;

    private SpaceShoooter game;
    private PlayerShip playerShip = new PlayerShip();
    private SecondShip secondShip = new SecondShip();
    private EntityManager entityManager = EntityManager.getInstance();

    GameScreen(SpaceShoooter game, int mode) {
        if(mode == 1) {
            this.game = game;
            entityManager.addEntity(playerShip);
            Space.getInstance().addEnemies();
        }
        else if(mode == 2) {
            this.game = game;
            entityManager.addEntity(playerShip);
            entityManager.addEntity(secondShip);
            Space.getInstance().addEnemies();
            isCoop = true;
        }
    }


    private void background() {
        bgx1 -= 1;
        bgx2 -= 1;

        if(bgx1 == -background1.getWidth()){
            System.out.println("cofam");
            bgx1 = background2.getWidth();
        }

        if(bgx2 == -background2.getWidth())
            bgx2 = background1.getWidth();
    }
    @Override
    public void show() {
        screenWidth = Gdx.graphics.getWidth();
        screenHeight= Gdx.graphics.getHeight();

        background1 = new Texture("space.gif");
        background2 = new Texture("space.gif");

        bgx2 = background1.getWidth();
    }

    @Override
    public void render(float delta) {
        playerShip.keyboard();
        if(isCoop)
            secondShip.keyboard();
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MainMenu(game));
            EntityManager.getInstance().removeAllEntities();
        }
        if(playerShip.getHealth() <=0) {
            game.setScreen(new MainMenu(game));
            EntityManager.getInstance().removeAllEntities();
        }
        Space.getInstance().destroyEnemy();
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        for(Entity e: entityManager.getEntities()) {
            if(e instanceof Laser)
                Space.getInstance().laserCollisions((Laser)e);
        }

        background();
        game.batch.begin();
        game.batch.draw(background1,bgx1,0);
        game.batch.draw(background2, bgx2,0);
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
        System.out.println("coś");
    }

    @Override
    public void dispose() {

    }
}
