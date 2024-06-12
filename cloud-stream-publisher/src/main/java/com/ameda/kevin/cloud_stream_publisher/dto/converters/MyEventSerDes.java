package com.ameda.kevin.cloud_stream_publisher.dto.converters;/*
*
@author ameda
@project cloud-stream-publisher
*
*/

import com.ameda.kevin.cloud_stream_publisher.dto.MyEvent;
import org.springframework.kafka.support.serializer.JsonSerde;

public class MyEventSerDes extends JsonSerde<MyEvent> {


}
