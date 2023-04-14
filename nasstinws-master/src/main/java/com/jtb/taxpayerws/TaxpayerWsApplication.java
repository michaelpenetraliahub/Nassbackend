package com.jtb.taxpayerws;

import com.jtb.taxpayerws.utils.JavaMailNotificationProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients(basePackages = "com.jtb.taxpayerws.client.proxy")
public class TaxpayerWsApplication {

	@Autowired
	private JavaMailNotificationProxy javaMailNotificationProxy;

	public static void main(String[] args) {
		SpringApplication.run(TaxpayerWsApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void sendEmail(){                                       
		javaMailNotificationProxy.sendMail("nassarawabir@jtb.gov.ng", "Nasarawa State IRS",
				"rotimimichaeljames@gmail.com","","");
	}

}
