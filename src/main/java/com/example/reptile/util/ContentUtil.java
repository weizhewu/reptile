package com.example.reptile.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.Data;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @version 1.0.0
 * @author: wei-zhe-wu
 * @description: 内容操作工具类
 * @createDate: 2023/4/25 15:18
 **/
public class ContentUtil {
    /**
     * 获取json中，所有以start开头，end结尾的数据 例如  start:<div     end：</div>
     * @param json json
     * @param start 标签开始
     * @param end 标签结束
     * @return json中，所有以start开头，end结尾的数据集合
     */
    public static List<String> getContentByRegex(String json,String start,String end){
        String pStr = "("+start+".*"+end+")";
        Pattern pattern = Pattern.compile(pStr);
        Matcher matcher = pattern.matcher(json);
        List<String> matcherStrList = new ArrayList<>();
        // 此处find()，每次被调用后，会偏移到下一个匹配
        while (matcher.find()){
            String matcherStr = matcher.group();
            matcherStrList.add(matcherStr.substring(start.length()));
        }
        return matcherStrList;
    }


    private static Map<String,Integer> getIndexList(String json,String tag,String keyWords){
        int startIndexCount = 0;
        int endIndexCount = 0;
        int endIndex = 0;
        String start = "<"+tag;
        String completeStart = start;
        String end = "</"+tag+">";
        if (Objects.nonNull(keyWords)){
            completeStart = start+" "+keyWords;
        }
        if (!json.contains(completeStart) || !json.contains(end)){
            return null;
        }
        Map<String,Integer> result = new HashMap<>(2);
        startIndexCount++;
        int startIndex = json.indexOf(completeStart);
        json = json.substring(startIndex+completeStart.length());
        endIndex = completeStart.length()+startIndex;
        while (startIndexCount!=endIndexCount && (json.contains(start) || json.contains(end))){
            int temStartIndex = json.indexOf(start);
            int temEndIndex = json.indexOf(end);
            if (temStartIndex == temEndIndex){
                break;
            }
            if (temEndIndex == -1 || temStartIndex < temEndIndex){
                json = json.substring(temStartIndex+start.length());
                endIndex += temStartIndex+start.length();
                startIndexCount++;
            } else {
                json = json.substring(temEndIndex+end.length());
                endIndex += temEndIndex+end.length();
                endIndexCount++;
            }
        }
        result.put("start",startIndex);
        result.put("end",endIndex);
        return result;
    }


    public static List<String> getContent(String json,String tag,String keyWords){
        List<String> result = new ArrayList<>();
        Map<String,Integer> indexMap = new HashMap<>(2);
        while ((indexMap = getIndexList(json, tag, keyWords)) != null){
            int startIndex = indexMap.get("start");
            int endIndex = indexMap.get("end");
            String temResult = json.substring(startIndex,endIndex);
            result.add(temResult);
            json = json.substring(endIndex+1);
        }
        return result;
    }
}
