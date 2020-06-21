package com.ndsl.bun133.display.key;

import com.ndsl.bun133.display.Display;
import com.ndsl.bun133.game.GameMain;
import com.ndsl.bun133.util.EqualMap;
import com.ndsl.bun133.util.MultiSet;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class KeyInput implements java.awt.event.KeyListener {
    public EqualMap<MultiSet<Integer, Character>, Boolean> KeyBool=new EqualMap<>();
    public KeyInput(Display display){
        display.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        KeyBool.put(new MultiSet<Integer, Character>(e.getKeyCode(),e.getKeyChar()),true);
//        onKey(e.getKeyCode(),e.getKeyChar(),true);
    }

    private void onKey(int keyCode, char keyChar,boolean isPushed) {
        GameMain.logger.debug(isPushed ? "[KeyInput]Key: "+keyChar+"("+keyCode+")"+" is Pushing" : "[KeyInput]Key: "+keyChar+"("+keyCode+")"+" is Not Pushing");
        this.onKey_Register(keyChar,keyCode,isPushed);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        KeyBool.put(new MultiSet<Integer, Character>(e.getKeyCode(),e.getKeyChar()),true);
        onKey(e.getKeyCode(),e.getKeyChar(),true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(!KeyBool.containsKey(new MultiSet<Integer, Character>(e.getKeyCode(),e.getKeyChar()))) GameMain.logger.error("[KeyInput]Nothing Match for Map Binding");
        KeyBool.put(new MultiSet<Integer, Character>(e.getKeyCode(),e.getKeyChar()),false);
        onKey(e.getKeyCode(),e.getKeyChar(),false);
    }

    public boolean getKey(int keycode){
        for(MultiSet<MultiSet<Integer,Character>,Boolean> entry:KeyBool.getValues()){
            if(entry.t_value.t_value==keycode){
                return entry.s_value;
            }
        }
        GameMain.logger.low_level_debug("[KeyInput]NotFoundKey:"+keycode);
        return false;
    }

//    public Set<MultiSet<Integer,Character>> getAll(){
//        return KeyBool.keySet();
//    }

    public List<MultiSet<MultiSet<Integer, Character>,Boolean>> getAll(){
        return new ArrayList<MultiSet<MultiSet<Integer,Character>,Boolean>>(KeyBool.keySet());
    }

    public List<MultiSet<MultiSet<Integer, Character>,Boolean>> getActiveAll(){
        List<MultiSet<MultiSet<Integer, Character>,Boolean>> list = new ArrayList<>();
        for(MultiSet<MultiSet<Integer,Character>,Boolean> entry:KeyBool.getValues()){
            if(entry.s_value){
                list.add(entry);
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
        for(IKeyListener keyListener:keyListeners){
            keyListener.onKey(key_char,key_code,isPushing);
        }
    }
}
