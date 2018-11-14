package com.founder.interservice.qgzyfw.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "abutment_config")
@Data
@Log4j
@NoArgsConstructor
@AllArgsConstructor
public class AbutmentConfig {
	private String senderId;
	private String serviceId;
	private String userCardId;
	private String userDept;
	private String url;
}
