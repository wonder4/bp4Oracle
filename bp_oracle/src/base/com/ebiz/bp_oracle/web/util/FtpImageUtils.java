package com.ebiz.bp_oracle.web.util;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

/**
 * utils of resize image and waterMark
 */
public class FtpImageUtils {

	// private static final Logger logger = LoggerFactory.getLogger(FtpImageUtils.class);

	public static void resize(String source, String fileSavePath, int maxSize) throws IOException {
		String desc = StringUtils.substringBeforeLast(source, ".") + "_"
				+ StringUtils.leftPad(String.valueOf(maxSize), 3, "0") + "." + FilenameUtils.getExtension(source);
		File sourceFile = new File(source);
		File descFile = new File(desc);
		Thumbnails.of(sourceFile).size(maxSize, maxSize).toFile(descFile);

		String uploadFile = StringUtils.substringBeforeLast(fileSavePath, ".") + "_"
				+ StringUtils.leftPad(String.valueOf(maxSize), 3, "0") + "." + FilenameUtils.getExtension(fileSavePath);
		FtpUtils.uploadFile(uploadFile, descFile);
	}

	/**
	 * 给图片添加水印
	 * 
	 * @param originalImagePath 待水印的图片路径
	 * @param waterMarkPath 水印的图片路径
	 * @param Positions 水印位置BOTTOM_CENTER,BOTTOM_LEFT,BOTTOM_RIGHT,CENTER,CENTER_LEFT,CENTER_RIGHT,TOP_CENTER ,TOP_LEFT
	 *            ,TOP_RIGHT
	 */
	public static void waterMark(String originalImagePath, String waterMarkPath, Positions positions)
			throws IOException {
		if (null == positions) {
			positions = Positions.BOTTOM_RIGHT;
		}
		ImageIcon imgIcon = new ImageIcon(originalImagePath);
		Image theImg = imgIcon.getImage();
		int width = theImg.getWidth(null);
		int height = theImg.getHeight(null);

		File imgFile = new File(originalImagePath);
		Thumbnails.of(imgFile).size(width, height).watermark(positions, ImageIO.read(new File(waterMarkPath)), 0.8f)
				.outputQuality(1f).toFile(imgFile);
	}

}
