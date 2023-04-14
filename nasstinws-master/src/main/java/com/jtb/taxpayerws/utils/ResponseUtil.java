package com.jtb.taxpayerws.utils;

import com.jtb.taxpayerws.exception.ErrorDetails;
import com.jtb.taxpayerws.payload.Meta;
import com.jtb.taxpayerws.payload.response.ApiResponse;
import org.springframework.http.HttpStatus;

public class ResponseUtil {
    public static ErrorDetails buildErrorResponse(HttpStatus status, String message, String info) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setStatus(status.value());
        errorDetails.setMessage(message);
        errorDetails.setInfo(info);

        return errorDetails;
    }

    private static Meta buildOkMeta() {
        Meta meta = new Meta();
        meta.setStatus(String.valueOf(HttpStatus.OK.value()));
        meta.setMessage("OK");
        meta.setInfo("OK");

        return meta;
    }

    private static Meta buildCreatedMeta() {
        Meta meta = new Meta();
        meta.setStatus(String.valueOf(HttpStatus.CREATED.value()));
        meta.setMessage("CREATED");
        meta.setInfo("Resource created");

        return meta;
    }

    public static <T> ApiResponse<T> buildOkResponse(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setMeta(buildOkMeta());
        response.setData(data);

        return response;
    }

    public static <T> ApiResponse<T> buildCreatedResponse(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setMeta(buildCreatedMeta());
        response.setData(data);

        return response;
    }
}
