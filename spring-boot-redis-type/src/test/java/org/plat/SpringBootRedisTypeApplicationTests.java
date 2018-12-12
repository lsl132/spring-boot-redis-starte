package org.plat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.plat.entity.UserInfo;
import org.plat.util.RedisKeyUtil;
import org.plat.util.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRedisTypeApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private RedisService redisService;

    @Autowired
    private ValueOperations<String, Object> valueOperations;

    @Autowired
    private HashOperations<String, String, Object> hashOperations;

    @Autowired
    private ListOperations<String, Object> listOperations;

    @Autowired
    private SetOperations<String, Object> setOperations;

    @Autowired
    private ZSetOperations<String, Object> zSetOperations;

    @Test
    public void contextLoads() {
        UserInfo ui = new UserInfo();
        ui.setUsername(Work.getRandomChar());
        ui.setPid("123456");
        ui.setAge(15);
        ui.setMoblie("11535487451");

        long num = System.currentTimeMillis();

        redisService.expireKey("userinfo"+num, 60 , TimeUnit.SECONDS);

        RedisKeyUtil.getKey("ui", "userinfo:"+num, ui.getUsername());

    }

    @Test
    public void testValueOption() {
        valueOperations.set("value0bj",1);
        valueOperations.increment("value0bj",14);
        System.out.println(valueOperations.get("valueObj"));
    }


    @Test
    public void testSetOperation() {
        UserInfo ui = new UserInfo();
        ui.setUsername(Work.getRandomChar());
        ui.setPid(System.currentTimeMillis()+"");
        ui.setAge(12);
        ui.setMoblie("11535487451");
        setOperations.add("setObj", ui);

        try {
            redisService.expireKeyAt("setObj", new SimpleDateFormat("yyyy-MM-dd").parse("2018-11-29"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testHashOperation() {
        UserInfo ui = new UserInfo();
        ui.setUsername(Work.getRandomChar());
        ui.setPid(System.currentTimeMillis()+"");
        ui.setAge(28);
        ui.setMoblie("15823697548");
        hashOperations.put("hash:user",ui.hashCode()+"", ui);

        redisService.expireKey("hash:user", 30 , TimeUnit.MINUTES);
    }


    @Test
    public void testListOperation() {

//        for(int i=0;i<5;i++) {
//            int r = new Random().nextInt(999)+100;
//            try {
//                Thread.sleep(r);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            UserInfo ui = new UserInfo();
//            ui.setUsername(Work.getRandomChar());
//            ui.setPid(System.currentTimeMillis()+"");
//            ui.setAge(r/10);
//            ui.setMoblie("13818261"+r);
//
//            listOperations.leftPush("listObj",ui);
//
//            redisService.expireKey("listObj", 30 , TimeUnit.MINUTES);
//        }

//        UserInfo ui = (UserInfo) listOperations.leftPop("listObj");//
//        System.out.println(ui.toString());
        UserInfo ui2 = (UserInfo) listOperations.rightPop("listObj");//
        System.out.println(ui2.toString());

    }




}
