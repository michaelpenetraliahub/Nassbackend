package com.jtb.taxpayerws.client.proxy;



import com.jtb.taxpayerws.client.hystrix.JTBClientFallback;
import com.jtb.taxpayerws.client.payload.request.JTBLoginRequest;
import com.jtb.taxpayerws.client.payload.request.JTBTaxpayerRequest;
import com.jtb.taxpayerws.client.payload.response.JTBIndividualResponse;
import com.jtb.taxpayerws.client.payload.response.JTBLoginResponse;
import com.jtb.taxpayerws.client.payload.response.JTBNonIndividualResponse;
import com.jtb.taxpayerws.config.JTBClientProxyConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "jtbclient",
        url = "${jtb.base-url}",
        configuration = JTBClientProxyConfiguration.class,
        fallback = JTBClientFallback.class)
public interface JTBClientProxy {

    @PostMapping("/GetTokenID")
    JTBLoginResponse login(@RequestBody JTBLoginRequest login);

    @PostMapping("/individualtinvalidation")
    JTBIndividualResponse getIndividual(@RequestBody JTBTaxpayerRequest jtbTaxpayerRequest , @RequestParam("tokenid") String token);

    @PostMapping("/nonindividualtinvalidation")
    JTBNonIndividualResponse getNonIndividual(@RequestBody JTBTaxpayerRequest jtbTaxpayerRequest, @RequestParam("tokenid") String token);
}
