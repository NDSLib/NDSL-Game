package com.ndsl.bun133.util;

import com.ndsl.bun133.game.GameMain;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileMaster {



    public static Map<String, String> repoMap=new HashMap<>();

    public static Image getImage(String path){
        return getImage(new File(path));
    }

    public static Image getImage(File file){
        if(!file.exists()){
            if (isExist(RESOURCE_LOCATION+file.getPath())){
                return getImage(RESOURCE_LOCATION+file.getPath());
            }else{
                GameMain.logger.error("[FileMaster]NotFoundFile:"+file.getPath());
                return null;
            }
        }else{
            Image image = null;
            try {
                image = ImageIO.read(file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return image;
        }
    }

    public static final String RESOURCE_LOCATION="src\\src\\main\\resources\\";

    public static boolean isExist(String path){
        return new File(path).exists();
    }

    public static Image get(String key, String filename){
        if(isRepoExist(key)){
            return getImage(repoMap.get(key)+"\\"+filename);
        }else{
            GameMain.logger.warn("[FileMaster]Not Repo Found:"+key);
            return getImage(filename);
        }
    }

    public static void addRepo(String key, String folderName){
        repoMap.put(key, folderName);
    }

    public static boolean isRepoExist(String repoName){
        return repoMap.containsKey(repoName);
    }


    static {
        addRepo("block",RESOURCE_LOCATION+"game\\textures\\blocks");
        addRepo("blocks",RESOURCE_LOCATION+"game\\textures\\blocks");
        addRepo("title",RESOURCE_LOCATION+"game\\textures\\title");
        addRepo("textures",RESOURCE_LOCATION+"game\\textures");
        addRepo("game",RESOURCE_LOCATION+"game");
    }
}
