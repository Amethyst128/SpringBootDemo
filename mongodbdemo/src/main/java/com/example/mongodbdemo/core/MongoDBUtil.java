package com.example.mongodbdemo.core;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc: 封装MongoDB工具类--连接数据库的工具类
 * @Author: zy
 * @Date:Created in 2019/7/23
 */
public class MongoDBUtil {

    /**
     * @desc 不通过认证，直接链接数据库对象
     */
    public static MongoDatabase getConnect() {
        //连接到mongoDB服务
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        //连接到具体某个数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
        return mongoDatabase;
    }

    /**
     * @desc 需要密码认证方式的连接
     */
    public static MongoDatabase getConnect2() {
        //ServerAddress
        List<ServerAddress> addresses = new ArrayList<>();
        //args= 服务器地址 端口号
        ServerAddress serverAddress = new ServerAddress("localhost", 27017);
        addresses.add(serverAddress);

        //MongoCredential
        List<MongoCredential> credentials = new ArrayList<>();
        //args= 用户名，数据库名，密码
        MongoCredential mongoCredential = MongoCredential.createScramSha1Credential("userName", "databaseName", "password".toCharArray());
        credentials.add(mongoCredential);

        //通过连接认证活得MongoDB连接
        MongoClient mongoClient = new MongoClient(addresses, credentials);
        //连接到具体某个数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
        return mongoDatabase;
    }

}
