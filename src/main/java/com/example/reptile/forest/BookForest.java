package com.example.reptile.forest;

import com.dtflys.forest.annotation.Body;
import com.dtflys.forest.annotation.DataVariable;
import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.Post;

import java.util.Objects;

/**
 * @version 1.0.0
 * @author: wei-zhe-wu
 * @description: 一些相关书城的api
 * @createDate: 2023/4/25 15:04
 **/
public interface BookForest {
    @Post(url = "http://www.wanho.net/a/jyxb/")
    Object sendPost();
}
