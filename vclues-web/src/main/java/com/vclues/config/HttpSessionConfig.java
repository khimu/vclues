package com.vclues.config;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession//(maxInactiveIntervalInSeconds = 0)
public class HttpSessionConfig {

}
