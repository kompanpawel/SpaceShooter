package pl.kompanpawel.spaceshoooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Enemy extends Entity {


    @Override
    public void draw(SpriteBatch batch, float delta) {
        update();
        fire();
        batch.draw(toDraw, this.getLocation().x, this.getLocation().y);
    }

    private Texture toDraw;
    private Texture tie;
    private Texture destroyer;

    private Entity owner;

    private int enemyType;
    private int health;

    private Timer timer;
    private TimerTask movmentTask;

    private boolean canShoot = true;
    private boolean changeDir = true;
    private boolean isDead = false;
    private boolean initialMovement = true;
    private boolean firstFire = false;

    private int randomFireTime;
    private int fire;

    private Vector2 startingLocation;




    public Enemy(int type, Vector2 location, Vector2 velocity) {
        if(type == 1) {
            tie = SpaceShoooter.assetManager.get("rsz_tie_fighter.png");
            toDraw = tie;
            this.setLocation(location);
            this.setVelocity(velocity);
            this.setHealth(2);
            startingLocation = new Vector2(0,0);
            enemyType = type;
            timer = new Timer();
            movmentTask = new TimerTask() {
                @Override
                public void run() {
                    if(isDead) {return;}
                    changeDir = !changeDir;
                }
            };
            timer.schedule(movmentTask, 2000, 2000);
        }
        if(type == 2) {
            destroyer = SpaceShoooter.assetManager.get("executor_copy.png");
            toDraw = destroyer;
            this.setLocation(location);
            this.setVelocity(velocity);
            this.setHealth(10);
            startingLocation = new Vector2(0,0);
            enemyType = type;
            timer = new Timer();
            movmentTask = new TimerTask() {
                @Override
                public void run() {
                    if(isDead) {return;}
                    changeDir = !changeDir;
                }
            };
            timer.schedule(movmentTask, 10000, 10000);
        }
    }

    public void fire() {
        if (enemyType == 1) {
            if (!canShoot) {return;}
            if (isDead) {return;}
            if (!firstFire) {return;}
            Laser laser;
            int level = Space.getInstance().getLevel();
            laser = new Laser(this, getLocation().cpy().add(-50, tie.getHeight() / 2 - 5), new Vector2(-10+(3*level), 0));
            EntityManager.getInstance().addEntity(laser);
            canShoot = false;
            Random rand = new Random();
            randomFireTime = rand.nextInt(2000) + 1000;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    canShoot = true;
                }
            }, randomFireTime);
        }
        else if(enemyType == 2) {

        }
    }

    public void update() {
        if(isDead) {return;}
        if(initialMovement && enemyType == 1) {
            this.getLocation().x -= this.getVelocity().x/2 * Gdx.graphics.getDeltaTime();
            startingLocation.x += this.getVelocity().x/2 * Gdx.graphics.getDeltaTime();
            if(startingLocation.x  >150 ) {
                initialMovement = false;
                Random rand = new Random();
                fire = rand.nextInt(1000)+50;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        firstFire = true;
                    }
                },fire);
            }
        }
        else if(initialMovement && enemyType == 2) {
            this.getLocation().x -= this.getVelocity().x/2 * Gdx.graphics.getDeltaTime();
            startingLocation.x += this.getVelocity().x/2 * Gdx.graphics.getDeltaTime();
            if(startingLocation.x  >500 ) {
                initialMovement = false;
                Random rand = new Random();
                fire = rand.nextInt(1000)+50;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        firstFire = true;
                    }
                },fire);
            }
        }
        System.out.println(this.getLocation().x);
        if(!initialMovement && changeDir && (getLocation().y < (SpaceShoooter.getHeight() - toDraw.getHeight() - 5)))
            this.getLocation().y += this.getVelocity().y/2 * Gdx.graphics.getDeltaTime();
        else if(!initialMovement && !changeDir && (getLocation().y > 5))
            this.getLocation().y -= this.getVelocity().y/2 * Gdx.graphics.getDeltaTime();
    }

    public int getWidth() {
        return toDraw.getWidth();
    }

    public int getHeight() {
        return toDraw.getHeight();
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
        else if(health<=0 && enemyType == 2) {
            if(e instanceof PlayerShip) {
                PlayerShip p = (PlayerShip) e;
                p.addScore(100);
            }
            destroyed();
        }
    }
    @Override
    public void dispose() {
        isDead = true;
        movmentTask.cancel();
        timer.cancel();
        Space.getInstance().setEnemyNumber(Space.getInstance().getEnemyNumber()-1);
    }

    public void destroyed() {
            EntityManager.getInstance().removeEntity(this);
    }


    public boolean isDead() {
        return isDead;
    }

    public boolean initialMovement() {return initialMovement;}

    public Vector2 getStartingLocation() {
        return startingLocation;
    }

    public void setStartingLocation(Vector2 startingLocation) {
        this.startingLocation = startingLocation;
    }

    public int getEnemyType() {
        return enemyType;
    }

    public void setEnemyType(int enemyType) {
        this.enemyType = enemyType;
    }
}

