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

    private int randomFireTime, rF1, rF2, rF3, rF4, rF5, rF6, rF7, rF8;
    private int fire;

    private Vector2 startingLocation;

    private boolean shootFirst = true;
    private boolean shootSecond = true;
    private boolean shootThird = true;
    private boolean shootForth = true;
    private boolean shootFifth = true;
    private boolean shootSixth = true;
    private boolean shootSeventh = true;
    private boolean shootEigth = true;
    private boolean shootNineth = true;


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
            this.setHealth(50);
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
            laser = new Laser(this,1, getLocation().cpy().add(-50, tie.getHeight() / 2 - 5), new Vector2(-10-(3*level), 0));
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
            if(isDead) {return;}
            if(!firstFire) {return;}
            Laser laser1, laser2, laser3, laser4, laser5, laser6, laser7, laser8, laser9;
            if(shootFirst) {
                laser1 = new Laser(this, 1, getLocation().cpy().add(95, 77), new Vector2(-13, 0));
                EntityManager.getInstance().addEntity(laser1);
                shootFirst = false;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        shootFirst = true;
                    }
                }, randomFireTime);
            }
            if(shootSecond) {
                laser2 = new Laser(this, 2, getLocation().cpy().add(115, 87), new Vector2(-13, 2));
                EntityManager.getInstance().addEntity(laser2);
                shootSecond = false;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        shootSecond = true;
                    }
                }, rF1);
            }
            if(shootThird) {
                laser3 = new Laser(this, 3, getLocation().cpy().add(115, 67), new Vector2(-13, -2));
                EntityManager.getInstance().addEntity(laser3);
                shootThird = false;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        shootThird = true;
                    }
                }, rF2);
            }
            if(shootForth) {
                laser4 = new Laser(this, 1, getLocation().cpy().add(284, 28), new Vector2(-13, 0));
                EntityManager.getInstance().addEntity(laser4);
                shootForth = false;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        shootForth = true;
                    }
                }, rF3);
            }
            if(shootFifth) {
                laser5 = new Laser(this, 1, getLocation().cpy().add(284, 140), new Vector2(-13, 0));
                EntityManager.getInstance().addEntity(laser5);
                shootFifth = false;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        shootFifth = true;
                    }
                }, rF4);
            }
            if(shootSixth) {
                laser6 = new Laser(this, 4, getLocation().cpy().add(200, 50), new Vector2(-13, 4));
                EntityManager.getInstance().addEntity(laser6);
                shootSixth = false;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        shootSixth = true;
                    }
                }, rF5);
            }
            if(shootSeventh) {
                laser7 = new Laser(this, 5, getLocation().cpy().add(200, 100), new Vector2(-13, -4));
                EntityManager.getInstance().addEntity(laser7);
                shootSeventh = false;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        shootSeventh = true;
                    }
                }, rF6);
            }
            if(shootEigth) {
                laser8 = new Laser(this, 1, getLocation().cpy().add(300, 0), new Vector2(-15, 0));
                EntityManager.getInstance().addEntity(laser8);
                shootEigth = false;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        shootEigth = true;
                    }
                }, rF7);
            }
            if(shootNineth) {
                laser9 = new Laser(this, 1, getLocation().cpy().add(300, 177), new Vector2(-15, 0));
                EntityManager.getInstance().addEntity(laser9);
                shootNineth = false;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        shootNineth = true;
                    }
                }, rF8);
            }
            Random rand = new Random();
            randomFireTime = rand.nextInt(1500) + 500;
            rF1 = rand.nextInt(1500) + 500;
            rF2 = rand.nextInt(1500) + 500;
            rF3 = rand.nextInt(1500) + 500;
            rF4 = rand.nextInt(1500) + 500;
            rF5 = rand.nextInt(1500) + 500;
            rF6 = rand.nextInt(1500) + 500;
            rF7 = rand.nextInt(1500) + 500;
            rF8 = rand.nextInt(1500) + 500;



        }
    }

    public void update() {
        if(isDead) {return;}
        if(initialMovement && enemyType == 1) {
            this.getLocation().x -= this.getVelocity().x/2 * Gdx.graphics.getDeltaTime();
            startingLocation.x += this.getVelocity().x/2 * Gdx.graphics.getDeltaTime();
            if(startingLocation.x  >200 ) {
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
            this.getLocation().x -= this.getVelocity().x *3 * Gdx.graphics.getDeltaTime();
            startingLocation.x += this.getVelocity().x *3 * Gdx.graphics.getDeltaTime();
            if(startingLocation.x  >500 ) {
                initialMovement = false;
                Random rand = new Random();
                fire = rand.nextInt(2000)+1000;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        firstFire = true;
                    }
                },fire);
            }
        }
        if(enemyType == 1) {
            if (!initialMovement && changeDir && (getLocation().y < (SpaceShoooter.getHeight() - toDraw.getHeight() - 5)))
                this.getLocation().y += this.getVelocity().y / 2 * Gdx.graphics.getDeltaTime();
            else if (!initialMovement && !changeDir && (getLocation().y > 5))
                this.getLocation().y -= this.getVelocity().y / 2 * Gdx.graphics.getDeltaTime();
        }
        else if(enemyType == 2) {
            if (!initialMovement && changeDir && (getLocation().y < (SpaceShoooter.getHeight() - toDraw.getHeight() - 5)))
                this.getLocation().y += this.getVelocity().y / 2 * Gdx.graphics.getDeltaTime();
            else if (!initialMovement && !changeDir && (getLocation().y > 5))
                this.getLocation().y -= this.getVelocity().y / 2 * Gdx.graphics.getDeltaTime();
        }
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

