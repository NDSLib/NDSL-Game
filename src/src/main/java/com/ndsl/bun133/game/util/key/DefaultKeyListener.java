package com.ndsl.bun133.game.util.key;

import com.ndsl.bun133.display.key.IKeyListener;
import com.ndsl.bun133.display.key.KeyInput;
import com.ndsl.bun133.game.GameMain;

import java.awt.event.KeyEvent;

public class DefaultKeyListener implements IKeyListener {
    public KeyInput keyInput;
    public DefaultKeyListener(KeyInput keyInput){
        this.keyInput=keyInput;
        keyInput.addKeyListener(this);
    }

    @Override
    public void onKey(char key_char, int key_code, boolean isPushing) {
        if(isPushing){
            switch (key_code){
                case KeyEvent
                        .VK_W:
                    GameMain.logger.debug("[DefaultKeyListener]NowPressingKey:"+keyInput.getActiveAll());
            }
        }
    }
}
