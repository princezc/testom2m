package com.zhengcheng.testom2m.service;

import com.zhengcheng.testom2m.service.RestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RestServiceImpl
 * @Author zhengcheng
 * @Date 2018/10/4 12:02
 **/
@Slf4j
@Service
public class RestServiceImpl implements RestService {
    @Override
    public void sendGet(String url, Integer times) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        List<MediaType> acceptList = new ArrayList<>();
        acceptList.add(MediaType.APPLICATION_XML);
        headers.setAccept(acceptList);
        List<String> om2mList = new ArrayList<>();
        om2mList.add("admin:admin");
        headers.put("X-M2M-Origin",om2mList);
        HttpEntity<String> httpEntity = new HttpEntity(headers);
        long time1 = System.currentTimeMillis();
        long totalTime = 0L;
        int fails = 0;
        for (int i = 0; i < times; i++) {
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
            if (responseEntity.getStatusCodeValue() != 200) {
                log.error("[RestServiceImpl][sendGet] the get response {} is error, the response is {}", i, responseEntity.toString());
                fails++;
            }
        }
        long time2 = System.currentTimeMillis();
        totalTime = time2 - time1;
        log.info("[RestServiceImpl][sendGet] {} get request costs {} ms", times - fails, totalTime);
    }
}
