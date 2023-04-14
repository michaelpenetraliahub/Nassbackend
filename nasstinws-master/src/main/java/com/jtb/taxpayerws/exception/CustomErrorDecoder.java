package com.jtb.taxpayerws.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()){
            case 400:
//                return new BadRequestException();
            case 404:
//                return new NotFoundException();
            default:
                System.out.println(response.status());
                return new Exception("Generic error");
        }
    }
}
