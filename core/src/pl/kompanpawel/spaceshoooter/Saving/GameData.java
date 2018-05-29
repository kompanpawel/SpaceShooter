package pl.kompanpawel.spaceshoooter.Saving;

import pl.kompanpawel.spaceshoooter.Entities.Enemy;
import pl.kompanpawel.spaceshoooter.Entities.Entity;
import pl.kompanpawel.spaceshoooter.Entities.EntityManager;
import pl.kompanpawel.spaceshoooter.Entities.PlayerShip;
import pl.kompanpawel.spaceshoooter.Space.Space;

import java.util.ArrayList;
import java.util.List;

public class GameData {
    private static GameData instance = new GameData();
    private EntityManager entityManager = EntityManager.getInstance();
    private SaveController save = new SaveController();

    private float playerPosX;
    private float playerPosY;
    private int playerHealth;
    private int playerScore;

    private float player2PosX;
    private float player2PosY;
    private int player2Health;
    private int player2Score;

    private int enemiesNumber;
    private int level;
    private int wave;
    private int type;

    private boolean stop;
    private boolean chain;
    private boolean playerCanShoot;
    private boolean isCoop;

    private int counter = 1;

    private List <Float> enemiesPositions = new ArrayList<Float>();
    private List <Integer> enemiesHealth = new ArrayList<Integer>();


    public static GameData getInstance() {return instance;}

    public void getGameData() {
        for(Entity ent : entityManager.getEntities()) {
            if(ent instanceof PlayerShip) {
               PlayerShip playerShip = (PlayerShip) ent;
               if(playerShip.getPlayerNumber() == 1) {
                   playerPosX = playerShip.getLocation().x;
                   playerPosY = playerShip.getLocation().y;
                   playerHealth = playerShip.getHealth();
                   playerScore = playerShip.getScore();
               }
               if(playerShip.getPlayerNumber() == 2) {
                   player2PosX = playerShip.getLocation().x;
                   player2PosY = playerShip.getLocation().y;
                   player2Health = playerShip.getHealth();
                   player2Score = playerShip.getScore();
               }
            }
            if(ent instanceof Enemy) {
                Enemy enemy = (Enemy) ent;
                enemiesPositions.add(enemy.getLocation().x);
                enemiesPositions.add(enemy.getLocation().y);
                enemiesHealth.add(enemy.getHealth());
            }
            enemiesNumber = Space.getInstance().getEnemyNumber();
            level = Space.getInstance().getLevel();
            wave = Space.getInstance().getWave();
            type = Space.getInstance().getType();
            stop = Space.getInstance().isStop();
            chain = Space.getInstance().isStop();
            playerCanShoot = Space.getInstance().isPlayerCanShoot();
            isCoop = Space.getInstance().isCoop();
        }
        save.saveFile();
    }

    public void loadGameData() {
        playerPosX = save.prefs.getFloat("Player_position_X", 20);
        playerPosY = save.prefs.getFloat("Player_position_Y",360);
        playerHealth = save.prefs.getInteger("Player_health",10);
        playerScore = save.prefs.getInteger("Player_score");
        if(Space.getInstance().isCoop()) {
            player2PosX = save.prefs.getFloat("Player2_position_X", 20);
            player2PosY = save.prefs.getFloat("Player2_position_Y",360);
            player2Health = save.prefs.getInteger("Player2_health",10);
            player2Score = save.prefs.getInteger("Player2_score");
        }
        enemiesNumber = save.prefs.getInteger("Enemies_number");
        for(int i = 0; i<enemiesNumber; i++) {
            enemiesPositions.add(save.prefs.getFloat("Enemy_" + counter + "_position_X"));
            enemiesPositions.add(save.prefs.getFloat("Enemy_" + counter + "_position_Y"));
            counter++;
        }
        for(int i = 0; i<enemiesNumber; i++) {
            enemiesHealth.add(save.prefs.getInteger("Enemy_" + i + "_health"));
        }
        level = save.prefs.getInteger("Level");
        wave = save.prefs.getInteger("Wave");
        type = save.prefs.getInteger("Type");
        stop = save.prefs.getBoolean("Stop");
        chain = save.prefs.getBoolean("Chain");
        playerCanShoot = save.prefs.getBoolean("Player_can_shoot");
        isCoop = save.prefs.getBoolean("IsCoop");

    }

    public float getPlayerPosX() {
        return playerPosX;
    }
    public float getPlayerPosY() {
        return playerPosY;
    }
    public int getEnemiesNumber() {
        return enemiesNumber;
    }
    public int getLevel() {
        return level;
    }
    public int getWave() {
        return wave;
    }
    public int getType() {
        return type;
    }
    boolean isStop() {
        return stop;
    }
    boolean isChain() {
        return chain;
    }
    boolean isPlayerCanShoot() {
        return playerCanShoot;
    }
    public List<Float> getEnemiesPositions() {
        return enemiesPositions;
    }
    public boolean isCoop() {
        return isCoop;
    }
    public float getPlayer2PosX() {
        return player2PosX;
    }
    public float getPlayer2PosY() {
        return player2PosY;
    }
    public int getPlayerHealth() {
        return playerHealth;
    }
    public int getPlayerScore() {
        return playerScore;
    }
    public int getPlayer2Health() {
        return player2Health;
    }
    public int getPlayer2Score() {
        return player2Score;
    }
    List<Integer> getEnemiesHealth() {
        return enemiesHealth;
    }
}
