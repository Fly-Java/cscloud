package com.zzp.provider.rocketMQ;

import com.zzp.provider.config.JmsConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.stereotype.Component;

/**
 * @ClassName Producer
 * @Description
 * @Date
 * @Author Administrator
 **/
@Slf4j
@Component
public class Provider {
    private String producerGroup = "test_producer";
    private DefaultMQProducer provider;

    public Provider(){
        //示例生产者
        provider = new DefaultMQProducer(producerGroup);
        //不开启vip通道 开通口端口会减2
        provider.setVipChannelEnabled(false);
        //绑定name server
        provider.setNamesrvAddr(JmsConfig.NAME_SERVER);
        start();
    }
    /**
     * 对象在使用之前必须要调用一次，只能初始化一次
     */
    public void start(){
        try {
            this.provider.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    public DefaultMQProducer getProducer(){
        return this.provider;
    }
    /**
     * 一般在应用上下文，使用上下文监听器，进行关闭
     */
    public void shutdown(){
        this.provider.shutdown();
    }

}
