package com.hjm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author hjm
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.hjm"})
@MapperScan(basePackages = "com.hjm.dao")
public class Application
{
    public static void main( String[] args )
    {
        SpringApplication.run(Application.class,args);
    }
}
