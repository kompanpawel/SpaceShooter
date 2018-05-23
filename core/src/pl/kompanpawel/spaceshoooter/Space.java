package pl.kompanpawel.spaceshoooter;


import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;

import com.badlogic.gdx.math.Vector2;



import java.util.Timer;
import java.util.TimerTask;

public class Space {
    private static Space instance = new Space();

    private int level = 1;
    private int enemyNumber = 0;
    private int wave = 0;
    private int type = 1;


    private long delay = 4000;

    private Timer timer;

    private boolean stop = true;
    private boolean chain = false;
    private boolean playerCanShoot = true;

    private Polygon execPoly;
    private Polygon enemyPoly;
    private Polygon playerPoly;


    public static Space getInstance() { return instance; }
    private EntityManager entityManager = EntityManager.getInstance();


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
        timer = new Timer();
        timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    addEnemies();
                    timer.cancel();
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
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            playerCanShoot = true;
                        }
                    },8000);
                }
            }
    }

    public void laserCollisions(Laser laser) {
        for (Entity ent : entityManager.getEntities()) {
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
                Polygon laserPoly = new Polygon(new float[]{
                        laser.getLocation().x,
                        laser.getLocation().y,
                        laser.getLocation().x +laser.getWidth(),
                        laser.getLocation().y,
                        laser.getLocation().x,
                        laser.getLocation().y + laser.getHeight(),
                        laser.getLocation().x + laser.getWidth(),
                        laser.getLocation().y+laser.getHeight()
                });
                if (Intersector.overlapConvexPolygons(laserPoly, playerPoly ) && laser.getOwner() instanceof Enemy) {
                    laser.hit(playerShip);
                    System.out.println(playerShip.getLocation().x);
                    System.out.println(playerShip.getLocation().y);

                    playerShip.getHited();
                    return;
                }
            }

            if (ent instanceof Enemy) {
                Enemy enemy = (Enemy) ent;
                if (enemy.isDead())
                    continue;
                if (((Enemy) ent).getEnemyType() == 2 || ((Enemy) ent).getEnemyType() == 3) {
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
                } else {
                    enemyPoly = new Polygon(new float[]{
                            enemy.getLocation().x,
                            enemy.getLocation().y,
                            enemy.getLocation().x + enemy.getWidth(),
                            enemy.getLocation().y,
                            enemy.getLocation().x,
                            enemy.getLocation().y + enemy.getHeight(),
                            enemy.getLocation().x + enemy.getWidth(),
                            enemy.getLocation().y + enemy.getHeight()
                    });
                    //enemyRect = new Rectangle(Math.round(enemy.getLocation().x), Math.round(enemy.getLocation().y),enemy.getWidth()-10, enemy.getHeight());
                }
                Polygon laserPoly = new Polygon(new float[]{
                        laser.getLocation().x,
                        laser.getLocation().y,
                        laser.getLocation().x +laser.getWidth(),
                        laser.getLocation().y,
                        laser.getLocation().x,
                        laser.getLocation().y + laser.getHeight(),
                        laser.getLocation().x + laser.getWidth(),
                        laser.getLocation().y+laser.getHeight()
                });
                if((enemyPoly!=null &&(Intersector.overlapConvexPolygons(enemyPoly, laserPoly) && laser.getOwner() instanceof PlayerShip)) ||(execPoly!=null && (Intersector.overlapConvexPolygons(laserPoly,execPoly) && laser.getOwner() instanceof PlayerShip))) {
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

    public void setStop(boolean stop) {
        this.stop = stop;
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

    public void setChain(boolean chain) {
        this.chain = chain;
    }

    public boolean isPlayerCanShoot() {
        return playerCanShoot;
    }
}
