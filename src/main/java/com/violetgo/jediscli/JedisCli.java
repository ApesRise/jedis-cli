package com.violetgo.jediscli;

import redis.clients.jedis.BuilderFactory;
import redis.clients.jedis.Protocol;
import redis.clients.util.RedisInputStream;
import redis.clients.util.RedisOutputStream;
import redis.clients.util.SafeEncoder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * @author weigao
 * @since 6/29/15
 */
public class JedisCli {
    private String host;
    private int port = 6379;
    private Socket socket;
    private RedisInputStream instream;
    private RedisOutputStream outstream;

    public JedisCli(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * connect to server
     */
    private void connect() throws JedisCliException {
        if (!isConnected()) {
            try {
                socket = new Socket();
                socket.setReuseAddress(true);
                socket.setKeepAlive(true);
                socket.setTcpNoDelay(true);
                socket.setSoLinger(true, 0);
                socket.connect(new InetSocketAddress(host, port), 3000);
                outstream = new RedisOutputStream(socket.getOutputStream());
                instream = new RedisInputStream(socket.getInputStream());
            } catch (IOException e) {
                throw new JedisCliException("failed to connect server", e);
            }
        }
    }

    private boolean isConnected() {
        return socket != null && socket.isBound() && !socket.isClosed() && socket.isConnected()
                && !socket.isInputShutdown() && !socket.isOutputShutdown();
    }

    /**
     * case result to String just like u look at in terminal
     *
     * @param obj
     * @return
     */
    private String caseResultToString(Object obj) {
        if (obj instanceof byte[]) {
            return new String((byte[]) obj);
        } else if (obj instanceof Long) {
            return ((Long) obj).toString();
        } else if (obj instanceof List) {
            List temp = (List) obj;
            if (temp.size() == 0) {
                return "";
            }

            StringBuilder sb = new StringBuilder();

            String className = temp.get(0).getClass().getName();
            String className2 = null;
            if (temp.size() > 1) {
                className2 = temp.get(1).getClass().getName();
            }
            List build = null;
            if (className2 == null || className2.equals(className)) {
                if (className.equals("[B")) {
                    build = BuilderFactory.STRING_LIST.build((List<byte[]>) temp);
                } else if (className.equals("java.lang.Long")) {
                    build = BuilderFactory.STRING_LIST.build((List<Long>) temp);
                } else if (className.equals("java.lang.Object")) {
                    build = BuilderFactory.STRING_LIST.build((List<Long>) temp);
                }
                // fix for sscan
            } else {
                sb.append(new String((byte[]) temp.get(0)));
                sb.append("\n");
                build = BuilderFactory.STRING_LIST.build(((List) (temp.subList(1, temp.size()))).get(0));
            }

            if (build == null) {
                return "";
            }

            for (Object cur : build) {
                sb.append(cur);
                sb.append("\n");
            }
            return sb.toString().trim();
        }
        return "";
    }

    /**
     * send command and get result String
     * if connect is close;
     * will auto connect
     *
     * @param command
     * @return
     * @throws JedisCliException when command error or can't connect to server
     */
    public String sendCommand(String command) throws JedisCliException {
        connect();
        String[] keys = command.split("\\s+");
        if (keys.length == 0) {
            throw new JedisCliException("command parse error,command is :"+command);
        }

        IProtocolCommand key = new IProtocolCommand(keys[0]);
        if (key.getRaw() == null) {
            throw new JedisCliException("command not valid ,command is :"+command);
        }

        final List<byte[]> values = new LinkedList<byte[]>();
        for (int i = 1; i < keys.length; i++) {
            values.add(SafeEncoder.encode(keys[i]));
        }

        Protocol.sendCommand(outstream, key, values.toArray(new byte[values.size()][]));
        try {
            outstream.flush();
        } catch (IOException e) {
            throw new JedisCliException("failed to flush command to redis", e);
        }
        try {
            Object obj = Protocol.read(instream);

            return caseResultToString(obj);
        } catch (Exception e) {
            throw new JedisCliException("failed to read redis result", e);
        }
    }

    /**
     * close redis server connection
     *
     * @throws JedisCliException when close fail
     */
    public void close() throws JedisCliException {
        try {
            if (instream != null) {
                instream.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (outstream != null) {
                outstream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            throw new JedisCliException("failed to close", e);
        }
    }

}
