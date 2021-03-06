package pl.kompanpawel.spaceshoooter.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import pl.kompanpawel.spaceshoooter.*;
import pl.kompanpawel.spaceshoooter.Space.Space;

import java.util.Random;
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
    private Texture executor;
    private Texture destroyer_left;
    private Texture destroyer_right;

    private int enemyType;
    private int health;

    private TimerTask movmentTask;

    private boolean canShoot = true;
    private boolean changeDir = true;
    private boolean isDead = false;
    private boolean initialMovement = true;
    private boolean firstFire = false;
    private boolean right = true;
    private boolean hyperSpace = true;
    private boolean canFly = false;
    private boolean chainOn = false;
    private boolean firstRandom = true;
    private boolean mutex = true;


    private int randomFireTime, rF1, rF2, rF3, rF4, rF5, rF6, rF7, rF8;
    private int fire;

    private Vector2 startingLocation;
    private Vector2 fireDest;

    private boolean shootFirst = true;
    private boolean shootSecond = true;
    private boolean shootThird = true;
    private boolean shootForth = true;
    private boolean shootFifth = true;
    private boolean shootSixth = true;
    private boolean shootSeventh = true;
    private boolean shootEighth = true;
    private boolean shootNinth = true;


    Enemy(int type, Vector2 location, Vector2 velocity) {
        if(type == 1) {
            tie = SpaceShoooter.assetManager.get("ships/rsz_tie_fighter.png");
            toDraw = tie;
            this.setLocation(location);
            this.setVelocity(velocity);
            this.setHealth(1+Space.getInstance().getLevel());
            startingLocation = new Vector2(0,0);
            enemyType = type;
            movmentTask = new TimerTask() {
                @Override
                public void run() {
                    if(isDead) {return;}
                    changeDir = !changeDir;
                }
            };
            Space.getInstance().getTimer().schedule(movmentTask, 2000, 2000);
        }
        else if(type == 2) {
            destroyer_right = SpaceShoooter.assetManager.get("ships/dest_right.png");
            destroyer_left = SpaceShoooter.assetManager.get("ships/dest_left.png");
            toDraw = destroyer_right;
            this.setLocation(location);
            this.setVelocity(velocity);
            this.setHealth(50);
            startingLocation = new Vector2(0,0);
            enemyType = type;
            movmentTask = new TimerTask() {
                @Override
                public void run() {
                    if(isDead) {return;}
                    changeDir = !changeDir;
                }
            };
            Space.getInstance().getTimer().schedule(movmentTask, 5000, 5000);
        }
        else if(type == 3) {
            executor = SpaceShoooter.assetManager.get("ships/executor_copy.png");
            toDraw = executor;
            this.setLocation(location);
            this.setVelocity(velocity);
            this.setHealth(100);
            startingLocation = new Vector2(0,0);
            enemyType = type;
            movmentTask = new TimerTask() {
                @Override
                public void run() {
                    if(isDead) {return;}
                    changeDir = !changeDir;
                }
            };
            Space.getInstance().getTimer().schedule(movmentTask, 10000, 10000);
        }
    }

    private void fire() {
        if(EntityManager.getInstance().isPause()) {
            return;
        }
        if (enemyType == 1) {
            if (!canShoot) {return;}
            if (isDead) {return;}
            if (!firstFire) {return;}
            Laser laser;
            int level = Space.getInstance().getLevel();
            laser = new Laser(this,1, getLocation().cpy().add(10, tie.getHeight() / 2 ), new Vector2(-10-(3*level), 0));
            EntityManager.getInstance().addEntity(laser);
            canShoot = false;
            Random rand = new Random();
            randomFireTime = rand.nextInt(2000) + 1000;
            Space.getInstance().getTimer().schedule(new TimerTask() {
                @Override
                public void run() {
                    canShoot = true;
                }
            }, randomFireTime);
        }
        else if(enemyType == 3) {
            if(isDead) {return;}
            if(!firstFire) {return;}
            Laser laser1, laser2, laser3, laser4, laser5, laser6, laser7, laser8, laser9;
            if(shootFirst) {
                laser1 = new Laser(this, 1, getLocation().cpy().add(95, 77), new Vector2(-13, 0));
                EntityManager.getInstance().addEntity(laser1);
                shootFirst = false;
                Space.getInstance().getTimer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        shootFirst = true;
                    }
                }, randomFireTime);
            }
            if(shootSecond) {
                locateEnemy(115, 107);
                laser2 = new Laser(this, 2, getLocation().cpy().add(115, 87), fireDest);
                EntityManager.getInstance().addEntity(laser2);
                shootSecond = false;
                Space.getInstance().getTimer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        shootSecond = true;
                    }
                }, rF1);
            }
            if(shootThird) {
                locateEnemy(115,58);
                laser3 = new Laser(this, 3, getLocation().cpy().add(115, 67), fireDest);
                EntityManager.getInstance().addEntity(laser3);
                shootThird = false;
                Space.getInstance().getTimer().schedule(new TimerTask() {
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
                Space.getInstance().getTimer().schedule(new TimerTask() {
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
                Space.getInstance().getTimer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        shootFifth = true;
                    }
                }, rF4);
            }
            if(shootSixth) {
                locateEnemy(200, 40);
                laser6 = new Laser(this, 4, getLocation().cpy().add(200, 50), fireDest);
                EntityManager.getInstance().addEntity(laser6);
                shootSixth = false;
                Space.getInstance().getTimer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        shootSixth = true;
                    }
                }, rF5);
            }
            if(shootSeventh) {
                locateEnemy(200, 90);
                laser7 = new Laser(this, 5, getLocation().cpy().add(200, 100), fireDest);
                EntityManager.getInstance().addEntity(laser7);
                shootSeventh = false;
                Space.getInstance().getTimer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        shootSeventh = true;
                    }
                }, rF6);
            }
            if(shootEighth) {
                laser8 = new Laser(this, 1, getLocation().cpy().add(300, 0), new Vector2(-15, 0));
                EntityManager.getInstance().addEntity(laser8);
                shootEighth = false;
                Space.getInstance().getTimer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        shootEighth = true;
                    }
                }, rF7);
            }
            if(shootNinth) {
                laser9 = new Laser(this, 1, getLocation().cpy().add(300, 177), new Vector2(-15, 0));
                EntityManager.getInstance().addEntity(laser9);
                shootNinth = false;
                Space.getInstance().getTimer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        shootNinth = true;
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
        else if(enemyType == 2) {
            if(isDead) {return;}
            if(!firstFire) {return;}
            if(firstRandom && mutex) {
                for(int j = 0; j<3; j++) {
                    int i;
                    Random rng = new Random();
                    i = rng.nextInt(6) + 1;
                    switch (i) {
                        case 1: shootFirst = false; break;
                        case 2: shootSecond = false; break;
                        case 3: shootThird = false; break;
                        case 4: shootForth = false; break;
                        case 5: shootFifth = false; break;
                        case 6: shootSixth = false; break;
                    }
                }
            }
            if(!firstRandom && mutex) {
                shootFirst = true;
                shootSecond = true;
                shootThird =  true;
                shootForth = true;
                shootFifth = true;
                shootSixth = true;
                mutex = false;
            }
            Laser laser1, laser2, laser3, laser4, laser5, laser6, laser7;
            if(shootFirst && toDraw == destroyer_right) {
                if(getLocation().y < SpaceShoooter.getHeight()/2)
                    laser1 = new Laser(this, 1, getLocation().cpy().add(50, 177), new Vector2(0, 5));
                else
                    laser1 = new Laser(this, 1, getLocation().cpy().add(60, 40), new Vector2(0, -5));
                EntityManager.getInstance().addEntity(laser1);
                shootFirst = false;
                Space.getInstance().getTimer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            shootFirst = true;
                        }
                    }, randomFireTime);
            }
            if(shootFirst && toDraw == destroyer_left) {
                laser1 = new Laser(this, 1, getLocation().cpy().add(95, 77), new Vector2(-13, 0));
                EntityManager.getInstance().addEntity(laser1);
                shootFirst = false;
                Space.getInstance().getTimer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        shootFirst = true;
                    }
                }, randomFireTime);
            }
            if(shootSecond && toDraw == destroyer_right) {
                if(getLocation().y < SpaceShoooter.getHeight()/2)
                    laser2 = new Laser(this, 1, getLocation().cpy().add(130, 167), new Vector2(0, 5));
                else
                    laser2 = new Laser(this, 1, getLocation().cpy().add(140, 50), new Vector2(0, -5));
                EntityManager.getInstance().addEntity(laser2);
                shootSecond = false;
                Space.getInstance().getTimer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        shootSecond = true;
                    }
                }, rF1);
            }
            if(shootSecond && toDraw == destroyer_left) {
                locateEnemy(115, 77);
                laser2 = new Laser(this, 2, getLocation().cpy().add(115, 87), fireDest);
                EntityManager.getInstance().addEntity(laser2);
                shootSecond = false;
                Space.getInstance().getTimer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        shootSecond = true;
                    }
                }, rF1);
            }
            if(shootThird && toDraw == destroyer_right) {
                if(getLocation().y < SpaceShoooter.getHeight()/2)
                    laser3 = new Laser(this, 1, getLocation().cpy().add(210, 157), new Vector2(0, 5));
                else
                    laser3 = new Laser(this, 1, getLocation().cpy().add(200, 70), new Vector2(0, -5));
                EntityManager.getInstance().addEntity(laser3);
                shootThird = false;
                Space.getInstance().getTimer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        shootThird = true;
                    }
                }, rF2);
            }
            if(shootThird && toDraw == destroyer_left) {
                locateEnemy(115, 57);
                laser3 = new Laser(this, 3, getLocation().cpy().add(115, 67), fireDest);
                EntityManager.getInstance().addEntity(laser3);
                shootThird = false;
                Space.getInstance().getTimer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        shootThird = true;
                    }
                }, rF2);
            }
            if(shootForth && toDraw == destroyer_right) {
                if(getLocation().y < SpaceShoooter.getHeight()/2)
                    laser4 = new Laser(this, 1, getLocation().cpy().add(290, 147), new Vector2(0, 5));
                else
                    laser4 = new Laser(this, 1, getLocation().cpy().add(260, 80), new Vector2(0, -5));
                EntityManager.getInstance().addEntity(laser4);
                shootForth = false;
                Space.getInstance().getTimer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        shootForth = true;
                    }
                }, rF3);
            }
            if(shootForth && toDraw == destroyer_left) {
                laser4 = new Laser(this, 1, getLocation().cpy().add(284, 28), new Vector2(-13, 0));
                EntityManager.getInstance().addEntity(laser4);
                shootForth = false;
                Space.getInstance().getTimer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        shootForth = true;
                    }
                }, rF3);
            }
            if(shootFifth && toDraw == destroyer_right) {
                if(getLocation().y < SpaceShoooter.getHeight()/2)
                    laser5 = new Laser(this, 1, getLocation().cpy().add(360, 137), new Vector2(0, 5));
                else
                    laser5 = new Laser(this, 1, getLocation().cpy().add(320, 90), new Vector2(0, -5));
                EntityManager.getInstance().addEntity(laser5);
                shootFifth = false;
                Space.getInstance().getTimer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        shootFifth = true;
                    }
                }, rF4);
            }
            if(shootFifth && toDraw == destroyer_left) {
                laser5 = new Laser(this, 1, getLocation().cpy().add(284, 140), new Vector2(-13, 0));
                EntityManager.getInstance().addEntity(laser5);
                shootFifth = false;
                Space.getInstance().getTimer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        shootFifth = true;
                    }
                }, rF4);
            }
            if(shootSixth && toDraw == destroyer_right) {
                if(getLocation().y < SpaceShoooter.getHeight()/2)
                    laser6 = new Laser(this, 1, getLocation().cpy().add(440, 127), new Vector2(0, 5));
                else
                    laser6 = new Laser(this, 1, getLocation().cpy().add(380, 100), new Vector2(0, -5));
                EntityManager.getInstance().addEntity(laser6);
                shootSixth = false;
                Space.getInstance().getTimer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        shootSixth = true;
                    }
                }, rF5);
            }
            if(shootSixth && toDraw == destroyer_left) {
                laser6 = new Laser(this, 4, getLocation().cpy().add(200, 50), new Vector2(-13, 4));
                EntityManager.getInstance().addEntity(laser6);
                shootSixth = false;
                Space.getInstance().getTimer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        shootSixth = true;
                    }
                }, rF5);
            }
            if(shootSeventh && toDraw == destroyer_left) {
                laser7 = new Laser(this, 5, getLocation().cpy().add(200, 100), new Vector2(-13, -4));
                EntityManager.getInstance().addEntity(laser7);
                shootSeventh = false;
                Space.getInstance().getTimer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        shootSeventh = true;
                    }
                }, rF6);
            }
            Random rand = new Random();
            randomFireTime = rand.nextInt(3000) + 2000;
            rF1 = rand.nextInt(3000) + 2000;
            rF2 = rand.nextInt(3000) + 2000;
            rF3 = rand.nextInt(3000) + 2000;
            rF4 = rand.nextInt(3000) + 2000;
            rF5 = rand.nextInt(3000) + 2000;
            rF6 = rand.nextInt(3000) + 2000;
            firstRandom = false;
            }
    }

    private void update() {
        if(EntityManager.getInstance().isPause()) {return;}
        if(isDead) {return;}
        if(initialMovement && enemyType == 1) {
            this.getLocation().x -= this.getVelocity().x/2 * Gdx.graphics.getDeltaTime();
            startingLocation.x += this.getVelocity().x/2 * Gdx.graphics.getDeltaTime();
            if(startingLocation.x  >200 ) {
                initialMovement = false;
                Random rand = new Random();
                fire = rand.nextInt(1000)+50;
                Space.getInstance().getTimer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        firstFire = true;
                    }
                },fire);
            }
        }
        else if(initialMovement && enemyType == 3) {
            this.getLocation().x -= this.getVelocity().x *10 * Gdx.graphics.getDeltaTime();
            startingLocation.x += this.getVelocity().x *10 * Gdx.graphics.getDeltaTime();
            if(startingLocation.x  >500 ) {
                initialMovement = false;
                Random rand = new Random();
                fire = rand.nextInt(2000)+1000;
                Space.getInstance().getTimer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        firstFire = true;
                    }
                },fire);
            }
        }
        else if(initialMovement && enemyType == 2) {
            if(hyperSpace) {
                this.getLocation().x += this.getVelocity().x * 10 * Gdx.graphics.getDeltaTime();
                if (this.getLocation().x >= 0) {
                    hyperSpace = false;
                    chainOn = true;
                    Space.getInstance().getTimer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            canFly = true;
                            firstFire = true;
                        }
                    }, 3000);
                }

            }
            if(right && canFly) {
                this.getLocation().x += this.getVelocity().x /2 * Gdx.graphics.getDeltaTime();
                startingLocation.x += this.getVelocity().x /2 * Gdx.graphics.getDeltaTime();
            }
            if(startingLocation.x  >SpaceShoooter.getWidth() +30) {
                toDraw = destroyer_left;
                right = false;
                startingLocation.x = 0;
            }
            if(!right && canFly) {
                this.getLocation().x -= this.getVelocity().x / 2 * Gdx.graphics.getDeltaTime();
                startingLocation.x += this.getVelocity().x/2 * Gdx.graphics.getDeltaTime();
                if (startingLocation.x > 500) {
                    initialMovement = false;
                }
            }
        }

        if(enemyType == 1) {
            if (!initialMovement && changeDir && (getLocation().y < (SpaceShoooter.getHeight() - toDraw.getHeight() - 5)) && (getLocation().x > 500)) {
                this.getLocation().y += this.getVelocity().y / 2 * Gdx.graphics.getDeltaTime();
                this.getLocation().x -= this.getVelocity().x / 8 * Space.getInstance().getLevel() * Gdx.graphics.getDeltaTime();
            }
            else if (!initialMovement && !changeDir && (getLocation().y > 5) && (getLocation().x > 500)) {
                this.getLocation().y -= this.getVelocity().y / 2 * Gdx.graphics.getDeltaTime();
                this.getLocation().x -= this.getVelocity().x / 8 * Gdx.graphics.getDeltaTime();
            }
        }
        else if(enemyType == 2) {
            if (!initialMovement && changeDir && (getLocation().y < (SpaceShoooter.getHeight() - toDraw.getHeight() - 5)))
                this.getLocation().y += this.getVelocity().y / 2 * Gdx.graphics.getDeltaTime();
            else if (!initialMovement && !changeDir && (getLocation().y > 5))
                this.getLocation().y -= this.getVelocity().y / 2 * Gdx.graphics.getDeltaTime();
        }
        else if(enemyType == 3) {
            if (!initialMovement && changeDir && (getLocation().y < (SpaceShoooter.getHeight() - toDraw.getHeight() - 5)))
                this.getLocation().y += this.getVelocity().y / 1.5 * Gdx.graphics.getDeltaTime();
            else if (!initialMovement && !changeDir && (getLocation().y > 5))
                this.getLocation().y -= this.getVelocity().y / 1.5 * Gdx.graphics.getDeltaTime();
        }
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
        else if(health<=0 && enemyType == 3) {
            if(e instanceof PlayerShip) {
                PlayerShip p = (PlayerShip) e;
                p.addScore(300);
            }
            destroyed();
        }
    }
    @Override
    public void dispose() {
        isDead = true;
        movmentTask.cancel();
        Space.getInstance().setEnemyNumber(Space.getInstance().getEnemyNumber()-1);
    }

    private void destroyed() {
        Explosion explosion = new Explosion(this.getLocation(), 3);
        EntityManager.getInstance().addEntity(explosion);
        EntityManager.getInstance().removeEntity(this);
    }

    private void locateEnemy(int x, int y) {
        float playerPosX = Space.getInstance().getPositionX();
        float playerPosY = Space.getInstance().getPositionY();
        float diffX = getLocation().x + x - playerPosX;
        float diffY = playerPosY - getLocation().y - y;
        float angle = (float) Math.atan(diffY / diffX);
        System.out.println(angle);
        fireDest = new Vector2(10, 10);
        fireDest.setAngleRad(angle);
        fireDest.x = -fireDest.x;
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

    private void setHealth(int health) {
        this.health = health;
    }

    public boolean isDead() {
        return isDead;
    }

    public int getEnemyType() {
        return enemyType;
    }

    public boolean isChainOn() {
        return chainOn;
    }

}

