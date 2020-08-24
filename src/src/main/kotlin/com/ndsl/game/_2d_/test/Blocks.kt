package com.ndsl.game._2d_.test

import com.ndsl.game._2d_.ImageBlock
import com.ndsl.game.util.FileMaster

var TestBlock: ImageBlock = ImageBlock(FileMaster.get("block","OhNo.png")!!, false, 1000)
//ImageIO.read(File("src\\src\\main\\resources\\game\\textures\\blocks\\OhNo.png"))