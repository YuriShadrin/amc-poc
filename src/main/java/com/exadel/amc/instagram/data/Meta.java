package com.exadel.amc.instagram.data;

import com.google.gson.annotations.SerializedName;

public class Meta {

    private Integer code;

    @SerializedName("error_type")
    private String errorType;

    @SerializedName("error_message")
    private String errorMessage;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        if (200 == code) {
            return "MetaData [code=" + code + "]";
        } else {
            return "MetaData [code=" + code + ", errorType=" + errorType + ", errorMessage=" + errorMessage + "]";
        }
    }

}
