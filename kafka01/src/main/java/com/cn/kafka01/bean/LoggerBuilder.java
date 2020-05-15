package com.cn.kafka01.bean;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.filter.LevelFilter;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;
import ch.qos.logback.core.util.OptionHelper;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static ch.qos.logback.core.spi.FilterReply.ACCEPT;
import static ch.qos.logback.core.spi.FilterReply.DENY;

public class LoggerBuilder {

    private static final Map<String, Logger> container = new HashMap<>();

    private static ConcurrentHashMap<Integer, ConsoleAppender> appenderConcurrentHashMap = new ConcurrentHashMap<Integer, ConsoleAppender>(4){
        {
            put(1,getAppender(Level.ERROR));
            put(2,getAppender(Level.WARN));
            put(3,getAppender(Level.INFO));
            put(4,getAppender(Level.DEBUG));
        }
    };

    //获取文件日志输出
    public static final Logger getLogger(String name) {
        Logger logger = container.get(name);
        if (logger != null) {
            return logger;
        }
        synchronized (LoggerBuilder.class) {
            logger = container.get(name);
            if (logger != null) {
                return logger;
            }
            RollingFileAppender infoAppender = getAppender(name, Level.INFO);
            LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
            logger = context.getLogger("FILE-" + name);
            //设置不向上级打印信息
            logger.setAdditive(false);
            logger.addAppender(infoAppender);
            container.put(name, logger);
        }
        return logger;
    }

    //获取控制台日志输出
    public static final Logger getLogger(Class c) {
        List<ConsoleAppender> appenders = appenderConcurrentHashMap.values().stream().collect(Collectors.toList());
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger logger = context.getLogger(c.getName());
        logger.setAdditive(false);
        for (ConsoleAppender appender : appenders) {
            logger.addAppender(appender);
        }
        return logger;
    }

    /**
     * 通过传入的名字和级别，动态设置文件输出参数
     *
     * @param name
     * @param level
     * @return
     */
    private static RollingFileAppender getAppender(String name, Level level) {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        RollingFileAppender appender = new RollingFileAppender();
        LevelFilter levelFilter = getLevelFilter(level);
        levelFilter.start();
        appender.addFilter(levelFilter);

        appender.setContext(context);
        appender.setName("FILE-" + name);
        appender.setFile(OptionHelper.substVars("${logging.dir}/ts_log/" + name + "/" + name + ".sql", context));
        appender.setAppend(true);
        appender.setPrudent(false);
        TimeBasedRollingPolicy policy = new TimeBasedRollingPolicy();
        String fp = OptionHelper.substVars("${logging.dir}/ts_log/" + name + "/" + name + "_%d{yyyyMMddHH}.sql", context);
        policy.setFileNamePattern(fp);
        //设置最大历史记录为30条
        policy.setMaxHistory(30);
        //总大小限制
        policy.setTotalSizeCap(FileSize.valueOf("128GB"));
        //设置父节点是appender
        policy.setParent(appender);
        policy.setContext(context);
        policy.start();

        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        //设置日志编码
        encoder.setCharset(Charset.forName("UTF-8"));
        encoder.setContext(context);
        //设置日志格式
        encoder.setPattern("%msg%n");
        encoder.start();

        //加入下面两个节点
        appender.setRollingPolicy(policy);
        appender.setEncoder(encoder);
        appender.start();
        return appender;
    }

    /**
     * 根据日志级别，动态设置控制台输出参数
     *
     * @param level
     * @return
     */
    private static ConsoleAppender getAppender(Level level) {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        ConsoleAppender appender = new ConsoleAppender();
        LevelFilter levelFilter = getLevelFilter(level);
        levelFilter.start();
        appender.addFilter(levelFilter);
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setCharset(Charset.forName("UTF-8"));
        encoder.setContext(context);
        //设置日志格式
        encoder.setPattern("%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level [%thread] %logger{36} : %msg%n");
        encoder.start();
        appender.setEncoder(encoder);
        appender.start();
        return appender;
    }

    /**
     * 日志级别过滤器
     *
     * @param level
     * @return
     */
    private static LevelFilter getLevelFilter(Level level) {
        LevelFilter levelFilter = new LevelFilter();
        levelFilter.setLevel(level);
        levelFilter.setOnMatch(ACCEPT);
        levelFilter.setOnMismatch(DENY);
        return levelFilter;
    }

    public static void modifyAppenders(int level){
        if (level <= 1){
            return;
        }
        for (int i = 1;i <= level;i++){
            if (i == 2){
                appenderConcurrentHashMap.put(2,getAppender(Level.WARN));
            }
            if (i == 3){
                appenderConcurrentHashMap.put(3,getAppender(Level.INFO));
            }
            if (i == 4){
                appenderConcurrentHashMap.put(4,getAppender(Level.DEBUG));
            }
            if (i == level){
                List<Integer> levels = Arrays.asList(2,3,4).stream().filter(c->c>level).collect(Collectors.toList());
                for (Integer integer : levels) {
                    appenderConcurrentHashMap.remove(integer);
                }
            }
        }
    }
}
