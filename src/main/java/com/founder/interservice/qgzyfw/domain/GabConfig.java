package com.founder.interservice.qgzyfw.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "gab_url")
@Data
@Log4j
@NoArgsConstructor
@AllArgsConstructor
public class GabConfig {
	private String ip;//联动调度节点
	private String senderId;//联动请求方id
	private String receiverAsjId;
	private String receiverFzxyrId;
	private String receiverXzgzryId;
}
