package com.zx.common.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author qingzhou
 *         2018/4/9
 */
public class ImageGenerator {

    private ImageGenerator(){}

    public static void generate(String srcImgPath, String tarImgPath, String title) throws IOException {
        Font font = new Font("微软雅黑", Font.BOLD, 20);
        Color color = new Color(255, 255, 0);

        FileOutputStream outImgStream = null;
        try {
            File srcImgFile = new File(srcImgPath);
            Image srcImg = ImageIO.read(srcImgFile);
            int srcImgWidth = srcImg.getWidth(null);
            int srcImgHeight = srcImg.getHeight(null);

            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            bufImg = g.getDeviceConfiguration().createCompatibleImage(srcImgWidth, srcImgHeight, Transparency.TRANSLUCENT);
            g.dispose();
            g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            g.setColor(color);
            g.setFont(font);

            int x = (srcImgWidth - getWatermarkLength(title, g)) / 2;
            int y = sun.font.FontDesignMetrics.getMetrics(font).getHeight() + 2;

            GlyphVector v = font.createGlyphVector(new JPanel().getFontMetrics(font).getFontRenderContext(), title);
            Shape shape = v.getOutline();
            g.translate(x, y);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            g.setColor(color);//字体颜色
            g.setColor(new Color(246, 255, 0));//字体颜色
            g.fill(shape);
//            g.setColor(Color.BLACK.darker().darker());//边框颜色
            g.setColor(Color.BLACK);//边框颜色
            g.setStroke(new BasicStroke(0.7f));
            g.draw(shape);

            g.dispose();
            outImgStream = new FileOutputStream(tarImgPath);
            ImageIO.write(bufImg, "png", outImgStream);
            outImgStream.flush();

            outImgStream.close();
        } finally {
            if (outImgStream != null) {
                try {
                    outImgStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }

    public static void main(String[] args) throws IOException {
        String srcImgPath = "C:/Users/16204/Desktop/test/staff-org.png";
        String tarImgPath = "C:/Users/16204/Desktop/test/staff1_test.png";
        String title = "张三丰哈";  //水印内容
        generate(srcImgPath, tarImgPath, title);
    }

}
