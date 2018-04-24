package pl.kompanpawel.spaceshoooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class MainMenu implements Screen {

    private Stage stage;
    private Table table;
    private TextButton playButton;

    Texture background;


    SpaceShoooter game;

    public MainMenu(SpaceShoooter game){
        this.game = game;

    }

    @Override
    public void show() {
        background = new Texture("starfield.png");
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        playButton = new TextButton("Play",  game.assetManager.get("uiskin.json", Skin.class));
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
            }
        });
        table = new Table(game.assetManager.get("uiskin.json", Skin.class));

        table.setFillParent(true);
        Label title = new Label("SpaceShoooter", game.assetManager.get("uiskin.json", Skin.class));
        //table.debug();
        table.add(title).row();
        table.add(playButton).expand().width(400).height(50);
        table.setTouchable(Touchable.childrenOnly);
        Gdx.input.setInputProcessor(stage);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        stage.act(delta);
        stage.draw();
        //game.batch.draw(background, 0,0);
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {

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
