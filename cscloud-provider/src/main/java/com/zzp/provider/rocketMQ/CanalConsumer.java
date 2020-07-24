package com.zzp.provider.rocketMQ;

import com.zzp.provider.config.JmsConfig;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @ClassName CanalConsumer
 * @Description
 * @Date
 * @Author Administrator
 **/
public class CanalConsumer {

//    @PostMapping("/rocketmq")
//    public Object callback() throws Exception {
//        //总共发送五次消息
//        for (String s : mesList) {
//            //创建生产信息
//            Message message = new Message(JmsConfig.TOPIC, "testtag", ("小小一家人的称谓:" + s).getBytes());
//            //发送
//            SendResult sendResult = producer.getProducer().send(message);
//            log.info("输出生产者信息={}",sendResult);
//        }
//        return "成功";
//    }

}
