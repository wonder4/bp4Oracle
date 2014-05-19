package com.ebiz.bp_oracle.web.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.jimi.core.Jimi;
import com.sun.jimi.core.JimiException;
import com.sun.jimi.core.JimiWriter;
import com.sun.jimi.core.options.JPGOptions;

/**
 * utils of resize image
 */
public class ImageUtils {

	private static final Logger logger = LoggerFactory.getLogger(ImageUtils.class);

	public static boolean ergodDirectory(String directory) throws FileNotFoundException, IOException {
		try {
			File file = new File(directory);
			if (file.isDirectory()) {
				String[] fileList = file.list();
				for (int i = 0; i < fileList.length; i++) {
					File readfile = new File(directory + File.pathSeparator + fileList[i]);
					if (!readfile.isDirectory()) {
						// ImageUtils.resizeByMaxSize(readfile.getAbsolutePath(),
						// null, new int[] { 60, 120, 200, 400
						// });
					} else if (readfile.isDirectory()) {
						ergodDirectory(directory + File.pathSeparator + fileList[i]);
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("FileNotFound");
		}
		return true;
	}

	public static void resize(String source, String desc, int width, int height) throws IOException {
		Image sourceImage = ImageIO.read(new File(source));
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		bi.getGraphics().drawImage(sourceImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

		FileOutputStream out = new FileOutputStream(desc);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(bi);
		out.close();
	}

	public static void resizeByFixedHeight(String source, String desc, int fixedHeight) throws JimiException,
			IOException {
		if (StringUtils.isBlank(desc)) {
			desc = StringUtils.substringBeforeLast(source, "_") + "_"
					+ StringUtils.leftPad(String.valueOf(fixedHeight), 3, "0") + ".jpg";
		}
		logger.debug("source image file is:{}" + source);
		logger.debug("desc image file is:{}" + desc);

		String jpgSource = toJPG(source, null, 100);
		logger.debug("source jpg file is:{}" + jpgSource);

		Image sourceImage = ImageIO.read(new File(jpgSource));

		double sourceImageWidth = sourceImage.getWidth(null);
		double sourceImageHeight = sourceImage.getHeight(null);

		int resizedWidth = (int) ((sourceImageWidth / sourceImageHeight) * fixedHeight);

		ImageUtils.resize(jpgSource, desc, resizedWidth, fixedHeight);

	}

	public static void resizeByFixedWidth(String source, String desc, int fixedWidth) throws JimiException, IOException {
		if (StringUtils.isBlank(desc)) {
			desc = StringUtils.substringBeforeLast(source, "_") + "_"
					+ StringUtils.leftPad(String.valueOf(fixedWidth), 3, "0") + ".jpg";
		}
		logger.debug("source image file is:{}" + source);
		logger.debug("desc image file is:{}" + desc);

		String jpgSource = toJPG(source, null, 100);
		logger.debug("source jpg file is:{}" + jpgSource);

		Image sourceImage = ImageIO.read(new File(jpgSource));

		double sourceImageWidth = sourceImage.getWidth(null);
		double sourceImageHeight = sourceImage.getHeight(null);

		int resizedHeight = (int) ((sourceImageHeight / sourceImageWidth) * fixedWidth);

		ImageUtils.resize(jpgSource, desc, fixedWidth, resizedHeight);
	}

	public static void resizeByMaxSize(String source, String desc, int maxSize) throws JimiException, IOException {
		if (StringUtils.isBlank(desc)) {
			desc = StringUtils.substringBeforeLast(source, ".") + "_"
					+ StringUtils.leftPad(String.valueOf(maxSize), 3, "0") + ".jpg";
		}
		logger.debug("source image file is:{}" + source);
		logger.debug("desc image file is:{}" + desc);

		String jpgSource = toJPG(source, null, 100);
		logger.debug("source jpg file is:{}" + jpgSource);

		double ratio = 0.0d;

		Image sourceImage = ImageIO.read(new File(jpgSource));

		double sourceImageWidth = sourceImage.getWidth(null);
		double sourceImageHeight = sourceImage.getHeight(null);

		if (sourceImageHeight > sourceImageWidth) {
			ratio = maxSize / sourceImageHeight;
		} else {
			ratio = maxSize / sourceImageWidth;
		}

		int resizedWidth = (int) (sourceImageWidth * ratio);
		int resizedHeight = (int) (sourceImageHeight * ratio);

		ImageUtils.resize(jpgSource, desc, resizedWidth, resizedHeight);
	}

	public static void resizeByRatio(String source, String desc, double ratio) throws JimiException, IOException {
		if (StringUtils.isBlank(desc)) {
			desc = StringUtils.substringBeforeLast(source, ".") + "_"
					+ StringUtils.leftPad(String.valueOf((ratio * 100)), 3, "0") + ".jpg";
		}
		logger.debug("source image file is:{}" + source);
		logger.debug("desc image file is:{}" + desc);

		String jpgSource = toJPG(source, null, 100);
		logger.debug("source jpg file is:{}" + jpgSource);

		String jpgSourceFile = toJPG(source, null, 100);
		Image sourceImage = ImageIO.read(new File(jpgSource));

		double sourceImageWidth = sourceImage.getWidth(null);
		double sourceImageHeight = sourceImage.getHeight(null);

		int resizedWidth = (int) (sourceImageWidth * ratio);
		int resizedHeight = (int) (sourceImageHeight * ratio);

		ImageUtils.resize(jpgSourceFile, desc, resizedWidth, resizedHeight);
	}

	public static String toJPG(String source, String dest, int quality) throws JimiException {
		if ((dest == null) || dest.trim().equals("")) {
			dest = source;
		}
		if (!dest.toLowerCase().trim().endsWith(".jpg")) {
			dest += ".jpg";

			if ((quality < 0) || (quality > 100) || ((quality + "") == null) || (quality + "").equals("")) {
				quality = 75;
			}
			JPGOptions options = new JPGOptions();
			options.setQuality(quality);
			ImageProducer image = Jimi.getImageProducer(source);
			JimiWriter writer = Jimi.createJimiWriter(dest);
			writer.setSource(image);
			// add options here if necessary
			writer.setOptions(options);
			writer.putImage(dest);
		}
		return dest;
	}
}
