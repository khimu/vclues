package com.vclues.config;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/*
 * Allows for clustering
 */
@EnableRedisHttpSession//(maxInactiveIntervalInSeconds = 0)
public class HttpSessionConfig {

}
