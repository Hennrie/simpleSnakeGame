package com.snake.screen.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.snake.SimpleSnakeGame;
import com.snake.common.EntityFactory;
import com.snake.common.GameManager;
import com.snake.config.GameConfig;
import com.snake.screen.menu.MenuScreen;
import com.snake.system.BoundsSystem;
import com.snake.system.CoinSystem;
import com.snake.system.CollisionSystem;
import com.snake.system.DirectionSystem;
import com.snake.system.PlayerControlSystem;
import com.snake.system.RenderSystem;
import com.snake.system.SnakeMovementSystem;
import com.snake.system.WorldWrapSystem;
import com.snake.system.debug.DebugCameraSystem;
import com.snake.system.debug.DebugRenderSystem;
import com.snake.system.debug.GridRenderSystem;
import com.snake.system.passive.SnakeSystem;
import com.snake.util.GdxUtils;

public class GameScreen extends ScreenAdapter {

    // == constants ==
    private static final Logger log = new Logger(GameScreen.class.getName(), Logger.DEBUG);
    // == attributes ==
    private final SimpleSnakeGame game;
    private final AssetManager assetManager;

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;
    private PooledEngine engine;
    private EntityFactory factory;

    private Entity snake;

    // == constructors ==

    public GameScreen(SimpleSnakeGame game) {
        this.game = game;
        assetManager = game.getAssetManager();
    }


    // == private methods ==

    // == public methods ==


    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        renderer = new ShapeRenderer();
        engine = new PooledEngine();
        factory = new EntityFactory(engine, assetManager);

        engine.addSystem(new GridRenderSystem(viewport, renderer));
        engine.addSystem(new DebugCameraSystem(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y,
                camera));

        engine.addSystem(new DebugRenderSystem(viewport, renderer));
        engine.addSystem(new SnakeSystem());
        engine.addSystem(new DirectionSystem());
        engine.addSystem(new SnakeMovementSystem());
        engine.addSystem(new BoundsSystem());
        engine.addSystem(new PlayerControlSystem());
        engine.addSystem(new WorldWrapSystem());
        engine.addSystem(new CoinSystem());
        engine.addSystem(new CollisionSystem(factory));
        engine.addSystem(new RenderSystem(game.getBatch(), viewport));

        log.debug("entityCount before adding head= " + engine.getEntities().size());
        snake = factory.createSnake();
        factory.createCoin();
        //factory.createSnakeHead();
        log.debug("entityCount after adding head= " + engine.getEntities().size());

        GameManager.INSTANCE.reset();
    }


    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();

        // debug
        if(Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            log.debug("before remove count= " + engine.getEntities().size());
            engine.removeEntity(snake);
            log.debug("after remove count= " + engine.getEntities().size());

        }
        engine.update(delta);

        if (GameManager.INSTANCE.isGameOver()) {
            game.setScreen(new MenuScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
        engine.removeAllEntities();
    }
}
