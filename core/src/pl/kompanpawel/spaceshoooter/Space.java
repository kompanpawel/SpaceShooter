package pl.kompanpawel.spaceshoooter;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.FloatArray;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Space {
    private static Space instance = new Space();

    private int level = 1;
    private int enemyNumber = 0;
    private int wave = 0;
    private int type = 0;

    private long delay = 4000;

    private Timer timer;

    private boolean stop = true;

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
            entityManager.addEntity(EntityFactory.factorDestroyer());
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



    public void laserCollisions(Laser laser) {
        for (Entity ent : entityManager.getEntities()) {
            if (ent instanceof PlayerShip) {
                PlayerShip playerShip = (PlayerShip) ent;
                if(playerShip.isDead())
                    continue;
                playerPoly = new Polygon(new float[]{playerShip.getLocation().x, playerShip.getLocation().y,playerShip.getLocation().x + playerShip.getWidth(), playerShip.getLocation().y,playerShip.getLocation().x, playerShip.getLocation().y + playerShip.getHeight(), playerShip.getLocation().x + playerShip.getWidth(), playerShip.getLocation().y + playerShip.getHeight()});
                Polygon laserPoly = new Polygon(new float[]{laser.getLocation().x, laser.getLocation().y,laser.getLocation().x +laser.getWidth(), laser.getLocation().y, laser.getLocation().x, laser.getLocation().y + laser.getHeight(), laser.getLocation().x + laser.getWidth(), laser.getLocation().y+laser.getHeight()});
                if (Intersector.overlapConvexPolygons(playerPoly, laserPoly ) && laser.getOwner() instanceof Enemy) {
                    laser.hit(playerShip);
                    System.out.println(playerShip.getLocation().x + playerShip.getWidth());
                    System.out.println(playerShip.getLocation().y + playerShip.getHeight());

                    playerShip.getHited();
                    return;
                }
            }

            if (ent instanceof Enemy) {
                Enemy enemy = (Enemy) ent;
                if (enemy.isDead())
                    continue;
                if (((Enemy) ent).getEnemyType() == 2) {
                    execPoly = new Polygon(new float[]{enemy.getLocation().x + 348, enemy.getLocation().y + enemy.getHeight(), enemy.getLocation().x + 434, enemy.getLocation().y + enemy.getHeight() - 60, enemy.getLocation().x + 434, enemy.getLocation().y + enemy.getHeight() - 114, enemy.getLocation().x + 350, enemy.getLocation().y + enemy.getHeight() - 175, enemy.getLocation().x +3, enemy.getLocation().y + enemy.getHeight() - 97});
                } else {
                    enemyPoly = new Polygon(new float[]{enemy.getLocation().x, enemy.getLocation().y,enemy.getLocation().x + enemy.getWidth(), enemy.getLocation().y,enemy.getLocation().x, enemy.getLocation().y + enemy.getHeight(), enemy.getLocation().x + enemy.getWidth(), enemy.getLocation().y + enemy.getHeight()});
                    //enemyRect = new Rectangle(Math.round(enemy.getLocation().x), Math.round(enemy.getLocation().y),enemy.getWidth()-10, enemy.getHeight());
                }
                Rectangle laserRect = new Rectangle(Math.round(laser.getLocation().x), Math.round(laser.getLocation().y), laser.getWidth(), laser.getHeight());
                Polygon laserPoly = new Polygon(new float[]{laser.getLocation().x, laser.getLocation().y,laser.getLocation().x +laser.getWidth(), laser.getLocation().y, laser.getLocation().x, laser.getLocation().y + laser.getHeight(), laser.getLocation().x + laser.getWidth(), laser.getLocation().y+laser.getHeight()});
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
}
