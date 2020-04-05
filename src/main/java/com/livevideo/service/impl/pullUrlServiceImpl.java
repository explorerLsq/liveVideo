package com.livevideo.service.impl;

import com.livevideo.enums.resultEnum;
import com.livevideo.exception.responseException;
import com.livevideo.service.pullUrlService;
import com.livevideo.utils.AliyunLiveUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class pullUrlServiceImpl implements pullUrlService {
    @Override
    public HashMap<String, String> getPullUrl(String sourceId) {
        HashMap<String, String> urlMap = (HashMap<String, String>) AliyunLiveUtils.createLivePullUrl(sourceId);

        int flag =0;
        for(String key : urlMap.keySet()){
            String value = urlMap.get(key);
            if(value == null){
                flag++;
            }
        }
        if (flag == 3){
            throw new responseException(resultEnum.PARAM_ERROR);
        }else {
            return urlMap;
        }

    }
}
