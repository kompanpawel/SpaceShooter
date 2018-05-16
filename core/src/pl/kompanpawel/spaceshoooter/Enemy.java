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
        update();
        fire();
        batch.draw(tie, this.getLocation().x, this.getLocation().y);
    }

    private Texture tie;
    private Entity owner;

    private int enemyType;
    private int health;

    private Timer timer;
    private TimerTask movmentTask;

    private boolean canShoot = true;
    private boolean changeDir = true;
    private boolean isDead = false;





    public Enemy(int type, Vector2 location, Vector2 velocity) {
        if(type == 1) {
            tie = SpaceShoooter.assetManager.get("rsz_tie_fighter.png");
            this.setLocation(location);
            this.setVelocity(velocity);
            this.setHealth(1);
            enemyType = type;
            timer = new Timer();
            movmentTask = new TimerTask() {
                @Override
                public void run() {
                    if(isDead) {return;}
                    System.out.println(isDead());
                    changeDir = !changeDir;
                }
            };
            timer.schedule(movmentTask, 2000, 2000);
        }
    }

    public void fire() {
        if(!canShoot) {return;}
        if(isDead) {return;}
        Laser laser;
        laser = new Laser (this ,getLocation().cpy().add(-50, tie.getHeight()/2 - 5), new Vector2(-10,0 ));
        EntityManager.getInstance().addEntity(laser);
        canShoot = false;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                canShoot = true;
            }
        },2000);
    }

    public void update() {
        if(isDead) {return;}
        if(changeDir && (getLocation().y < (SpaceShoooter.getHeight() - tie.getHeight() - 5)))
            this.getLocation().y += this.getVelocity().y * Gdx.graphics.getDeltaTime();
        else if(!changeDir && (getLocation().y > 5))
            this.getLocation().y -= this.getVelocity().y * Gdx.graphics.getDeltaTime();
    }

    public int getWidth() {
        return tie.getWidth();
    }

    public int getHeight() {
        return tie.getHeight();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void getHited(Entity e) {
        health -= 1;
        if(health<=0 && enemyType == 1) {
            if(e instanceof PlayerShip) {
                PlayerShip p = (PlayerShip) e;
                p.addScore(10);
            }
            destroyed();
        }
    }
    @Override
    public void dispose() {
        isDead = true;
        movmentTask.cancel();
        timer.cancel();
    }

    public void destroyed() {
            EntityManager.getInstance().removeEntity(this);
    }


    public boolean isDead() {
        return isDead;
    }
}

