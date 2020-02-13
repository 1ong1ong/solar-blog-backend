//package com.cxlsky.config;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.parser.ParserConfig;
//import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.interceptor.KeyGenerator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import java.time.Duration;
//
///**
// * className RedisConfig
// * description RedisConfig
// *
// * @author Toby
// * @date 2019-5-20
// */
//@Configuration
//@EnableCaching
//public class RedisConfig extends CachingConfigurerSupport {
//
//    @Bean("cacheManager1Month")
//    public CacheManager cacheManager1Month(RedisConnectionFactory factory) {
//        return getCacheManager(factory, Duration.ofDays(30));
//    }
//
//    @Bean("cacheManager1Hour")
//    public CacheManager cacheManager1Hour(RedisConnectionFactory factory) {
//        return getCacheManager(factory, Duration.ofHours(1));
//    }
//
//    @Bean("cacheManager1Day")
//    @Primary
//    public CacheManager cacheManager1Day(RedisConnectionFactory factory) {
//        return getCacheManager(factory, Duration.ofDays(1));
//    }
//
//    @Bean("cacheManager1Week")
//    public CacheManager cacheManager1Week(RedisConnectionFactory factory) {
//        return getCacheManager(factory, Duration.ofDays(7));
//    }
//
//    @Bean
//    @Override
//    public KeyGenerator keyGenerator() {
//        return (target, method, params) -> {
//            StringBuilder sb = new StringBuilder();
//            sb.append(target.getClass().getName());
//            sb.append(method.getName());
//            for (Object obj : params) {
//                // 由于参数可能不同, hashCode肯定不一样, 缓存的key也需要不一样
//                sb.append(JSON.toJSONString(obj).hashCode());
//            }
//            return sb.toString();
//        };
//    }
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        //序列化
//        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
//        // value值的序列化采用fastJsonRedisSerializer
//        template.setValueSerializer(fastJsonRedisSerializer);
//        template.setHashValueSerializer(fastJsonRedisSerializer);
//        // 全局开启AutoType，不建议使用
//        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
//        // key的序列化采用StringRedisSerializer
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setHashKeySerializer(new StringRedisSerializer());
//        template.setConnectionFactory(factory);
//        return template;
//    }
//
//    private CacheManager getCacheManager(RedisConnectionFactory factory, Duration duration) {
//        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(duration)
//                .disableCachingNullValues();
//
//        return RedisCacheManager.builder(factory)
//                .cacheDefaults(config)
//                .transactionAware()
//                .build();
//    }
//}
