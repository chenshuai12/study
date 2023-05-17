package com.cs.redis.service.impl;

import com.cs.redis.bean.po.ProductInfoPO;
import com.cs.redis.bean.po.ShopInfoPO;
import com.cs.redis.service.ProductInfoService;
import com.cs.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import java.math.BigDecimal;
import java.util.Random;
import java.util.zip.CRC32;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisServiceImplTest {

    @Autowired
    private RedisService redisService;

    @Test

    public void insertProductInfo(){
        int bucket = 50000;
        for (int i = 1; i <= 10000000;i++){
            String phone = getPhone();
            CRC32 crc32 = new CRC32();
            crc32.update(phone.getBytes());
            String key = crc32.getValue() % bucket + "";
            String field = encode(Long.parseLong(phone));
            redisService.save(key,field,999);
            System.out.println("i:" + i + "a:" + phone + ";key:" + key + ";field:" + field);
        }
    }

    //数字转62进制
    public static String encode(long num) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int scale = 62;
        StringBuilder sb = new StringBuilder();
        int remainder;
        while (num > scale - 1) {
            //对 scale 进行求余，然后将余数追加至 sb 中，由于是从末位开始追加的，因此最后需要反转字符串
            remainder = Long.valueOf(num % scale).intValue();
            sb.append(chars.charAt(remainder));
            //除以进制数，获取下一个末尾数
            num = num / scale;
        }
        sb.append(chars.charAt(Long.valueOf(num).intValue()));
        String value = sb.reverse().toString();
        return value;
    }

    /**
     * 随机生成11位手机号码
     */
    public String getPhone(){

        String a[]={"134","135","136","137","138","139","150","151","152","157","158","159","187","188"};
        String b[]={"130","131","132","155","156","185","186","145"};
        String c[]={"133","153","180","181","189"};
        String tel[][]={a,b,c};
        Random rd=new Random();
        int index = rd.nextInt(tel.length);//运营商个数
        int size = rd.nextInt(tel[index].length); //各运营商号码个数
        String phone=tel[index][size]+""+rd.nextInt(10)+""+rd.nextInt(10)+""+rd.nextInt(10)+""+rd.nextInt(10)+""+rd.nextInt(10)+""+rd.nextInt(10)+""+rd.nextInt(10)+""+rd.nextInt(10);
        return phone;
    }

}