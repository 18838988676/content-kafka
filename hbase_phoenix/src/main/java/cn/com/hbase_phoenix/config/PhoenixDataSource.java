package cn.com.hbase_phoenix.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
@Configuration
public class PhoenixDataSource {

    @Autowired
    private Environment environment;


    @Bean(name = "phoenixJdbcDataSource")
    @Qualifier("phoenixJdbcDataSource")
    public DataSource dataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(environment.getProperty("phoenix.url"));
        druidDataSource.setDriverClassName(environment.getProperty("phoenix.driver-class-name"));
        druidDataSource.setUsername(environment.getProperty("phoenix.username"));
        druidDataSource.setPassword(environment.getProperty("phoenix.password"));
        druidDataSource.setDefaultAutoCommit(Boolean.valueOf(environment.getProperty("phoenix.default-auto-commit")));
         druidDataSource.setConnectionProperties("phoenix.schema.isNamespaceMappingEnabled=true");
        druidDataSource.setValidationQuery("select 1 from dual");
        return druidDataSource;
    }




    @Bean(name = "phoenixJdbcTemplate")
    @Qualifier("phoenixJdbcTemplate")
    public JdbcTemplate phoenixJdbcTemplate(@Qualifier("phoenixJdbcDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Autowired
    @Qualifier("phoenixJdbcTemplate")
    JdbcTemplate  jdbcTemplate;

    @Test
    public void test(){
        System.out.println(jdbcTemplate);
    }



}
