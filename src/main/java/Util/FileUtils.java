package Util;

import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author: WangXinYu
 * @describe: TODO
 * @create: 2018年05月28日 14:24
 **/
public class FileUtils {
    public static File base64ToFile(String base64, String fileName) {
        File file = null;
        //创建文件目录
        String filePath="D:\\image";
        File  dir=new File(filePath);
        if (!dir.exists() && !dir.isDirectory()) {
            dir.mkdirs();
        }
        BufferedOutputStream bos = null;
        java.io.FileOutputStream fos = null;
        try {
            byte[] bytes = Base64.decodeBase64(base64);
            file=new File(filePath+"\\"+fileName);
            fos = new java.io.FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    public static void delFile(String localFileUrl){
        File file=new File(localFileUrl);
        if(file.exists()&&file.isFile()) {
            file.delete();
        }
    }


    public static File tr(String imageString,String imgName){
        BufferedImage image = null;
        byte[] imageByte = null;

        try {
            imageByte = DatatypeConverter.parseBase64Binary(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(new ByteArrayInputStream(imageByte));
            bis.close();

            File outputfile = new File("d:/image/"+imgName+".png");
            ImageIO.write(image, "png", outputfile);
            return outputfile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //base64字符串转化成图片
    public static String GenerateImage(String imgStr,String imageName) {
        System.out.print("已经收到了把字节码转化为图片的方法");
        //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) {
            return null;
        }
//        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            //Base64解码
            byte[] b = Base64.decodeBase64(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            String imgFilePath = "D:\\image\\"+imageName+".png";//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return imgFilePath;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
