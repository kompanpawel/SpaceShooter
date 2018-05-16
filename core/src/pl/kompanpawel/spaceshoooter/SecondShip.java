package pl.kompanpawel.spaceshoooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class SecondShip extends Entity {
    @Override
    public void draw(SpriteBatch batch, float delta) {
        batch.draw(xWing, getLocation().x, getLocation().y);
    }

    private Texture xWing;

    private int health;

    private boolean shootTop = true;
    private boolean isDead = false;


    public SecondShip() {
        xWing = SpaceShoooter.assetManager.get("xwing.png");
        this.setLocation(new Vector2(20, (Gdx.graphics.getHeight() / 2)+100));
        this.setVelocity(new Vector2 (200,200));
        health = 5;
    }

    public void getHited() {
        health -= 1;
    }

    public void destroyed() {
        isDead = true;
        EntityManager.getInstance().removeEntity(this);
    }

    public boolean isDead() {
        return isDead;
    }

    public int getHealth() {
        return health;
    }

    public int getWidth() {
        return xWing.getWidth();
    }

    public int getHeight() {
        return xWing.getHeight();
    }


    public void keyboard() {
        if ((Gdx.input.isKeyPressed(Input.Keys.W)) && (getLocation().y < (Gdx.graphics.getHeight() - xWing.getHeight() - 5))) {
            getLocation().y += getVelocity().y * Gdx.graphics.getDeltaTime();
        }

        if ((Gdx.input.isKeyPressed(Input.Keys.S)) && (getLocation().y > 5)) {
            getLocation().y -= getVelocity().y * Gdx.graphics.getDeltaTime();
        }

        if ((Gdx.input.isKeyPressed(Input.Keys.A)) && (getLocation().x > 5)) {
            getLocation().x -= getVelocity().x * Gdx.graphics.getDeltaTime();
        }

        if ((Gdx.input.isKeyPressed(Input.Keys.D) && (getLocation().x < (Gdx.graphics.getWidth() - xWing.getWidth() - 5)))) {
            getLocation().x += getVelocity().x * Gdx.graphics.getDeltaTime();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_LEFT)) {
            Laser laser;
            if(shootTop) {
                laser = new Laser (this ,getLocation().cpy().add(0,xWing.getHeight()- 5), new Vector2(10 ,0 ));
            } else {
                laser = new Laser (this ,getLocation().cpy(), new Vector2(10 ,0 ));
            }
            shootTop = !shootTop;
            EntityManager.getInstance().addEntity(laser);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            /* exit to menu */
        }
    }
}

