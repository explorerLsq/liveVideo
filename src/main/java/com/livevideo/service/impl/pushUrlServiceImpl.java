package com.livevideo.service.impl;

import com.livevideo.enums.resultEnum;
import com.livevideo.exception.responseException;
import com.livevideo.service.pushUrlService;
import com.livevideo.utils.AliyunLiveUtils;
import org.springframework.stereotype.Service;

@Service
public class pushUrlServiceImpl implements pushUrlService {
    @Override
    public String getFinallyPushUrl(String sourceId) {
        String getPushUrl = AliyunLiveUtils.createLivePushUrl(sourceId);
        if (getPushUrl == null){
            throw new responseException(resultEnum.PARAM_ERROR);
        }else {
            return getPushUrl;
        }

    }
}
