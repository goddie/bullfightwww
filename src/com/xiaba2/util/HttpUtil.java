package com.xiaba2.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class HttpUtil {

	public static int IMAGE_SIZE = 120;

	/**
	 * 获取客户端IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		if (null == request) {
			return null;
		}

		String proxs[] = { "X-Forwarded-For", "Proxy-Client-IP",
				"WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR" };

		String ip = null;

		for (String prox : proxs) {
			ip = request.getHeader(prox);
			if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
				continue;
			} else {
				break;
			}
		}

		if (StringUtils.isBlank(ip)) {
			return request.getRemoteAddr();
		}

		return ip;
	}

	public static void createPreviewImage(String srcFile, String destFile) {
		try {
			
			AffineTransform transform = new AffineTransform();
			
			
			File fi = new File(srcFile); // src
			File fo = new File(destFile); // dest
			BufferedImage bis = ImageIO.read(fi);

			int w = bis.getWidth();
			int h = bis.getHeight();
			double scale = (double) w / h;
			int nw = IMAGE_SIZE; // final int IMAGE_SIZE = 120;
			int nh = (nw * h) / w;
			if (nh > IMAGE_SIZE) {
				nh = IMAGE_SIZE;
				nw = (nh * w) / h;
			}
			double sx = (double) nw / w;
			double sy = (double) nh / h;

			transform.setToScale(sx, sy);
			AffineTransformOp ato = new AffineTransformOp(transform, null);
			BufferedImage bid = new BufferedImage(nw, nh,
					BufferedImage.TYPE_3BYTE_BGR);
			ato.filter(bis, bid);
			ImageIO.write(bid, " jpeg ", fo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					" Failed in create preview image. Error:  "
							+ e.getMessage());
		}
	}

	/**
	 * 
	 * @param infile
	 *            输入文件
	 * @param outfile
	 *            输出文件
	 * @param srcFormat
	 *            源格式
	 * @param destFormat
	 *            输出格式
	 * @return
	 * @throws Exception
	 */
	public static boolean convertFormat(InputStream infile,
			OutputStream outfile, String srcFormat, String destFormat,
			int width, int height) throws Exception {
		boolean flag = false;
		BufferedImage src = ImageIO.read(infile);
		if (height > 0 && width > 0) {// compress the origin image if width and
										// height are non-zero
			height = src.getHeight() > height ? height : src.getHeight();
			width = src.getWidth() > width ? width : src.getWidth();
			Image image = src.getScaledInstance(width, height,
					Image.SCALE_DEFAULT);// 这个是用来进行图片大小调整的

			BufferedImage tag = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);

			Graphics g = tag.getGraphics();
			// 可在下面对图片进行绘制和更改
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图

			g.dispose();
			tag.flush();
			flag = ImageIO.write(tag, destFormat, outfile);// 输出到经过缩放的文件流
		} else {
			flag = ImageIO.write(src, destFormat, outfile);// 输出原分辨率的图片
		}
		Logger.getLogger(HttpUtil.class).info(
				"图片转换成功: 从[" + srcFormat + "]到[" + destFormat + "]");
		return flag;
	}

}
