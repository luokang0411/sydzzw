package com.geostar.zrzy.sydzzw.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TestTimer {

    private final static Logger logger = LoggerFactory.getLogger(TestTimer.class);

    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Scheduled(cron = "*/5 * * * * ?")
    @Async
    public void scheduled() {
        logger.info("定时器任务开始了：{}", sdf.format(new Date()));
        try {
            Thread.sleep(1000 * 6);
        } catch (InterruptedException e) {
            logger.info(e.getMessage(), e);
        }
    }

}
