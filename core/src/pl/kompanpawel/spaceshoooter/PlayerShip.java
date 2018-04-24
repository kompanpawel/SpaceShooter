package pl.kompanpawel.spaceshoooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class PlayerShip extends Entity {

    private Texture xWing;
    private int PlayerSpeed = 20;


    public PlayerShip() {
        xWing = SpaceShoooter.assetManager.get("xwing.png");
        this.setLocation(new Vector2(20, Gdx.graphics.getHeight() / 2));
        this.setAcceleration(200);

        }


    public void keyboard() {
        if ((Gdx.input.isKeyPressed(Input.Keys.UP)) && (getLocation().y < (Gdx.graphics.getHeight() - xWing.getHeight() - 5))) {
            getLocation().y += getAcceleration() * Gdx.graphics.getDeltaTime();
        }

        if ((Gdx.input.isKeyPressed(Input.Keys.DOWN)) && (getLocation().y > 5)) {
            getLocation().y -= getAcceleration() * Gdx.graphics.getDeltaTime();
        }

        if ((Gdx.input.isKeyPressed(Input.Keys.LEFT)) && (getLocation().x > 5)) {
            getLocation().x -= getAcceleration() * Gdx.graphics.getDeltaTime();
        }

        if ((Gdx.input.isKeyPressed(Input.Keys.RIGHT)) && (getLocation().x < (Gdx.graphics.getWidth() - xWing.getWidth() - 5))) {
            getLocation().x += getAcceleration() * Gdx.graphics.getDeltaTime();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            /* shooting laser */
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            /* exit to menu */
        }
    }

    public void drawShip(SpriteBatch batch) {
        batch.draw(xWing, getLocation().x, getLocation().y);
    }

    public int getPlayerSpeed() {
        return PlayerSpeed;
    }

    public void setPlayerSpeed(int playerSpeed) {
        PlayerSpeed = playerSpeed;
    }
}