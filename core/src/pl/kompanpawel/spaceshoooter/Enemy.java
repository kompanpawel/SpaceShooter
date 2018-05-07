package pl.kompanpawel.spaceshoooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Timer;
import java.util.TimerTask;

public class Enemy extends Entity {


    @Override
    public void draw(SpriteBatch batch, float delta) {
        batch.draw(tie, this.getLocation().x, this.getLocation().y);
    }

    private Texture tie;
    private Entity owner;

    private int type;

    private boolean canShoot = true;



    public Enemy(Vector2 location, Vector2 velocity) {
        tie = SpaceShoooter.assetManager.get("rsz_tie_fighter.png");
        this.setLocation(location);
        this.setVelocity(velocity);
    }

    public void fire() {
        if(!canShoot) {return;}
        Laser laser;
        laser = new Laser (this ,getLocation().cpy().add(0, tie.getHeight()/2 - 5), new Vector2(-10,0 ));
        EntityManager.getInstance().addEntity(laser);
        canShoot = false;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                canShoot = true;
            }
        },2000);
    }

    public void update() {

    }
}