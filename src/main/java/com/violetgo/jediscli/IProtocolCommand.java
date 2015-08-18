package com.violetgo.jediscli;

import redis.clients.jedis.ProtocolCommand;
import redis.clients.util.SafeEncoder;

import java.util.HashSet;
import java.util.Set;

/**
 * redis cmd key
 *
 * @author weigao
 * @since 15/8/18
 */
public class IProtocolCommand implements ProtocolCommand {
    private static Set<String> isValidCommand = new HashSet<String>();

    static {
        isValidCommand.add("PING");
        isValidCommand.add("SET");
        isValidCommand.add("GET");
        isValidCommand.add("QUIT");
        isValidCommand.add("EXISTS");
        isValidCommand.add("DEL");
        isValidCommand.add("TYPE");
        isValidCommand.add("FLUSHDB");
        isValidCommand.add("KEYS");
        isValidCommand.add("RANDOMKEY");
        isValidCommand.add("RENAME");
        isValidCommand.add("RENAMENX");
        isValidCommand.add("RENAMEX");
        isValidCommand.add("DBSIZE");
        isValidCommand.add("EXPIRE");
        isValidCommand.add("EXPIREAT");
        isValidCommand.add("TTL");
        isValidCommand.add("SELECT");
        isValidCommand.add("MOVE");
        isValidCommand.add("FLUSHALL");
        isValidCommand.add("GETSET");
        isValidCommand.add("MGET");
        isValidCommand.add("SETNX");
        isValidCommand.add("SETEX");
        isValidCommand.add("MSET");
        isValidCommand.add("MSETNX");
        isValidCommand.add("DECRBY");
        isValidCommand.add("DECR");
        isValidCommand.add("INCRBY");
        isValidCommand.add("INCR");
        isValidCommand.add("APPEND");
        isValidCommand.add("SUBSTR");
        isValidCommand.add("HSET");
        isValidCommand.add("HGET");
        isValidCommand.add("HSETNX");
        isValidCommand.add("HMSET");
        isValidCommand.add("HMGET");
        isValidCommand.add("HINCRBY");
        isValidCommand.add("HEXISTS");
        isValidCommand.add("HDEL");
        isValidCommand.add("HLEN");
        isValidCommand.add("HKEYS");
        isValidCommand.add("HVALS");
        isValidCommand.add("HGETALL");
        isValidCommand.add("RPUSH");
        isValidCommand.add("LPUSH");
        isValidCommand.add("LLEN");
        isValidCommand.add("LRANGE");
        isValidCommand.add("LTRIM");
        isValidCommand.add("LINDEX");
        isValidCommand.add("LSET");
        isValidCommand.add("LREM");
        isValidCommand.add("LPOP");
        isValidCommand.add("RPOP");
        isValidCommand.add("RPOPLPUSH");
        isValidCommand.add("SADD");
        isValidCommand.add("SMEMBERS");
        isValidCommand.add("SREM");
        isValidCommand.add("SPOP");
        isValidCommand.add("SMOVE");
        isValidCommand.add("SCARD");
        isValidCommand.add("SISMEMBER");
        isValidCommand.add("SINTER");
        isValidCommand.add("SINTERSTORE");
        isValidCommand.add("SUNION");
        isValidCommand.add("SUNIONSTORE");
        isValidCommand.add("SDIFF");
        isValidCommand.add("SDIFFSTORE");
        isValidCommand.add("SRANDMEMBER");
        isValidCommand.add("ZADD");
        isValidCommand.add("ZRANGE");
        isValidCommand.add("ZREM");
        isValidCommand.add("ZINCRBY");
        isValidCommand.add("ZRANK");
        isValidCommand.add("ZREVRANK");
        isValidCommand.add("ZREVRANGE");
        isValidCommand.add("ZCARD");
        isValidCommand.add("ZSCORE");
        isValidCommand.add("MULTI");
        isValidCommand.add("DISCARD");
        isValidCommand.add("EXEC");
        isValidCommand.add("WATCH");
        isValidCommand.add("UNWATCH");
        isValidCommand.add("SORT");
        isValidCommand.add("BLPOP");
        isValidCommand.add("BRPOP");
        isValidCommand.add("AUTH");
        isValidCommand.add("SUBSCRIBE");
        isValidCommand.add("PUBLISH");
        isValidCommand.add("UNSUBSCRIBE");
        isValidCommand.add("PSUBSCRIBE");
        isValidCommand.add("PUNSUBSCRIBE");
        isValidCommand.add("PUBSUB");
        isValidCommand.add("ZCOUNT");
        isValidCommand.add("ZRANGEBYSCORE");
        isValidCommand.add("ZREVRANGEBYSCORE");
        isValidCommand.add("ZREMRANGEBYRANK");
        isValidCommand.add("ZREMRANGEBYSCORE");
        isValidCommand.add("ZUNIONSTORE");
        isValidCommand.add("ZINTERSTORE");
        isValidCommand.add("ZLEXCOUNT");
        isValidCommand.add("ZRANGEBYLEX");
        isValidCommand.add("ZREVRANGEBYLEX");
        isValidCommand.add("ZREMRANGEBYLEX");
        isValidCommand.add("SAVE");
        isValidCommand.add("BGSAVE");
        isValidCommand.add("BGREWRITEAOF");
        isValidCommand.add("LASTSAVE");
        isValidCommand.add("SHUTDOWN");
        isValidCommand.add("INFO");
        isValidCommand.add("MONITOR");
        isValidCommand.add("SLAVEOF");
        isValidCommand.add("CONFIG");
        isValidCommand.add("STRLEN");
        isValidCommand.add("SYNC");
        isValidCommand.add("LPUSHX");
        isValidCommand.add("PERSIST");
        isValidCommand.add("RPUSHX");
        isValidCommand.add("ECHO");
        isValidCommand.add("LINSERT");
        isValidCommand.add("DEBUG");
        isValidCommand.add("BRPOPLPUSH");
        isValidCommand.add("SETBIT");
        isValidCommand.add("GETBIT");
        isValidCommand.add("BITPOS");
        isValidCommand.add("SETRANGE");
        isValidCommand.add("GETRANGE");
        isValidCommand.add("EVAL");
        isValidCommand.add("EVALSHA");
        isValidCommand.add("SCRIPT");
        isValidCommand.add("SLOWLOG");
        isValidCommand.add("OBJECT");
        isValidCommand.add("BITCOUNT");
        isValidCommand.add("BITOP");
        isValidCommand.add("SENTINEL");
        isValidCommand.add("DUMP");
        isValidCommand.add("RESTORE");
        isValidCommand.add("PEXPIRE");
        isValidCommand.add("PEXPIREAT");
        isValidCommand.add("PTTL");
        isValidCommand.add("INCRBYFLOAT");
        isValidCommand.add("PSETEX");
        isValidCommand.add("CLIENT");
        isValidCommand.add("TIME");
        isValidCommand.add("MIGRATE");
        isValidCommand.add("HINCRBYFLOAT");
        isValidCommand.add("SCAN");
        isValidCommand.add("HSCAN");
        isValidCommand.add("SSCAN");
        isValidCommand.add("ZSCAN");
        isValidCommand.add("WAIT");
        isValidCommand.add("CLUSTER");
        isValidCommand.add("ASKING");
        isValidCommand.add("PFADD");
        isValidCommand.add("PFCOUNT");
        isValidCommand.add("PFMERGE");
        isValidCommand.add("READONLY");
    }

    private byte[] raw = null;

    public IProtocolCommand(String key) {
        key = key.toUpperCase();
        if (isValidCommand.contains(key)) {
            raw = SafeEncoder.encode(key);
        }
    }

    @Override
    public byte[] getRaw() {
        return raw;
    }
}
