package org.plat.controller;

import org.plat.entity.UserInfo;
import org.plat.service.IUserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class HashObjectController {

    private static Logger logger = LoggerFactory.getLogger(HashObjectController.class);

    @Autowired
    private IUserInfoService userInfoService;

    @GetMapping("/add/{num}")
    public String addCache(@PathVariable Integer num) {
        try {

            if(num == null || num<1) {
                num = new Random().nextInt(10)+1;
            }
            logger.warn("---------->添加数目:{}",num);
            for (int i = 0; i < num; i++) {
                int r = new Random().nextInt(1000)+1;
                Thread.sleep(r);
                logger.warn("---------->停顿时间:{}",r);
                userInfoService.cacheUserinfo(System.currentTimeMillis() + "");
            }
        } catch(Exception e) {
            logger.error(e.getMessage());
        }

        return num+"";
    }


    @GetMapping("/remove/{id}")
    public String removeCache(@PathVariable String id) {
        try {
            userInfoService.removeCach(id);
        } catch(Exception e) {
            logger.error(e.getMessage());
        }
        return id;
    }

    @GetMapping("/get/{id}")
    public UserInfo getCache(@PathVariable String id) {
        try {
            UserInfo ui = userInfoService.getCache(id);
            return ui;
        } catch(Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }


}
