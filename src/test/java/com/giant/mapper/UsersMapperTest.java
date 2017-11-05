package com.giant.mapper;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.SmartClassLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class UsersMapperTest {
    @Resource
    private String message;
    @Test
    public void test1() throws MalformedURLException {
        System.out.println(message);
        URL u =new URL(new File("src/webapp/WEB-INF").toURI().toString());
        URLClassLoader classLoader =new URLClassLoader(new URL[]{u});

        System.out.println(Arrays.toString(classLoader.getURLs()));

        URLClassLoader systemClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
//        systemClassLoader.
        System.out.println(Arrays.toString(systemClassLoader.getURLs()));
//        InputStream is = ;
        //../../src/webapp/WEB-INF/applicationContext.xml
        System.out.println(ClassLoader.getSystemClassLoader().getClass());
        System.out.println(ClassLoader.getSystemResource("jdbc.properties"));
    }
}