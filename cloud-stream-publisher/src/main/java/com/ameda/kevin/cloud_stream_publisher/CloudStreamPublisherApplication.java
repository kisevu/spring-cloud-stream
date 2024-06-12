package com.ameda.kevin.cloud_stream_publisher;

import com.ameda.kevin.cloud_stream_publisher.dto.Department;
import com.ameda.kevin.cloud_stream_publisher.dto.MyEvent;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class CloudStreamPublisherApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudStreamPublisherApplication.class, args);
	}
	@Bean
	public Supplier<Message<MyEvent>> producer(){
		return () ->{
			Department department = Department.values()[new Random().nextInt(Department.values().length)];
			MyEvent event = new MyEvent("Kevin",Department.TECH);
			return MessageBuilder.withPayload(event)
					.setHeader(KafkaHeaders.KEY,department.name()).build();
		};
	}

	@Bean
	public Function<KStream<String,MyEvent>,KStream<String,String>> enhancer(){
		return input -> input
				.mapValues(val-> val.name());
	}


}
