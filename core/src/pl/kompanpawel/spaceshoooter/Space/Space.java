package pl.kompanpawel.spaceshoooter.Space;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import pl.kompanpawel.spaceshoooter.Entities.*;
import pl.kompanpawel.spaceshoooter.Saving.GameData;

import pl.kompanpawel.spaceshoooter.SpaceShoooter;

import java.util.Timer;
import java.util.TimerTask;


public class Space {
    private static Space instance = new Space();

    private int level = 1;
    private int enemyNumber = 0;
    private int wave = 0;
    private int type = 1;


    private long delay = 4000;

    private Timer timer = new Timer();

    private boolean stop = true;
    private boolean chain = false;
    private boolean playerCanShoot = true;

    private boolean isCoop = false;
    private boolean showTextSaved = false;


    private float locX;
    private float locY;

    private Polygon execPoly;
    private Polygon enemyPoly;
    private Polygon playerPoly;
    private Polygon laserPoly;
    private Polygon destPoly;

    public static Space getInstance() { return instance; }
    private EntityManager entityManager = EntityManager.getInstance();

    public float getPositionX() {
        for (Entity ent : entityManager.getEntities()) {
            if(ent instanceof PlayerShip) {
                PlayerShip playerShip = (PlayerShip) ent;
                locX = playerShip.getLocation().x;
            }
        }
        return locX;

    }
    public float getPositionY() {
        for (Entity ent : entityManager.getEntities()) {
            if(ent instanceof PlayerShip) {
                PlayerShip playerShip = (PlayerShip) ent;
                locY = playerShip.getLocation().y;
            }
        }
        return locY;
    }

