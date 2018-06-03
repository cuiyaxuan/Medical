package Util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Map;

/**
 * @author: WangXinYu
 * @describe: TODO
 * @create: 2018年06月02日 17:46
 **/
public class WordUtils {
    public static void exportToWord(Map<String,Object> dataMap,HttpServletRequest request) throws Exception{
        Configuration configuration=new Configuration();
        Writer out = null;
        String wordTemplatePath = request.getSession().getServletContext().getRealPath("/") + "templet";
        String outPath = request.getSession().getServletContext().getRealPath("/") + "temp/word/";
        File tempFile = new File(outPath);
        if (!tempFile.exists() && !tempFile.isDirectory()) {
            tempFile.mkdirs();
        }
        configuration.setDefaultEncoding("utf-8");
        configuration.setDirectoryForTemplateLoading(new File(wordTemplatePath));
        Template t = configuration.getTemplate("message.ftl", "utf-8");
        try {
            String outputPath = tempFile + "病历"+dataMap.get("id").toString()  + ".doc";
            File outFile = new File(outputPath);
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"), 10240);
            t.process(dataMap, out);
            out.flush();
            out.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
