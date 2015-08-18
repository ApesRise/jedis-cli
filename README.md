# jedis-cli

provides execute method; 

you can use the Jedis to execute the command

this project dependency jedis2.7.2

example

	public class TestJedisCli {
    	@Test
	    public void sendCommandTest() throws JedisCliException {
    	    JedisCli cli = new JedisCli("127.0.0.1",6379);
	        System.out.println(cli.sendCommand("smembers myset"));
	        System.out.println(cli.sendCommand("mset test heh"));
	        System.out.println(cli.sendCommand("mget test"));
    	    cli.close();
	    }

	}

