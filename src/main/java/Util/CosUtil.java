package Util;


import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

/**
 * @author: WangXinYu
 * @describe: TODO
 * @create: 2018年05月22日 09:04
 **/
public class CosUtil {
    public static String picCOS(String localFile) throws Exception {
        File file = new File(localFile);
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials("AKIDJSP9gzq1jDUYQ5ikkW5QYTAeuwlMatqa", "d3HUZR7apqfHfpmV1JOFtKYZNxJSN5Zp");
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region("ap-beijing"));
        // 3 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        String bucketName = "image-1252351428";
        String key = "/mms/" + System.currentTimeMillis() + ".png";
        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20 M 以下的文件使用该接口
        // 大文件上传请参照 API 文档高级 API 上传
        // 指定要上传到 COS 上的路径

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
        cosClient.putObject(putObjectRequest);
        cosClient.shutdown();
        Date expiration = new Date(System.currentTimeMillis() + 5 * 60 * 10000);
        URL url = cosClient.generatePresignedUrl(bucketName, key, expiration);
        String returnData = "https://image-1252351428.cos.ap-beijing.myqcloud.com" + key;
        return returnData;
    }

    /**
     * 上传图片Base64字符串到云存储
     * @MethodName:uploadImgCloudBase64
     * @author: WangXinYu
     * @date 2018年3月24日 上午8:58:05
     * @version V1.0
     * @param request
     * @return
     * @throws IOException
     */
    public static AjaxResponse uploadImgCloudBase64(HttpServletRequest request) throws Exception {
        AjaxResponse ajaxResponse = new AjaxResponse();
        HttpSession session = request.getSession(true);
        //请求fileName
        String fileFileName=request.getParameter("fileName");
        //请求imgBase64
        String imgBase64=request.getParameter("imgBase64");
        imgBase64 = imgBase64.substring(imgBase64.indexOf(",") + 1);
        //上传的文件的后缀
        String fileExt = fileFileName.substring(fileFileName.lastIndexOf(".") + 1).toLowerCase();
        String localFile=FileUtils.GenerateImage(imgBase64,session.getAttribute("userName").toString());
        String url=CosUtil.picCOS(localFile);
        FileUtils.delFile(localFile);
        ajaxResponse.setSuccessMessage("上传图片成功！", url);
        return ajaxResponse;
    }
}
