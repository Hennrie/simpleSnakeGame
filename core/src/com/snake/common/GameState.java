package com.snake.common;

/**
 * Created by goran on 29/09/2016.
 */

public enum GameState {
    READY,
    PLAYING,
    GAME_OVER;

    // == public methods ==
    public boolean isReady() { return this == READY; }
    public boolean isPlaying() { return this == PLAYING; }
    public boolean isGameOver() { return this == GAME_OVER; }
}
