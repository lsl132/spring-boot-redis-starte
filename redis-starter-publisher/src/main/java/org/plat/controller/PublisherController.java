package org.plat.controller;

import org.plat.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublisherController {

    private static Logger logger = LoggerFactory.getLogger(PublisherController.class);

    private static final String CHANNEL = "userinfo";

    @Autowired
    private StringRedisTemplate template;

    @GetMapping(value = "/pub/{mobile}")
    public String pubMsg(@PathVariable String mobile) {
        try {
            UserInfo ui = new UserInfo(mobile,"李双隆",31,"430503198710201038");
            template.convertAndSend(CHANNEL,ui.toString());
            logger.warn("发送数据: {}",ui);
        } catch(Exception e) {
            logger.error(e.getMessage());
        }
        return mobile;
    }


}
