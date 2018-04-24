package pl.kompanpawel.spaceshoooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class PlayerShip extends Entity {

    @Override
    public void draw(SpriteBatch batch, float delta) {
        batch.draw(xWing, getLocation().x, getLocation().y);
    }

    private Texture xWing;
    private int PlayerSpeed = 20;

    private boolean shootTop = true;


    public PlayerShip() {
        xWing = SpaceShoooter.assetManager.get("xwing.png");
        this.setLocation(new Vector2(20, Gdx.graphics.getHeight() / 2));
        this.setVelocity(new Vector2 (200,200));

        }


    public void keyboard() {
        if ((Gdx.input.isKeyPressed(Input.Keys.UP)) && (getLocation().y < (Gdx.graphics.getHeight() - xWing.getHeight() - 5))) {
            getLocation().y += getVelocity().y * Gdx.graphics.getDeltaTime();
        }

        if ((Gdx.input.isKeyPressed(Input.Keys.DOWN)) && (getLocation().y > 5)) {
            getLocation().y -= getVelocity().y * Gdx.graphics.getDeltaTime();
        }

        if ((Gdx.input.isKeyPressed(Input.Keys.LEFT)) && (getLocation().x > 5)) {
            getLocation().x -= getVelocity().x * Gdx.graphics.getDeltaTime();
        }

        if ((Gdx.input.isKeyPressed(Input.Keys.RIGHT)) && (getLocation().x < (Gdx.graphics.getWidth() - xWing.getWidth() - 5))) {
            getLocation().x += getVelocity().x * Gdx.graphics.getDeltaTime();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            Laser laser;
            if(shootTop) {
                laser = new Laser (this ,getLocation().cpy().add(0,xWing.getHeight()- 5), new Vector2(400 * Gdx.graphics.getDeltaTime(),0 ));
            } else {
                laser = new Laser (this ,getLocation().cpy(), new Vector2(400 * Gdx.graphics.getDeltaTime(),0 ));
            }
            shootTop = !shootTop;
            EntityManager.getInstance().addEntity(laser);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            /* exit to menu */
        }
    }


    public int getPlayerSpeed() {
        return PlayerSpeed;
    }

    public void setPlayerSpeed(int playerSpeed) {
        PlayerSpeed = playerSpeed;
    }
}