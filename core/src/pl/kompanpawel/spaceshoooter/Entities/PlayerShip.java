package pl.kompanpawel.spaceshoooter.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import pl.kompanpawel.spaceshoooter.Space.Space;
import pl.kompanpawel.spaceshoooter.SpaceShoooter;


public class PlayerShip extends Entity {

    @Override
    public void draw(SpriteBatch batch, float delta) {
        batch.draw(xWing, getLocation().x, getLocation().y);
    }

    private Texture xWing;

    private int health;
    private int score;
    private int playerNumber;

    private boolean shootTop = true;
    private boolean isDead = false;


    public PlayerShip(int player) {
        if(player == 1) {
            xWing = SpaceShoooter.assetManager.get("ships/xwing.png");
            this.setLocation(new Vector2(20, SpaceShoooter.getCamera().viewportHeight / 4 * 3));
            this.setVelocity(new Vector2(300, 300));
            health = 100;
            playerNumber = 1;
        }
        else if (player == 2) {
            xWing = SpaceShoooter.assetManager.get("ships/xwing.png");
            this.setLocation(new Vector2(20, SpaceShoooter.getCamera().viewportHeight / 4 ));
            this.setVelocity(new Vector2(300, 300));
            health = 10;
            playerNumber = 2;
        }
    }

    public void getHited() {
        health -= 1;
        if(health <=0)
            destroyed();
    }

    private void destroyed() {
        isDead = true;
        EntityManager.getInstance().removeEntity(this);
    }



    public void keyboard(int player) {
        if(player == 1) {
            if(EntityManager.getInstance().isPause()) {return;}
            if ((Gdx.input.isKeyPressed(Input.Keys.UP)) && (getLocation().y < (SpaceShoooter.getCamera().viewportHeight - xWing.getHeight() - 5))) {
                getLocation().y += getVelocity().y * Gdx.graphics.getDeltaTime();
            }

            if ((Gdx.input.isKeyPressed(Input.Keys.DOWN)) && (getLocation().y > 5)) {
                getLocation().y -= getVelocity().y * Gdx.graphics.getDeltaTime();
            }

            if ((Gdx.input.isKeyPressed(Input.Keys.LEFT)) && (getLocation().x > 5)) {
                getLocation().x -= getVelocity().x * Gdx.graphics.getDeltaTime();
            }

            if ((Gdx.input.isKeyPressed(Input.Keys.RIGHT)) && (getLocation().x < (SpaceShoooter.getCamera().viewportWidth - xWing.getWidth() - 5))) {
                getLocation().x += getVelocity().x * Gdx.graphics.getDeltaTime();
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_RIGHT)) {
                if(isDead) {return;}
                if(!Space.getInstance().isPlayerCanShoot()){return;}
                Laser laser;
                if (shootTop) {
                    laser = new Laser(this, 1, getLocation().cpy().add(0, xWing.getHeight() - 5), new Vector2(10, 0));
                } else {
                    laser = new Laser(this, 1, getLocation().cpy(), new Vector2(10, 0));
                }
                shootTop = !shootTop;
                EntityManager.getInstance().addEntity(laser);
            }

        }
        else if (player == 2) {
            if(EntityManager.getInstance().isPause()) {return;}
            if ((Gdx.input.isKeyPressed(Input.Keys.W)) && (getLocation().y < (SpaceShoooter.getCamera().viewportHeight - xWing.getHeight() - 5))) {
                getLocation().y += getVelocity().y * Gdx.graphics.getDeltaTime();
            }

            if ((Gdx.input.isKeyPressed(Input.Keys.S)) && (getLocation().y > 5)) {
                getLocation().y -= getVelocity().y * Gdx.graphics.getDeltaTime();
            }

            if ((Gdx.input.isKeyPressed(Input.Keys.A)) && (getLocation().x > 5)) {
                getLocation().x -= getVelocity().x * Gdx.graphics.getDeltaTime();
            }

            if ((Gdx.input.isKeyPressed(Input.Keys.D) && (getLocation().x < (SpaceShoooter.getCamera().viewportWidth - xWing.getWidth() - 5)))) {
                getLocation().x += getVelocity().x * Gdx.graphics.getDeltaTime();
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_LEFT)) {
                if(isDead) {return;}
                if(!Space.getInstance().isPlayerCanShoot()){return;}
                Laser laser;
                if(shootTop) {
                    laser = new Laser (this, 1, getLocation().cpy().add(0,xWing.getHeight()- 5), new Vector2(10 ,0 ));
                } else {
                    laser = new Laser (this, 1, getLocation().cpy(), new Vector2(10 ,0 ));
                }
                shootTop = !shootTop;
                EntityManager.getInstance().addEntity(laser);
            }
        }
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getPlayerNumber() {
        return playerNumber;
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

    void addScore(int add) {
        score  += add;
    }
}