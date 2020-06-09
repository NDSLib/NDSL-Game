package com.ndsl.bun133.display.key;

import com.ndsl.bun133.display.Display;
import com.ndsl.bun133.game.GameMain;
import com.ndsl.bun133.util.MultiSet;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyInput implements java.awt.event.KeyListener {
    public Map<MultiSet<Integer, Character>, Boolean> KeyBool=new HashMap<>();
    public KeyInput(Display display){
        display.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        KeyBool.put(new MultiSet<Integer, Character>(e.getKeyCode(),e.getKeyChar()),true);
        onKey(e.getKeyCode(),e.getKeyChar(),true);
    }

    private void onKey(int keyCode, char keyChar,boolean isPushed) {
        GameMain.logger.debug(isPushed ? "[KeyInput]Key: "+keyChar+"("+keyCode+")"+" isPushing" : "[KeyInput]Key: "+keyChar+"("+keyCode+")"+" is Not Pushing");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        KeyBool.put(new MultiSet<Integer, Character>(e.getKeyCode(),e.getKeyChar()),true);
        onKey(e.getKeyCode(),e.getKeyChar(),true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        KeyBool.put(new MultiSet<Integer, Character>(e.getKeyCode(),e.getKeyChar()),false);
        onKey(e.getKeyCode(),e.getKeyChar(),false);
    }

    public boolean getKey(int keycode){
        for(Map.Entry<MultiSet<Integer, Character>, Boolean> entry:KeyBool.entrySet()){
            if(entry.getKey().t_value==keycode){
                return entry.getValue();
            }
        }
        GameMain.logger.low_level_debug("[KeyInput]NotFoundKey:"+keycode);
        return false;
    }

//    public Set<MultiSet<Integer,Character>> getAll(){
//        return KeyBool.keySet();
//    }

    public List<MultiSet<Integer, Character>> getAll(){
        return new ArrayList<>(KeyBool.keySet());
    }

    public List<MultiSet<Integer, Character>> getActiveAll(){
        List<MultiSet<Integer, Character>> list = new ArrayList<>();
        for(Map.Entry<MultiSet<Integer, Character>, Boolean> set:KeyBool.entrySet()){
            if(set.getValue().booleanValue()){
                list.add(set.getKey());
            }
        }
        return list;
    }


    /**
     * below is register
     */

    public List<IKeyListener> keyListeners=new ArrayList<>();

    public void addKeyListener(IKeyListener listener){
        keyListeners.add(listener);
    }

    public void onKey_Register(char key_char,int key_code,boolean isPushing){

    }
}
