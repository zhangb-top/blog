package com.promise.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.util.StringUtils;

import java.io.File;

public class QiniuCloudUtil {
    // 七牛云的Access Key和Secret Key
    private static final String ACCESS_KEY = "your Access Key";
    private static final String SECRET_KEY = "your Secret Key";
    // 要上传的空间名
    private static final String BUCKET_NAME = "your Bucket name";
    // 外链域名
    private static final String domain = "your domain";
    // 密钥
    private static final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    private QiniuCloudUtil() {
    }

    public static String getUrl(Response res) {
        try {
            return "http://" + domain + "/" + new Gson().fromJson(res.bodyString(),
                    DefaultPutRet.class).key;
        } catch (QiniuException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Response upload(String fileName, String localFilePath) {
        // 创建上传对象，Zone*代表地区 必须根据自己的空间名所处地域来填写Zone.what()
        Configuration configuration = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(configuration);
        // 调用put方法上传
        String token = auth.uploadToken(BUCKET_NAME);
        if (StringUtils.isEmpty(token)) {
            System.out.println("未获取到token，请重试！");
            return null;
        }
        Response res = null;
        try {
            res = uploadManager.put(new File(localFilePath), fileName, token);
        } catch (QiniuException e) {
            e.printStackTrace();
            return null;
        }
        return res;
    }
}
