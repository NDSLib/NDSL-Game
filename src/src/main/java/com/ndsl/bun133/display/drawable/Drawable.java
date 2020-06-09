package com.ndsl.bun133.display.drawable;

import com.ndsl.bun133.display.Display;
import com.ndsl.bun133.display.pos.onDisplayLine;
import com.ndsl.bun133.display.pos.onDisplayRect;
import com.ndsl.bun133.game.GameMain;
import com.ndsl.bun133.game.map.pos.Point;
import com.ndsl.bun133.game.map.pos.Rect;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Drawable {
    public DrawableType Drawabletype;

    protected Drawable(){}
    /**
     * @param image
     */
    public Drawable(Image image, Point left_up){
        this.image=image;
        Drawabletype=DrawableType.IMAGE;
        this.Image_rect=new onDisplayRect(left_up.x,left_up.y,left_up.x+image.getWidth(null),left_up.y+image.getHeight(null));
    }

    /**
     * for DrawableType.Image
     */
    public Image image;
    public onDisplayRect Image_rect;

    /**
     * @param rect
     */
    public Drawable(onDisplayRect rect){
        this(rect,new Color(0,0,0));
    }

    public Drawable(onDisplayRect rect, Color color){
        this.rect=rect;
        Drawabletype=DrawableType.RECT;
        rect_color=color;
    }

    /**
     * for DrawableType.Rect
     */
    public onDisplayRect rect;
    public Color rect_color;

    /**
     * @param line
     */
    public Drawable(onDisplayLine line){
        this(line,new Color(0,0,0));
    }

    public Drawable(onDisplayLine line, Color color){
        this.line=line;
        Drawabletype=DrawableType.LINE;
    }

    /**
     * for DrawableType.Line
     */
    public onDisplayLine line;
    public Color line_color;
    /**
     * @param r
     */
    public Drawable(Runnable r){
        this.runnable=r;
        Drawabletype=DrawableType.RUNNABLE;
    }

    public Runnable runnable;


    /**
     * Below is DrawSection
     */

    public void draw(Display display){
        GameMain.logger.low_level_debug("[DRAWABLE]onDraw");
        isNeedDraw=false;
        switch (this.Drawabletype){
            case LINE:
                Graphics g=display.getGraphics();
                g.setColor(line_color);
                g.drawLine(line.left_up.pos_x,line.left_up.pos_y,line.right_down.pos_x,line.right_down.pos_y);
                break;
            case RECT:
                Graphics gg=display.getGraphics();
                gg.setColor(rect_color);
                gg.fillRect(rect.left_up.pos_x,rect.left_up.pos_y,rect.getWidth(),rect.getHeight());
                break;
            case IMAGE:
                display.getGraphics().drawImage(image,Image_rect.left_up.pos_x,Image_rect.left_up.pos_y,null);
                break;
            case RUNNABLE:
                runnable.run();
                break;
        }

        if(GameMain.Drawable_Debug) debug(display);
    }

    private void debug(@NotNull Display display) {
        onDisplayRect rect=getRect();
        Graphics graphics=display.getGraphics();
        graphics.setColor(GameMain.Debug_Color);
        graphics.drawRect(rect.left_up.pos_x,rect.right_down.pos_y,rect.getWidth(), rect.getHeight());
    }

    public onDisplayRect getRect(){
        switch (this.Drawabletype){
            case IMAGE:
                return Image_rect;
            case LINE:
                return new onDisplayRect(line.left_up,line.right_down);
            case RECT:
                return rect;
            case RUNNABLE:
                return new onDisplayRect(1,1,1,1);
            default:
                GameMain.logger.warn("[Drawable]Unmatched DrawableType");
                return null;
        }
    }

    /**
     * @return is Need Repaint(or Draw)
     */
    boolean isNeedDraw=true;
    public boolean isNeedDraw() {
        return isNeedDraw;
    }

    private boolean isShow(@NotNull Display display){
        return new Rect(0,0,display.getWidth(),display.getHeight()).isContain(getRect());
    }

    public boolean isShowing(Display display){
        return isShow(display);
    }

    /**
     * Below is Events
     */

    public void onAdded(){
        GameMain.logger.debug("[Drawable]onAdded");
    }

    public String toString(){
        switch (this.Drawabletype){
            case RUNNABLE:
                return "{Type:Runnable}";
            case RECT:
                return "{Type:Rect"+this.rect.toString()+"}";
            case LINE:
                return "{Type:Line"+this.line.toString()+"}";
            case IMAGE:
                return "{Type:Image"+this.Image_rect.toString()+"}";
        }
        return "{Type:Unknown}";
    }

}
