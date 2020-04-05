package com.livevideo.enums;

import lombok.Getter;

@Getter
public enum resultEnum {
    PARAM_ERROR(1,"参数不正确")
    ;
    private Integer code;
    private String message;

    resultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
