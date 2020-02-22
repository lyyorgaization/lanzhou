package com.lucien.dap.framework.core.mq.kafka;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("spring.kafka")
public class KafkaConfig {
	private String bootstrap_servers;
	private Producer producer;
	private Consumer consumer;

	@Data
	public static class Producer {
		private String retries;
		private String batch_size;
		private String linger_ms;
		private String buffer_memory;
	}

	@Data
	public static class Consumer {
		private Boolean autoCommit;
		private String autoCommitInterval;
		private String sessionTimeOut;
		private String autoOffsetReset;
	}
}
