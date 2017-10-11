package cn.java.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.java.entity.Computer;

public class ComputerDaoImpl {
    private static SqlSession session = null;

    @Before
    public void init() {
        try {
            // 1、得到SqlSession
            SqlSessionFactoryBuilder sfb = new SqlSessionFactoryBuilder();
            InputStream ins = Resources.getResourceAsStream("mybatis.xml");
            SqlSessionFactory ssf = sfb.build(ins);
            session = ssf.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 查询所有电脑信息(不带条件查询)
    @Test
    public void selectAll() throws Exception {
        // 2、调用SqlSession这个类中的selectList方法
        List<Computer> lists = session.selectList("cn.java.dao.impl.ComputerDaoImpl.selectAll");
        System.out.println(lists);
    }

    @Test
    public void selectAll2() throws Exception {
        // 2、调用SqlSession这个类中的selectList方法
        List<Map<String, Object>> lists = session.selectList("cn.java.dao.impl.ComputerDaoImpl.selectAll2");
        System.out.println(lists.toString());
    }

    // 通过多个条件查询
    @Test
    public void selectByConditions() {
        Map<String, Object> paramter = new HashMap<String, Object>();
        paramter.put("a", "联想");
        paramter.put("memory_size", 4F);
        List<Computer> lists = session.selectList("cn.java.dao.impl.ComputerDaoImpl.selectByConditions", paramter);
        System.out.println(lists);
    }
}
