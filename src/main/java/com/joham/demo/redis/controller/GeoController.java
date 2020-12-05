package com.joham.demo.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Redis GEO
 *
 * @author joham
 */
@RestController
@RequestMapping("/geo")
public class GeoController {

    @Autowired
    private RedisTemplate redisTemplate;

    public static final String GEO_KEY = "city";

    @PostMapping("/testAdd")
    public void testAdd() {
        Long addedNum = redisTemplate.opsForGeo().add(GEO_KEY, new Point(116.405285, 39.904989), "北京");
        System.out.println(addedNum);
    }

    @PostMapping("/testGeoGet")
    public void testGeoGet() {
        List points = redisTemplate.opsForGeo().position(GEO_KEY, "北京", "上海", "深圳");
        System.out.println(points);
    }

    @PostMapping("/testDist")
    public void testDist() {
        Distance distance = redisTemplate.opsForGeo().distance(GEO_KEY, "北京", "上海", RedisGeoCommands.DistanceUnit.KILOMETERS);
        System.out.println(distance);
    }

    @PostMapping("/testNearByXY")
    public void testNearByXY() {
        //longitude,latitude
        Circle circle = new Circle(116.405285, 39.904989, Metrics.KILOMETERS.getMultiplier());
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates().sortAscending().limit(5);
        GeoResults<RedisGeoCommands.GeoLocation<String>> results = redisTemplate.opsForGeo().radius(GEO_KEY, circle, args);
        System.out.println(results);
    }

    @PostMapping("/testNearByPlace")
    public void testNearByPlace() {
        Distance distance = new Distance(5, Metrics.KILOMETERS);
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates().sortAscending().limit(5);
        GeoResults<RedisGeoCommands.GeoLocation<String>> results = redisTemplate.opsForGeo().radius(GEO_KEY, "北京", distance, args);
        System.out.println(results);
    }

    @PostMapping("/testGeoHash")
    public void testGeoHash() {
        List<String> results = redisTemplate.opsForGeo().hash(GEO_KEY, "北京", "上海", "深圳");
        System.out.println(results);
    }
}