    public void changeTextFlag() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                showTextSaved = false;
            }
        },3000);
    }
    public void addEnemiesAfterLoad() {
        if(type == 0) {
            for(int i = 0; i< enemyNumber*2; i+=2) {
                entityManager.addEntity(EntityFactory.factorCustomTie(new Vector2(GameData.getInstance().getEnemiesPositions().get(i),GameData.getInstance().getEnemiesPositions().get(i+1)),new Vector2(200,20)));
            }
        }
        else if(type == 1) {
            for (int i = 0; i < enemyNumber * 2; i += 2) {
                entityManager.addEntity(EntityFactory.factorCustomDestroyer(new Vector2(GameData.getInstance().getEnemiesPositions().get(i), GameData.getInstance().getEnemiesPositions().get(i + 1)), new Vector2(200, 20)));
            }
        }
        else if(type == 2)
            entityManager.addEntity(EntityFactory.factorCustomExecutor(new Vector2(GameData.getInstance().getEnemiesPositions().get(0), GameData.getInstance().getEnemiesPositions().get(1)), new Vector2(200, 20)));
    }

    public void addEnemies() {
        if(type == 0) {
            entityManager.addEntity(EntityFactory.factorTIE1());
            enemyNumber++;
            entityManager.addEntity(EntityFactory.factorTIE2());
            enemyNumber++;
            entityManager.addEntity(EntityFactory.factorTIE3());
            enemyNumber++;
            entityManager.addEntity(EntityFactory.factorTIE4());
            enemyNumber++;
            entityManager.addEntity(EntityFactory.factorTIE5());
            enemyNumber++;
            entityManager.addEntity(EntityFactory.factorTIE6());
            enemyNumber++;
            entityManager.addEntity(EntityFactory.factorTIE7());
            enemyNumber++;
            entityManager.addEntity(EntityFactory.factorTIE8());
            enemyNumber++;
            entityManager.addEntity(EntityFactory.factorTIE9());
            enemyNumber++;
            entityManager.addEntity(EntityFactory.factorTIE10());
            enemyNumber++;
            wave++;
        }
        else if(type == 1) {
            entityManager.addEntity(EntityFactory.factorDestroyer1());
            enemyNumber++;
            entityManager.addEntity(EntityFactory.factorDestroyer2());
            enemyNumber++;
            type = 0;
            chain = true;
        }
        else if(type == 2) {
            entityManager.addEntity(EntityFactory.factorExecutor());
            enemyNumber++;
            type = 0;
        }
    }

    public void newWaveEnemies() {
        if(!stop) {return;}
        stop = false;
        timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    addEnemies();
                    stop = true;
                }
            },delay);
        delay = 4000;

        if(wave > 4 && level == 1) {
            type = 1;
            delay = 10000;
            wave = 0;
            level = 2;
        }
        if(wave > 4 && level == 2)
        {
            type = 2;
            delay = 10000;
            wave = 0;
            level = 3;
        }
    }

    public void restrictArea (Enemy enemy) {
        for (Entity ent : entityManager.getEntities())
            if( ent instanceof PlayerShip && chain && enemy.isChainOn()) {
                PlayerShip playerShip = (PlayerShip) ent;
                playerCanShoot = false;
                if(playerShip.getLocation().x < enemy.getLocation().x)
                    playerShip.setLocation(new Vector2(enemy.getLocation().x, playerShip.getLocation().y));
                if(playerShip.getLocation().x > enemy.getLocation().x+enemy.getWidth())
                    playerShip.setLocation(new Vector2(enemy.getLocation().x+enemy.getWidth(), playerShip.getLocation().y));
                if(playerShip.getLocation().y < 230)
                    playerShip.setLocation(new Vector2(playerShip.getLocation().x, 230));
                if(playerShip.getLocation().y > 480)
                    playerShip.setLocation(new Vector2(playerShip.getLocation().x, 480));
                if(enemy.getLocation().x > 800) {
                    chain = false;
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            playerCanShoot = true;
                        }
                    },8000);
                }
            }
    }
    public void drawShip () {
        ShapeRenderer shapeRenderer;
        if(playerPoly!=null) {
            shapeRenderer = new ShapeRenderer();
            shapeRenderer.setAutoShapeType(true);
            shapeRenderer.setProjectionMatrix(SpaceShoooter.getCamera().combined);
            shapeRenderer.begin();
            shapeRenderer.polygon(playerPoly.getTransformedVertices());
            shapeRenderer.end();
        }
        if(enemyPoly!= null) {
            shapeRenderer = new ShapeRenderer();
            shapeRenderer.setAutoShapeType(true);
            shapeRenderer.setProjectionMatrix(SpaceShoooter.getCamera().combined);
            shapeRenderer.begin();
            shapeRenderer.polygon(enemyPoly.getTransformedVertices());
            shapeRenderer.end();
        }
        if(laserPoly!=null) {
            shapeRenderer = new ShapeRenderer();
            shapeRenderer.setAutoShapeType(true);
            shapeRenderer.setProjectionMatrix(SpaceShoooter.getCamera().combined);
            shapeRenderer.begin();
            shapeRenderer.polygon(laserPoly.getTransformedVertices());
            shapeRenderer.end();
        }
        if(destPoly!= null) {
            shapeRenderer = new ShapeRenderer();
            shapeRenderer.setAutoShapeType(true);
            shapeRenderer.setProjectionMatrix(SpaceShoooter.getCamera().combined);
            shapeRenderer.begin();
            shapeRenderer.polygon(destPoly.getTransformedVertices());
            shapeRenderer.end();
        }
    }

    public void laserCollisions(Laser laser) {
        for (Entity ent : entityManager.getEntities()) {
            if(laser.getOwner() instanceof Enemy) {
                laserPoly = new Polygon(new float[]{
                        laser.getLocation().x,
                        laser.getLocation().y,
                        laser.getLocation().x +laser.getWidth(),
                        laser.getLocation().y,
                        laser.getLocation().x + laser.getWidth(),
                        laser.getLocation().y+ laser.getHeight(),
                        laser.getLocation().x,
                        laser.getLocation().y + laser.getHeight()
                });
                    laserPoly.setOrigin(laser.getLocation().x , laser.getLocation().y);
                    laserPoly.rotate(laser.getVelocity().angle());

            }
            else {
                laserPoly = new Polygon(new float[]{
                        laser.getLocation().x,
                        laser.getLocation().y,
                        laser.getLocation().x + laser.getWidth(),
                        laser.getLocation().y,
                        laser.getLocation().x + laser.getWidth(),
                        laser.getLocation().y + laser.getHeight(),
                        laser.getLocation().x,
                        laser.getLocation().y + laser.getHeight()
                });
            }

            Explosion explosion;
            if (ent instanceof PlayerShip) {
                PlayerShip playerShip = (PlayerShip) ent;
                if(playerShip.isDead())
                    continue;
                playerPoly = new Polygon(new float[]{
                        playerShip.getLocation().cpy().x+14,
                        playerShip.getLocation().cpy().y,
                        playerShip.getLocation().cpy().x + playerShip.getWidth(),
                        playerShip.getLocation().cpy().y+20,
                        playerShip.getLocation().cpy().x+14,
                        playerShip.getLocation().cpy().y +playerShip.getHeight(),
                        playerShip.getLocation().cpy().x,
                        playerShip.getLocation().cpy().y + 25,
                        playerShip.getLocation().cpy().x,
                        playerShip.getLocation().cpy().y + 15,
                });

                if (Intersector.overlapConvexPolygons(laserPoly, playerPoly ) && laser.getOwner() instanceof Enemy) {
                    explosion = new Explosion(laser.getLocation(), 20);
                    EntityManager.getInstance().addEntity(explosion);
                    laser.hit(playerShip);
                    playerShip.getHited();
                    return;
                }
            }

            if (ent instanceof Enemy) {
                Enemy enemy = (Enemy) ent;
                if (enemy.isDead())
                    continue;
                if (((Enemy) ent).getEnemyType() == 3) {
                    execPoly = new Polygon(new float[]{
                            enemy.getLocation().x + 348,
                            enemy.getLocation().y + enemy.getHeight(),
                            enemy.getLocation().x + 434,
                            enemy.getLocation().y + enemy.getHeight() - 60,
                            enemy.getLocation().x + 434,
                            enemy.getLocation().y + enemy.getHeight() - 114,
                            enemy.getLocation().x + 350,
                            enemy.getLocation().y + enemy.getHeight() - 175,
                            enemy.getLocation().x +3,
                            enemy.getLocation().y + enemy.getHeight() - 97
                    });
                }
                else if(((Enemy) ent).getEnemyType() == 2) {
                    destPoly = new Polygon(new float[]{
                            enemy.getLocation().x + 378,
                            enemy.getLocation().y + enemy.getHeight(),
                            enemy.getLocation().x + 434,
                            enemy.getLocation().y + enemy.getHeight() - 60,
                            enemy.getLocation().x + 434,
                            enemy.getLocation().y + enemy.getHeight() - 114,
                            enemy.getLocation().x + 378,
                            enemy.getLocation().y + enemy.getHeight() - 225,
                            enemy.getLocation().x +3,
                            enemy.getLocation().y + enemy.getHeight() - 117
                    });
                }
                else {
                    enemyPoly = new Polygon(new float[]{
                            enemy.getLocation().x,
                            enemy.getLocation().y,
                            enemy.getLocation().x + enemy.getWidth(),
                            enemy.getLocation().y,
                            enemy.getLocation().x + enemy.getWidth(),
                            enemy.getLocation().y + enemy.getHeight(),
                            enemy.getLocation().x,
                            enemy.getLocation().y + enemy.getHeight()

                    });
                }
                if((enemyPoly!=null &&(Intersector.overlapConvexPolygons(enemyPoly, laserPoly) && laser.getOwner() instanceof PlayerShip)) ||(execPoly!=null && (Intersector.overlapConvexPolygons(laserPoly,execPoly) && laser.getOwner() instanceof PlayerShip)) || (destPoly!=null && (Intersector.overlapConvexPolygons(laserPoly,destPoly) && laser.getOwner() instanceof PlayerShip))) {
                    explosion = new Explosion(laser.getLocation(), 2);
                    EntityManager.getInstance().addEntity(explosion);
                    laser.hit(enemy);
                    enemy.getHited(laser.getOwner());
                    return;
                }
            }
        }
    }



    public int getEnemyNumber() {
        return enemyNumber;
    }

    public void setEnemyNumber(int enemyNumber) {
        this.enemyNumber = enemyNumber;
    }

    public int getWave() {
        return wave;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isStop() {
        return stop;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isChain() {
        return chain;
    }

    public boolean isPlayerCanShoot() {
        return playerCanShoot;
    }

    public Timer getTimer () {
        return timer;
    }

    public boolean isCoop() {
        return isCoop;
    }

    public void setCoop(boolean coop) {
        isCoop = coop;
    }

    public boolean isShowTextSaved() {
        return showTextSaved;
    }

    public void setShowTextSaved(boolean showTextSaved) {
        this.showTextSaved = showTextSaved;
    }
}
