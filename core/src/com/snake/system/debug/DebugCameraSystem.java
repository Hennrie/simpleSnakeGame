package com.snake.system.debug;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.snake.util.debug.DebugCameraController;

public class DebugCameraSystem extends EntitySystem {

    // == constants ==
    public static final DebugCameraController DEBUG_CAMERA_CONTROLLER = new
            DebugCameraController();
    // == attributes ==
    private final OrthographicCamera camera;
    // == constructors ==
    public DebugCameraSystem(float startX, float startY, OrthographicCamera camera) {
        this.camera = camera;
        DEBUG_CAMERA_CONTROLLER.setStartPosition(startX, startY);
    }

    // == private methods ==

    // == public methods ==

    @Override
    public void update(float deltaTime) {
        DEBUG_CAMERA_CONTROLLER.handleDebugInput(deltaTime);
        DEBUG_CAMERA_CONTROLLER.applyTo(camera);
    }
}
