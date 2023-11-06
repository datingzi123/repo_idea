package com.lagou.service.impl;

import com.lagou.dao.TestMapper;
import com.lagou.domain.Test;
import com.lagou.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    //对test表进行查询所有
    @Override
    public List<Test> findAllTest() {
        List<Test> list = testMapper.findAllTest();
        return list;
    }


    @org.junit.Test
    public void xxx(){
        /*Map<String,String>  map = new HashMap<>();
        map.put("1","123");
        map.put("2","456");

        for (String s : map.keySet()) {
            System.out.println("map的key="+s+" value="+ map.get(s));
        }*/

        /*String xx = "111";
        Integer i = 0;
        i = Integer.parseInt(xx);
        i += 1;
        xx = i.toString()";
        System.out.println(xx);*/
    }
}
