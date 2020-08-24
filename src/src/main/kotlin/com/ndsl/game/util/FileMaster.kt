package com.ndsl.game.util

import com.ndsl.game._2d_.logger
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class FileMaster {
    companion object{
        private var fileRepositories:MutableMap<String, File> = mutableMapOf()
        private fun addRepository(key:String, directory:File){
            if(directory.isDirectory){
                fileRepositories[key] = directory
            }else{throw IllegalArgumentException("${directory.absolutePath} is not Directory")}
        }

        fun get(key:String,filename:String): BufferedImage? {
            if(fileRepositories.contains(key)){
                logger.info("Loading:${fileRepositories[key]}\\$filename")
                return ImageIO.read(File("${fileRepositories[key]}\\$filename"))
            }
            return null
        }

        init {
            addRepository("block",File("src\\src\\main\\resources\\game\\textures\\blocks"))
        }
    }
}