package pl.kompanpawel.spaceshoooter;

import com.badlogic.gdx.InputProcessor;

import java.util.Observable;
public class ControllerManager extends Observable implements InputProcessor {
    private static ControllerManager ourInstance = new ControllerManager();

    public static ControllerManager getInstance() {
        return ourInstance;
    }

    private ControllerManager() {
    }

    @Override
    public boolean keyDown(int keycode) {
        notifyObservers(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        notifyObservers(keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
