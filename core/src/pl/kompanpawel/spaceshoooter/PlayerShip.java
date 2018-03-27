package pl.kompanpawel.spaceshoooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Observable;
import java.util.Observer;

public class PlayerShip extends Entity{

    private Texture xWing;

    public PlayerShip() {
        xWing = SpaceShoooter.assetManager.get("xwing.png");
        this.setLocation(new Vector2(20, Gdx.graphics.getHeight() / 2));
        this.setAcceleration(200);
        ControllerManager.getInstance().addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                keyboard((Integer) arg);
            }
        });
    }

    private void keyboard(int key) {
        if((Gdx.input.isKeyPressed(Input.Keys.UP)) && (getLocation().y < (Gdx.graphics.getHeight() - xWing.getHeight()-5))) {
            getLocation().y += getAcceleration() * Gdx.graphics.getDeltaTime();
        }

        if((Gdx.input.isKeyPressed(Input.Keys.DOWN)) && (getLocation().y > 5)) {
            getLocation().y -= getAcceleration() * Gdx.graphics.getDeltaTime();
        }

        if((Gdx.input.isKeyPressed(Input.Keys.LEFT)) && (getLocation().x > 5)) {
            getLocation().x -= getAcceleration() * Gdx.graphics.getDeltaTime();
        }

        if((Gdx.input.isKeyPressed(Input.Keys.RIGHT)) && (getLocation().x < (Gdx.graphics.getWidth() - xWing.getWidth()-5))) {
            getLocation().x += getAcceleration() * Gdx.graphics.getDeltaTime();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            Laser laser = new Laser(getLocation(), getVelocity());
            laserManager.add(laser);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            /* exit to menu */
        }
    }
}
