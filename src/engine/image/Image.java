package engine.image;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {

	/**
	 * Return a BufferedImage from the given position.
	 * @param path location like ["/img/name.png"].
	 */
	public static BufferedImage loadBufferedImage(String path) {
		try {
			return ImageIO.read(Image.class.getClass().getResourceAsStream(path));
		} catch (IOException e) {
			System.err.println("Datei [" + path + "] could not found.");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Return a BufferedImage out of the given spritesheet.
	 * @param sprite
	 * @param tilesizeWidth the original sprite width.
	 * @param tilesizeHeight the original sprite height.
	 * @param col 
	 * @param row
	 * @param width the width of the image.
	 * @param height the height of the image.
	 * @return return a new BufferedImage.
	 */
	public static BufferedImage getSubImage(BufferedImage sprite, int tilesizeWidth, int tilesizeHeight, int row, int col, int width, int height) {
		return sprite.getSubimage((col * tilesizeWidth) - tilesizeWidth, (row * tilesizeHeight) - tilesizeHeight, width, height);
	}
	
	/**
	 * Return a scaled BufferedImage
	 * @param width of the return image.
	 * @param height of the return image.
	 * @param toScale that image will be scaled.
	 * @return a scaled BufferedImage
	 */
	public static BufferedImage scale( BufferedImage toScale, int width, int height) {
		BufferedImage scaled = new BufferedImage(width, height, toScale.getType());
		Graphics2D g = scaled.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(toScale, 0, 0, width, height, null);
		g.dispose();
		return scaled;
	}
	
	/**
	 * Return a scaled BufferedImage
	 * @param toScale that image will be scaled.
	 * @param factor width and height is multiplied by that factor.
	 * @return a scaled BufferedImage
	 */
	public static BufferedImage scale(BufferedImage toScale, double factor) {
		BufferedImage scaled = new BufferedImage((int)(toScale.getWidth() * factor), (int)(toScale.getHeight() * factor), toScale.getType());
		Graphics2D g = scaled.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(toScale, 0, 0, (int)(toScale.getWidth() * factor), (int)(toScale.getHeight() * factor), null);
		g.dispose();
		return scaled;
	}
	
	
	/**
	 * Rotates a given BufferedImage by a number of degrees
	 * @param img image to manipulate
	 * @param radians angle (in radians) to rotate the image
	 * @return a rotated bufferedimage
	 * */
	public static BufferedImage rotate(BufferedImage img, float radians) {
		//As a consequence make the image that big
		BufferedImage rotated = new BufferedImage((int)(img.getWidth() *1.3), (int)(img.getHeight() *1.3), img.getType());
		//We create a Graphics2D object...
		Graphics2D f = rotated.createGraphics();
		f.translate(rotated.getWidth()/2, rotated.getHeight()/2); //translate half the width and height
		f.rotate(radians); //rotate the image...
		f.translate(-img.getWidth()/2, -img.getHeight()/2); //translate-back to half the original width and height
		f.drawImage(img, 0, 0, null); //draw image
		f.dispose();
		return rotated;
	}
}