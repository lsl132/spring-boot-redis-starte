package org.plat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

public class Receiver {

    private static Logger logger = LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch latch;

    @Autowired
    public Receiver(CountDownLatch latch) {
        this.latch = latch;
    }

    /**
     * 处理消息方法
     * @param message 消息内容
     */
    public void receiveMessage(String message) {
        try {
            logger.info("Received <" + message + ">");
            latch.countDown();
        } catch(Exception e) {
            logger.error(e.getMessage());
        }
    }

}
