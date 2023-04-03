package com.promise.util;

import com.qiniu.http.Response;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceMdPic {
    // 正则表达式，用于匹配图片部分
    private static final String MARKDOWN_IMAGE_REGEX = "!\\[.*?]\\((.*?)\\)";

    // 替换图片
    public static String replace(String markedSting) {
        Pattern pattern = Pattern.compile(MARKDOWN_IMAGE_REGEX);
        Matcher matcher = pattern.matcher(markedSting);
        Response res = null;
        while (matcher.find()) {
            res = QiniuCloudUtil.upload(new File(matcher.group(1)).getName().split("\\.")[0],
                    matcher.group(1));
            if (res == null) return markedSting;
            String url = QiniuCloudUtil.getUrl(res);
            if (url == null) return markedSting;
            markedSting = markedSting.replace(matcher.group(1), url);
        }
        return markedSting;
    }
}
