package com.ndsl.main;

import com.ndsl.bun133.game.GameMain;

public class Main {
    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) throws InterruptedException {
        GameMain gamemain = new GameMain();
        while (true) {
            gamemain.run();
//            GameMain.logger.println("Test");
            Thread.sleep(1);
            gamemain.display.update();
        }
    }
}
