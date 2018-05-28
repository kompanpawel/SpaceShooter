package pl.kompanpawel.spaceshoooter.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import pl.kompanpawel.spaceshoooter.*;
import pl.kompanpawel.spaceshoooter.Entities.*;
import pl.kompanpawel.spaceshoooter.Saving.GameData;
import pl.kompanpawel.spaceshoooter.Space.Space;

import java.util.Timer;
import java.util.TimerTask;

public class GameScreen implements Screen {

    private Texture background1;
    private Texture background2;

    private float screenWidth = 0;
    private float screenHeight = 0;

    private boolean isCoop = false;

    private int bgx1 = 0;
    private int bgx2;

    private Window window;



    private SpaceShoooter game;
    private PlayerShip playerShip = new PlayerShip(1);
    private PlayerShip secondShip = new PlayerShip(2);
    private EntityManager entityManager = EntityManager.getInstance();
    private SpriteBatch spriteBatch;
    private GameUI gameUI;

    GameScreen(SpaceShoooter game, int mode) {
        if(mode == 1) {
            this.game = game;
            entityManager.addEntity(playerShip);
            playerShip.setLocation(new Vector2(20, SpaceShoooter.getCamera().viewportHeight / 2));
            Space.getInstance().addEnemies();
            setCamera();
            setSprite();
            gameUI = new GameUI(game);
        }
        else if(mode == 2) {
            this.game = game;
            entityManager.addEntity(playerShip);
            entityManager.addEntity(secondShip);
            Space.getInstance().addEnemies();
            isCoop = true;
            Space.getInstance().setCoop(true);
            setCamera();
            setSprite();
            gameUI = new GameUI(game);
        }
        else if(mode == 3) {
            this.game = game;
            GameData.getInstance().loadGameData();
            entityManager.addEntity(playerShip);
            playerShip.setLocation(new Vector2(GameData.getInstance().getPlayerPosX(),GameData.getInstance().getPlayerPosY()));
            playerShip.setHealth(GameData.getInstance().getPlayerHealth());
            playerShip.setScore(GameData.getInstance().getPlayerScore());
            Space.getInstance().setCoop(GameData.getInstance().isCoop());
            isCoop = Space.getInstance().isCoop();
            if(Space.getInstance().isCoop()) {
                entityManager.addEntity(secondShip);
                secondShip.setLocation(new Vector2(GameData.getInstance().getPlayer2PosX(), GameData.getInstance().getPlayer2PosY()));
                secondShip.setHealth(GameData.getInstance().getPlayer2Health());
                secondShip.setScore(GameData.getInstance().getPlayer2Score());
            }
            isCoop = Space.getInstance().isCoop();
            setCamera();
            setSprite();
            gameUI = new GameUI(game);
            Space.getInstance().setEnemyNumber(GameData.getInstance().getEnemiesNumber());
            Space.getInstance().setLevel(GameData.getInstance().getLevel());
            Space.getInstance().setWave(GameData.getInstance().getWave());
            Space.getInstance().setType(GameData.getInstance().getType());
            Space.getInstance().addEnemiesAfterLoad();
        }
    }
    private void setCamera(){
        SpaceShoooter.getCamera().setToOrtho(false, 1280,720);
    }

    private void setSprite() {
        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(SpaceShoooter.getCamera().combined);
    }
    private void background() {
        if(EntityManager.getInstance().isPause()) {return;}
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
        screenWidth = SpaceShoooter.getCamera().viewportWidth;
        screenHeight= SpaceShoooter.getCamera().viewportHeight;

        background1 = new Texture("backgrounds/space.gif");
        background2 = new Texture("backgrounds/space.gif");

        bgx2 = background1.getWidth();
    }

    @Override
    public void render(float delta) {
        playerShip.keyboard(1);
        if(playerShip.getHealth() <=0 ||((isCoop) && playerShip.getHealth()<=0 && secondShip.getHealth()<=0)) {
            game.setScreen(new EndScreen(game));
            EntityManager.getInstance().removeAllEntities();
        }
        if(isCoop)
            secondShip.keyboard(2);
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MainMenu(game));
            entityManager.removeAllEntities();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.F5)) {
            GameData.getInstance().getGameData();
            Space.getInstance().setShowTextSaved(true);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (!EntityManager.getInstance().isPause()) {
                EntityManager.getInstance().setPause(true);
                Space.getInstance().getTimer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        while(EntityManager.getInstance().isPause()) {
                            System.out.println(EntityManager.getInstance().isPause());
                            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
                                EntityManager.getInstance().setPause(false);
                        }
                        System.out.println("zwalniam");
                    }
                }, 100);
            }

        }
        if(Space.getInstance().getEnemyNumber()<=0) {
            Space.getInstance().newWaveEnemies();
        }
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        for(Entity e: entityManager.getEntities()) {
            if(e instanceof Laser)
                Space.getInstance().laserCollisions((Laser)e);
            if(e instanceof Enemy && Space.getInstance().isChain())
                Space.getInstance().restrictArea((Enemy) e);
        }

        background();
        spriteBatch.begin();
        spriteBatch.draw(background1,bgx1,0,background1.getWidth(),SpaceShoooter.getCamera().viewportHeight);
        spriteBatch.draw(background2, bgx2,0, background2.getWidth(),SpaceShoooter.getCamera().viewportHeight);
        entityManager.draw(spriteBatch, delta);
        spriteBatch.end();
        if(isCoop)
            gameUI.draw(playerShip, secondShip);
        else
            gameUI.draw(playerShip);
        Space.getInstance().drawShip();
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
