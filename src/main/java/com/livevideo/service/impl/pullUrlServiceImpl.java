package com.livevideo.service.impl;

import com.livevideo.enums.resultEnum;
import com.livevideo.exception.responseException;
import com.livevideo.service.pullUrlService;
import com.livevideo.utils.AliyunLiveUtils;

import java.util.HashMap;

public class pullUrlServiceImpl implements pullUrlService {
    @Override
    public HashMap<String, String> getPullUrl(String sourceId) {
        HashMap<String, String> urlMap = (HashMap<String, String>) AliyunLiveUtils.createLivePullUrl(sourceId);
        if(urlMap == null){
            throw new responseException(resultEnum.PARAM_ERROR);
        }else {
            return urlMap;
        }
    }
}
