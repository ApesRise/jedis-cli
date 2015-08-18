package com.violetgo.jediscli;

import org.junit.Test;

/**
 * @author weigao
 * @since 15/8/18
 */
public class TestJedisCli {
    @Test
    public void sendCommandTest() throws JedisCliException {
        JedisCli cli = new JedisCli("127.0.0.1",6379);
        System.out.println(cli.sendCommand("smembers myset"));
        System.out.println(cli.sendCommand("mset test heh"));
        System.out.println(cli.sendCommand("mget test"));
        cli.close();
        System.out.println(cli.sendCommand("mset test heh2"));
        System.out.println(cli.sendCommand("mget test"));
    }

}
