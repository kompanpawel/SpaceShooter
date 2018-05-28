package pl.kompanpawel.spaceshoooter.Saving;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import pl.kompanpawel.spaceshoooter.Space.Space;

import java.util.Map;

public class SaveController implements Preferences {


    public Preferences prefs = Gdx.app.getPreferences("Game_saved_data");

    private int counter = 1;

    public SaveController() {}

    public void saveFile() {
        prefs.putFloat("Player_position_X", GameData.getInstance().getPlayerPosX());
        prefs.putFloat("Player_position_Y", GameData.getInstance().getPlayerPosY());
        prefs.putInteger("Player_health", GameData.getInstance().getPlayerHealth());
        prefs.putInteger("Player_score", GameData.getInstance().getPlayerScore());
        if(Space.getInstance().isCoop()) {
            prefs.putFloat("Player2_position_X", GameData.getInstance().getPlayer2PosX());
            prefs.putFloat("Player2_position_Y", GameData.getInstance().getPlayer2PosY());
            prefs.putInteger("Player2_health", GameData.getInstance().getPlayerHealth());
            prefs.putInteger("Player2_score", GameData.getInstance().getPlayerScore());
        }
        for(int i = 0; i<GameData.getInstance().getEnemiesPositions().size(); i+=2) {
            prefs.putFloat("Enemy_" + counter + "_position_X", GameData.getInstance().getEnemiesPositions().get(i));
            prefs.putFloat("Enemy_" + counter + "_position_Y", GameData.getInstance().getEnemiesPositions().get(i+1));
            counter++;
        }
        for(int i = 0; i<GameData.getInstance().getEnemiesHealth().size();i++) {
            prefs.putInteger("Enemy_" + i + "_health", GameData.getInstance().getEnemiesHealth().get(i));
        }
        prefs.putInteger("Enemies_number", GameData.getInstance().getEnemiesNumber());
        prefs.putInteger("Level", GameData.getInstance().getLevel());
        prefs.putInteger("Wave", GameData.getInstance().getWave());
        prefs.putInteger("Type", GameData.getInstance().getType());
        prefs.putBoolean("Chain", GameData.getInstance().isChain());
        prefs.putBoolean("Stop", GameData.getInstance().isStop());
        prefs.putBoolean("Player_can_shoot", GameData.getInstance().isPlayerCanShoot());
        prefs.putBoolean("IsCoop", GameData.getInstance().isCoop());
        prefs.flush();
        System.out.println("saved");
    }

    @Override
    public Preferences putBoolean(String key, boolean val) {
        return null;
    }

    @Override
    public Preferences putInteger(String key, int val) {
        return null;
    }

    @Override
    public Preferences putLong(String key, long val) {
        return null;
    }

    @Override
    public Preferences putFloat(String key, float val) {
        return null;
    }

    @Override
    public Preferences putString(String key, String val) {
        return null;
    }

    @Override
    public Preferences put(Map<String, ?> vals) {
        return null;
    }

    @Override
    public boolean getBoolean(String key) {
        return false;
    }

    @Override
    public int getInteger(String key) {
        return 0;
    }

    @Override
    public long getLong(String key) {
        return 0;
    }

    @Override
    public float getFloat(String key) {
        return 0;
    }

    @Override
    public String getString(String key) {
        return null;
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        return false;
    }

    @Override
    public int getInteger(String key, int defValue) {
        return 0;
    }

    @Override
    public long getLong(String key, long defValue) {
        return 0;
    }

    @Override
    public float getFloat(String key, float defValue) {
        return 0;
    }

    @Override
    public String getString(String key, String defValue) {
        return null;
    }

    @Override
    public Map<String, ?> get() {
        return null;
    }

    @Override
    public boolean contains(String key) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public void remove(String key) {

    }

    @Override
    public void flush() {

    }
}


