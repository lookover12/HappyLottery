package com.project.happylottery.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

@Component
public class SyncHappyEightOpenWinJob {

    @Scheduled(cron = "*/6 * * * * ?")
    public void syncHappyEightHistoryWinData() {
        String URL = "https://www.cwl.gov.cn/cwl_admin/front/cwlkj/search/kjxx/findDrawNotice?name=kl8&issueCount=&issueStart=&issueEnd=&dayStart=&dayEnd=&pageNo=%s&pageSize=100&week=&systemType=PC";
        int i = 1;
        List<String> allResult = new ArrayList<>();
        while (i < 2000) {
            List<String> list = getOpenWinNum(String.format(URL, i));
            if(CollectionUtils.isEmpty(list)) {
                break;
            }
            i++;

            allResult.addAll(list);
        }

        try {
            FileWriter fw=new FileWriter(new File("E:/happy8.txt"));
            //写入中文字符时会出现乱码
            BufferedWriter bw=new BufferedWriter(fw);

            for(String result : allResult){
                bw.write(result+"\t\n");
            }
            bw.close();
            fw.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getOpenWinNum(String url) {
        RestTemplate restTemplate = new RestTemplate();
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Mobile Safari/537.36");
        headers.set("Cookie", "HMF_CI=8b7742be43fd5cbb87295aa4d44f481fec60b7a13067bdf5f64f10c2b276bb383b5f0a26bd83e5d5efeec67da95879e8d42d64401211ce4db45f0ff28aa474f00a");

        // 创建 HttpEntity 对象
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        List<String> result = new ArrayList<>();
        try {
            // 发送 GET 请求
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            String responseData = response.getBody();
            JSONObject jsonObject = JSON.parseObject(responseData);
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            for (int i = 0; i < jsonArray.size(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                result.add(jsonObject.getString("red"));
            }
        } catch (Exception e) {
           return null;
        }
        return result;
    }
}
