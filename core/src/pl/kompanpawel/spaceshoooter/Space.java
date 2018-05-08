package pl.kompanpawel.spaceshoooter;

import com.badlogic.gdx.math.Rectangle;

public class Space {
    private static Space instance = new Space();

    public static Space getInstance() { return instance; }
    private EntityManager entityManager = EntityManager.getInstance();


    private Enemy enemy1 = (Enemy) EntityFactory.factorTIE1();
    private Enemy enemy2 = (Enemy) EntityFactory.factorTIE2();
    private Enemy enemy3 = (Enemy) EntityFactory.factorTIE3();
    private Enemy enemy4 = (Enemy) EntityFactory.factorTIE4();
    private Enemy enemy5 = (Enemy) EntityFactory.factorTIE5();

    public void addEnemies() {
        entityManager.addEntity(enemy1);
        entityManager.addEntity(enemy2);
        entityManager.addEntity(enemy3);
        entityManager.addEntity(enemy4);
        entityManager.addEntity(enemy5);
    }

    public void enemyMovement() {
        enemy1.fire();
        enemy2.fire();
        enemy3.fire();
        enemy4.fire();
        enemy5.fire();
        enemy1.update();
        enemy2.update();
        enemy3.update();
        enemy4.update();
        enemy5.update();
    }

    public void destroyEnemy() {
        if(enemy1.getHealth()<=0)
            enemy1.destroyed();
        if(enemy2.getHealth()<=0)
            enemy2.destroyed();
        if(enemy3.getHealth()<=0)
            enemy3.destroyed();
        if(enemy4.getHealth()<=0)
            enemy4.destroyed();
        if(enemy5.getHealth()<=0)
            enemy5.destroyed();
    }

    public void laserCollisions(Laser laser) {
        for (Entity ent : entityManager.getEntities()) {
            if (ent instanceof PlayerShip) {
                PlayerShip playerShip = (PlayerShip) ent;
                if(playerShip.isDead())
                    continue;
                Rectangle playerShipRect = new Rectangle(Math.round(playerShip.getLocation().x), Math.round(playerShip.getLocation().y), playerShip.getWidth()-10, playerShip.getHeight());
                Rectangle laserRect = new Rectangle(Math.round(laser.getLocation().x), Math.round(laser.getLocation().y), laser.getWidth(), laser.getHeight());
                if (playerShipRect.overlaps(laserRect) && laser.getOwner() instanceof Enemy) {
                    laser.hit(playerShip);
                    playerShip.getHited();
                    return;
                }
            }
            if (ent instanceof Enemy) {
                Enemy enemy = (Enemy) ent;
                if(enemy.isDead())
                    continue;
                Rectangle enemyRect = new Rectangle(Math.round(enemy.getLocation().x), Math.round(enemy.getLocation().y),enemy.getWidth()-10, enemy.getHeight());
                Rectangle laserRect = new Rectangle(Math.round(laser.getLocation().x), Math.round(laser.getLocation().y), laser.getWidth(), laser.getHeight());
                if(enemyRect.overlaps(laserRect) && laser.getOwner() instanceof PlayerShip) {
                    laser.hit(enemy);
                    enemy.getHited();
                    return;
                }
            }
        }
    }
}
