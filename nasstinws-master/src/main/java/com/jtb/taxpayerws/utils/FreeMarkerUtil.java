package com.jtb.taxpayerws.utils;

import com.jtb.taxpayerws.enums.ErrorInfo;
import com.jtb.taxpayerws.exception.ApiException;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.Map;

@Component
public class FreeMarkerUtil {
    private final Configuration freemarkerConfig;

    public FreeMarkerUtil(@Qualifier("freemarkerConfig") Configuration freemarkerConfig) {
        this.freemarkerConfig = freemarkerConfig;
    }

    public String getContentFromTemplate(Map<String, Object> templateData, String template) {
        StringBuilder content = new StringBuilder();

        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfig.getTemplate(template), templateData));
        } catch (Exception ex) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorInfo.UNABLE_TO_SEND_MAIL.getErrorMessage());
        }

        return content.toString();
    }
}
