package pl.kompanpawel.spaceshoooter;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.kompanpawel.spaceshoooter.Entities.PlayerShip;
import pl.kompanpawel.spaceshoooter.Space.Space;

public class GameUI {
    private SpaceShoooter game;
    private SpriteBatch spriteBatch;
    private OrthographicCamera camera;
    private BitmapFont font;

    public GameUI(SpaceShoooter game) {
        this.game = game;
        font = new BitmapFont();
        camera = SpaceShoooter.getCamera();
        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(camera.combined);
    }

    public void draw(PlayerShip playerShip) {
        spriteBatch.begin();
        font.draw(spriteBatch, "Player 1",10,camera.viewportHeight - 15);
        font.draw(spriteBatch, "HP: "+ playerShip.getHealth(), 10, camera.viewportHeight - 30);
        font.draw(spriteBatch,"Points: "+playerShip.getScore(), 10, camera.viewportHeight - 45);
        if(Space.getInstance().isChain())
            font.draw(spriteBatch, "Can't shoot: magnetic field", 10, camera.viewportHeight - 60);
        if(!Space.getInstance().isPlayerCanShoot() && !Space.getInstance().isChain())
            font.draw(spriteBatch, "Magnetic field off \nLaser restart", 10, camera.viewportHeight - 60);
        /*if(playerShip.getLaserAmmo()>0)
            font.draw(spriteBatch, "Shots: "+playerShip.getLaserAmmo(), 10, camera.viewportHeight - 60);
        else
            font.draw(spriteBatch, "Shots: deheating", 10, camera.viewportHeight - 60);*/
        spriteBatch.end();
    }

    public void draw(PlayerShip playerShip, PlayerShip secondShip) {
        spriteBatch.begin();
        font.draw(spriteBatch, "Player 1",10,camera.viewportHeight - 15);
        font.draw(spriteBatch, "HP: "+ playerShip.getHealth(), 10, camera.viewportHeight - 30);
        font.draw(spriteBatch,"Points: "+playerShip.getScore(), 10, camera.viewportHeight - 45);
        if(Space.getInstance().isChain())
            font.draw(spriteBatch, "Can't shoot: magnetic field", 10, camera.viewportHeight - 60);
        if(!Space.getInstance().isPlayerCanShoot())
            font.draw(spriteBatch, "Laser restart", 10, camera.viewportHeight - 60);
        font.draw(spriteBatch, "Player 2",10,60);
        font.draw(spriteBatch, "HP: "+ secondShip.getHealth(), 10,  45);
        font.draw(spriteBatch,"Points: "+secondShip.getScore(), 10, 30);
        /*if(secondShip.getLaserAmmo()>0)
            font.draw(spriteBatch, "Shots: "+secondShip.getLaserAmmo(), 10,  15);
        else
            font.draw(spriteBatch, "Shots: deheating", 10, 15);*/
        spriteBatch.end();
    }
}
