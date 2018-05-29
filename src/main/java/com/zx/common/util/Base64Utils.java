package com.zx.common.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Base64Utils {

    private final static String DEFAULT_IMAGE_TYPE = "jpg";

    public static void main(String[] args) throws Exception {

        //本地图片地址
        String url = "C:/Users//39442/Desktop/0142135541fe180000019ae9b8cf86.jpg@1280w_1l_2o_100sh.png";
        //在线图片地址
        String string = "http://bpic.588ku.com//element_origin_min_pic/17/03/03/7bf4480888f35addcf2ce942701c728a.jpg";

        String str = Base64Utils.ImageToBase64ByLocal(url);

        String ste = Base64Utils.ImageToBase64ByOnline(string);

        System.out.println(str);

        Base64Utils.Base64ToImage(str,"C:/Users/39442/Desktop/test1.jpg");

        Base64Utils.Base64ToImage(ste, "C:/Users/39442/Desktop/test2.jpg");
    }

    /**
     * 将文件转成base64字符串
     * @param path
     * @return
     * @throws Exception
     */
    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);;
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return new BASE64Encoder().encode(buffer);
    }

    /**
     * 将base64字符保存文本文件
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */
    public static void saveBase64ToFile(String base64Code, String targetPath)
            throws Exception {
        byte[] buffer = base64Code.getBytes();
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();
    }

    /**
     * 将base64字符解码保存文件
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */
    public static void decoderBase64File(String base64Code, String targetPath)
            throws Exception {
        byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();
    }

    /**
     * pdf转成一张图片
     * @param base64Pdf
     * @param formatName
     * @return
     */
    public static String pdfToImageBase64(String base64Pdf, String formatName) {
        String base64Image = "";
        if (StringUtil.isEmpty(formatName)) {
            formatName = DEFAULT_IMAGE_TYPE;
        }
        try {
            PDDocument pdf = PDDocument.load(base64ToByteArray(base64Pdf));
            int pageSize = pdf.getNumberOfPages();
            List<BufferedImage> imageList = new ArrayList<BufferedImage>();
            BufferedImage  image = null;
            for (int i = 0; i < pageSize; i++) {
                image = new PDFRenderer(pdf).renderImageWithDPI(i, 130, ImageType.RGB);
                imageList.add(image);
            }
            base64Image = mergeVerticalImageToBase64(imageList, formatName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return base64Image;
    }


    /**
     * base64字符串转字节
     * @param base64String
     * @return
     */
    public static byte[] base64ToByteArray(String base64String) {
        return Base64.decodeBase64(base64String);
    }

    /**
     * 将宽度相同的图片，纵向追加在一起 ##注意：宽度必须相同
     * @param imageList
     * @param formatName
     * @return
     */
    public static String mergeVerticalImageToBase64(List<BufferedImage> imageList, String formatName) {
        String base64Image = "";
        if (imageList == null || imageList.size() <= 0) {
            System.out.println("图片数组为空!");
            return base64Image;
        }
        if (StringUtil.isEmpty(formatName)) {
            formatName = DEFAULT_IMAGE_TYPE;
        }
        try {
            int height = 0; // 总高度
            int width = 0; // 总宽度
            int _height = 0; // 临时的高度 , 或保存偏移高度
            int __height = 0; // 临时的高度，主要保存每个高度
            int picNum = imageList.size();// 图片的数量
            int[] heightArray = new int[picNum]; // 保存每个文件的高度
            BufferedImage buffer = null; // 保存图片流
            List<int[]> imgRGB = new ArrayList<int[]>(); // 保存所有的图片的RGB
            int[] _imgRGB; // 保存一张图片中的RGB数据
            for (int i = 0; i < picNum; i++) {
                buffer = imageList.get(i);
                heightArray[i] = _height = buffer.getHeight();// 图片高度
                if (i == 0) {
                    width = buffer.getWidth();// 图片宽度
                }
                height += _height; // 获取总高度
                _imgRGB = new int[width * _height];// 从图片中读取RGB
                _imgRGB = buffer.getRGB(0, 0, width, _height, _imgRGB, 0, width);
                imgRGB.add(_imgRGB);
            }
            _height = 0; // 设置偏移高度为0
            // 生成新图片
            BufferedImage imageResult = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < picNum; i++) {
                __height = heightArray[i];
                if (i != 0) _height += __height; // 计算偏移高度
                imageResult.setRGB(0, _height, width, __height, imgRGB.get(i), 0, width); // 写入流中
            }
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(imageResult, formatName, os);
            base64Image =  Base64.encodeBase64String(os.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return base64Image;
    }

    /**
     * 本地图片转换成base64字符串
     * @param imgFile   图片本地路径
     * @return
     */
    public static String ImageToBase64ByLocal(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理


        InputStream in = null;
        byte[] data = null;

        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);

            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();

        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }

    /**
     * 在线图片转换成base64字符串
     *
     * @param imgURL    图片线上路径
     * @return
     */
    public static String ImageToBase64ByOnline(String imgURL) {
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        try {
            // 创建URL
            URL url = new URL(imgURL);
            byte[] by = new byte[1024];
            // 创建链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            InputStream is = conn.getInputStream();
            // 将内容读取内存中
            int len = -1;
            while ((len = is.read(by)) != -1) {
                data.write(by, 0, len);
            }
            // 关闭流
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data.toByteArray());
    }


    /**
     * base64字符串转换成图片
     * @param imgStr        base64字符串
     * @param imgFilePath   图片存放路径
     * @return
     *
     */
    public static boolean Base64ToImage(String imgStr,String imgFilePath) { // 对字节数组字符串进行Base64解码并生成图片

        if (StringUtil.isEmpty(imgStr)) // 图像数据为空
            return false;

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }

            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();

            return true;
        } catch (Exception e) {
            return false;
        }

    }

}