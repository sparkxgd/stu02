package demo;

import com.jfinal.plugin.IPlugin;
import com.mongodb.MongoClient;

/**
 * 负责链接mongodb的插件
 * @author Administrator
 *
 */
public class MongoConPlugin implements IPlugin{
	public static MongoClient mongoClient;
	private String ip;
	private int port;
	
	public MongoConPlugin(String ip,int port) {
		this.ip=ip;
		this.port=port;
	}
	@Override
	public boolean start() {
		// 连接到 mongodb 服务
        mongoClient = new MongoClient(ip , port );
		return true;
	}

	@Override
	public boolean stop() {
		
		return false;
	}



}
