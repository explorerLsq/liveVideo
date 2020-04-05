package com.livevideo.exception;

import com.livevideo.enums.resultEnum;
import lombok.Getter;

@Getter
public class responseException extends RuntimeException{
    private Integer code;

    public responseException(resultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public responseException(Integer code,String message){
        super(message);
        this.code = code;
    }
}
