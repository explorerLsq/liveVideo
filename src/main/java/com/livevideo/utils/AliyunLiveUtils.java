package com.livevideo.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lgx
 * 阿里云直播工具类
 */
public class AliyunLiveUtils {

    private static final Logger log = LoggerFactory.getLogger(AliyunLiveUtils.class);
    /**
     * 推流域名
      */
    private static final String   ALIYUN_LIVE_PUSH_DOMAIN="alit.zhongkeruitong.top";
    /**
     * 推流签名key
     */
    private static final String    ALIYUN_LIVE_PUSH_IDENT_KEY="d6iyco6vc6";
    /**
     * 拉流域名
     */
    private static final String   ALIYUN_LIVE_PULL_DOMAIN="ali.zhongkeruitong.top";
    /**
     * 拉流签名key
     */
    private static final String   ALIYUN_LIVE_PULL_IDENT_KEY="ds93iRwrGU";
    /**
     * 应用名称
     */
    private static final String   ALIYUN_LIVE_APPNAME="chen";
    /**
     * 直播streamName
     */
    private static final String  ALIYUN_LIVE_STREAMNAME="{}{}";
//    private static final String  ALIYUN_LIVE_STREAMNAME="ali";
    /**
     * 超时时间
     */
    private static final Integer  ALIYUN_LIVE_IDENT_URL_VALIDTIME =7200;
    /**
     * 直播类型
     */
    private static final String  LIVE_TYPE="";


    /**
     * 根据源id创建该id的推流url
     * @param sourceId 资源的id
     * @return 返回推流地址
     */
    public static String createLivePushUrl(String sourceId) {

        // 流名称
        String streamName = StrUtil.format(ALIYUN_LIVE_STREAMNAME, LIVE_TYPE, sourceId);
        // 计算过期时间
        String timestamp = String.valueOf((System.currentTimeMillis() / 1000) + ALIYUN_LIVE_IDENT_URL_VALIDTIME);

        // 组合推流域名前缀
        String rtmpUrl = StrUtil.format("rtmp://{}/{}/{}", ALIYUN_LIVE_PUSH_DOMAIN, ALIYUN_LIVE_APPNAME, streamName);
        log.debug("推流域名前缀，rtmpUrl=" + rtmpUrl);
        // 组合md5加密串
        String md5Url = StrUtil.format("/{}/{}-{}-0-0-{}", ALIYUN_LIVE_APPNAME, streamName, timestamp, ALIYUN_LIVE_PUSH_IDENT_KEY);

        // md5加密
        String md5Str = DigestUtil.md5Hex(md5Url);
        log.debug("md5加密串，md5Url=" + md5Url + "------md5加密结果，md5Str=" + md5Str);
        // 组合最终鉴权过的推流域名
        String finallyPushUrl = StrUtil.format("{}?auth_key={}-0-0-{}", rtmpUrl, timestamp, md5Str);
        log.debug("最终鉴权过的推流域名=" + finallyPushUrl);

        return finallyPushUrl;
    }

    /**
     * 创建拉流域名，key=rtmpUrl、flvUrl、m3u8Url，代表三种拉流类型域名
     *
     * @param sourceId 资源的id
     * @return 返回播流地址
     */
    public static Map<String, String> createLivePullUrl(String sourceId) {

        // 流名称
        String streamName = StrUtil.format(ALIYUN_LIVE_STREAMNAME, LIVE_TYPE, sourceId);
        // 计算过期时间
        String timestamp = String.valueOf((System.currentTimeMillis() / 1000) + ALIYUN_LIVE_IDENT_URL_VALIDTIME);

        // 组合通用域名
        String pullUrl = StrUtil.format("{}/{}/{}", ALIYUN_LIVE_PULL_DOMAIN, ALIYUN_LIVE_APPNAME, streamName);
        log.debug("组合通用域名，pullUrl=" + pullUrl);

        // 组合md5加密串
        String md5Url = StrUtil.format("/{}/{}-{}-0-0-{}", ALIYUN_LIVE_APPNAME, streamName, timestamp, ALIYUN_LIVE_PULL_IDENT_KEY);
        String md5FlvUrl = StrUtil.format("/{}/{}.flv-{}-0-0-{}", ALIYUN_LIVE_APPNAME, streamName, timestamp, ALIYUN_LIVE_PULL_IDENT_KEY);
        String md5M3u8Url = StrUtil.format("/{}/{}.m3u8-{}-0-0-{}", ALIYUN_LIVE_APPNAME, streamName, timestamp, ALIYUN_LIVE_PULL_IDENT_KEY);
        // md5加密
        String md5Str = DigestUtil.md5Hex(md5Url);
        String md5FlvStr = DigestUtil.md5Hex(md5FlvUrl);
        String md5M3u8Str = DigestUtil.md5Hex(md5M3u8Url);
        log.debug("md5加密串，md5Url    =" + md5Url + "       ------     md5加密结果，md5Str=" + md5Str);
        log.debug("md5加密串，md5FlvUrl =" + md5FlvUrl + "    ------    md5加密结果，md5FlvStr=" + md5FlvStr);
        log.debug("md5加密串，md5M3u8Url=" + md5M3u8Url + "   ------    md5加密结果，md5M3u8Str=" + md5M3u8Str);

        // 组合三种拉流域名前缀
        String rtmpUrl = StrUtil.format("rtmp://{}?auth_key={}-0-0-{}", pullUrl, timestamp, md5Str);
        String flvUrl = StrUtil.format("http://{}.flv?auth_key={}-0-0-{}", pullUrl, timestamp, md5FlvStr);
        String m3u8Url = StrUtil.format("http://{}.m3u8?auth_key={}-0-0-{}", pullUrl, timestamp, md5M3u8Str);

        log.debug("最终鉴权过的拉流rtmp域名=" + rtmpUrl);
        log.debug("最终鉴权过的拉流flv域名 =" + flvUrl);
        log.debug("最终鉴权过的拉流m3u8域名=" + m3u8Url);

        HashMap<String, String> urlMap = new HashMap<>(3);
        urlMap.put("rtmpUrl", rtmpUrl);
        urlMap.put("flvUrl", flvUrl);
        urlMap.put("m3u8Url", m3u8Url);

        return urlMap;
    }


//    public static void main(String[] args) {
//        String url = createLivePushUrl("ali");
//        createLivePullUrl("ali");
//        System.out.println(url);
//    }
}


