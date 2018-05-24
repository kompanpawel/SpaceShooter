package pl.kompanpawel.spaceshoooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class HelpScreen implements Screen {

    private Stage stage;
    private Table table;
    private SpriteBatch spriteBatch;

    Texture background;

    SpaceShoooter game;

    public HelpScreen(SpaceShoooter game) {
        this.game = game;
        setCamera();
        setSprite();
    }

    private void setCamera(){
        SpaceShoooter.getCamera().setToOrtho(false, 1280,720);
    }

    private void setSprite() {
        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(SpaceShoooter.getCamera().combined);
    }


    @Override
    public void show() {
        background = new Texture("backgrounds/Parallax100.png");
        stage = new Stage(new StretchViewport(SpaceShoooter.getCamera().viewportWidth, SpaceShoooter.getCamera().viewportHeight));

        table = new Table(game.assetManager.get("fonts and others/uiskin.json", Skin.class));
        table.setFillParent(true);

        Label header = new Label("How to play", game.assetManager.get("fonts and others/uiskin.json", Skin.class));
        Label controls = new Label("Controls", game.assetManager.get("fonts and others/uiskin.json", Skin.class));
        Label steering1 = new Label("Player 1:\nUp Key - steering up\nDown Key - steering down\nLeft Key - steering left\nRight Key - steering right\nRight Control - firing", game.assetManager.get("fonts and others/uiskin.json", Skin.class));
        Label steering2 = new Label("Player 2:\nW - steering up\nS - steering down\nA - steering left\nD - steering right\nLeftControl - firing", game.assetManager.get("fonts and others/uiskin.json", Skin.class));
        Label pauseInfo = new Label("Pausing game", game.assetManager.get("fonts and others/uiskin.json", Skin.class));
        Label pause = new Label("To pause game press ESC, to unpause, press ESC again(warning, unpause at least 3 sec after pausing, temporary instability)", game.assetManager.get("fonts and others/uiskin.json", Skin.class));
        Label save = new Label("Saving/loading game", game.assetManager.get("fonts and others/uiskin.json", Skin.class));
        Label saveText = new Label("//TODO", game.assetManager.get("fonts and others/uiskin.json", Skin.class));
        //table.debug();
        table.add(header).expand().colspan(2).row();
        table.add(controls).colspan(2).row();
        table.add(steering1).expand().width(300).height(300);
        table.add(steering2).expand().width(300).height(300).row();
        table.add(pauseInfo).expand().colspan(2).row();
        table.add(pause).colspan(2).expand().width(1000).height(200).row();
        table.add(save).colspan(2).row();
        table.add(saveText).colspan(2);
        Gdx.input.setInputProcessor(stage);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            game.setScreen(new MainMenu(game));
        spriteBatch.begin();
        spriteBatch.draw(background, 0,0,SpaceShoooter.getCamera().viewportWidth, SpaceShoooter.getCamera().viewportHeight);
        spriteBatch.end();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height);
        table.setSize(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
