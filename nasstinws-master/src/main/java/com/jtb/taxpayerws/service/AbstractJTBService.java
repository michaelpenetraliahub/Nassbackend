package com.jtb.taxpayerws.service;

import com.jtb.taxpayerws.client.payload.request.JTBLoginRequest;
import com.jtb.taxpayerws.client.payload.request.JTBTaxpayerRequest;
import com.jtb.taxpayerws.client.payload.response.JTBLoginResponse;
import com.jtb.taxpayerws.client.proxy.JTBClientProxy;
import org.springframework.beans.factory.annotation.Value;

public abstract class AbstractJTBService {

    protected final JTBClientProxy jtbClientProxy;

    public AbstractJTBService(JTBClientProxy jtbClientProxy){
        this.jtbClientProxy = jtbClientProxy;
    }

    @Value("${jtb.email}")
    protected String email;

    @Value("${jtb.password}")
    protected String password;

    @Value("${jtb.clientname}")
    protected String clientName;

    private JTBLoginRequest buildJTBLoginEntity(){
        JTBLoginRequest jtbLoginRequest = new JTBLoginRequest();
        jtbLoginRequest.setEmail(email);
        jtbLoginRequest.setPassword(password);
        jtbLoginRequest.setClientName(clientName);

        return jtbLoginRequest;
    }

    protected JTBLoginResponse getLoginResponseEntity(){
        return jtbClientProxy.login(buildJTBLoginEntity());
    }

    protected JTBTaxpayerRequest getJtbTaxpayerRequest(String tin) {
        JTBTaxpayerRequest jtbTaxpayerRequest = new JTBTaxpayerRequest();
        jtbTaxpayerRequest.setTin(tin);
        return jtbTaxpayerRequest;
    }
}
