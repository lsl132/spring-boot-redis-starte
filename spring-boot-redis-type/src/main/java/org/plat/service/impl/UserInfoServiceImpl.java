package org.plat.service.impl;

import org.plat.entity.UserInfo;
import org.plat.service.IUserInfoService;
import org.plat.util.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserInfoServiceImpl implements IUserInfoService {

    private static Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);



    @CachePut(value = "userinfo", key = "#id")
    @Override
    public UserInfo cacheUserinfo(String id) throws Exception {

        int r = new Random().nextInt(9999)+1000;
        UserInfo ui = new UserInfo();
        ui.setUsername(Work.getRandomChar()+""+Work.getRandomChar()+""+Work.getRandomChar());
        ui.setMoblie("158"+r+"5954");
        ui.setAge(new Random().nextInt(60)+18);
        ui.setPid(new Random().nextInt(9999999)+100000 + "");

        return ui;
    }

    @CacheEvict(value = "userinfo", key = "#id")
    @Override
    public void removeCach(String id) throws Exception {

    }

    @Cacheable(value = "userinfo", key = "#id")
    @Override
    public UserInfo getCache(String id) throws Exception {
        return null;
    }
}
