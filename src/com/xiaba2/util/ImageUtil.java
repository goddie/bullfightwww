package com.xiaba2.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {

	/**
	 * 缩略图
	 * 
	 * @param fname
	 * @param crop
	 * @param size
	 */
	public static void scaleThumb(String fname, Boolean crop, int[] size) {

		String ex = fname.substring(fname.lastIndexOf("."));

		String newname = fname.replace(ex, "") + "_" + size[0] + "x" + size[1]
				+ ex;

		// asBufferedImage() 返回BufferedImage

		try {

			FileInputStream fis;
			fis = new FileInputStream(fname);
			BufferedImage bufferedImg = ImageIO.read(fis);
			int w = bufferedImg.getWidth();
			int h = bufferedImg.getHeight();
			size[0] = Math.min(w, size[0]);
			size[1] = Math.min(h, size[1]);

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		Builder<File> instance = Thumbnails.of(fname);
		instance.size(size[0], size[1]);
		instance.outputQuality(0.8f);
		// instance.outputFormat("jpg");

		if (crop) {
			instance.crop(Positions.CENTER);
		}

		try {
			instance.toFile(newname);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
