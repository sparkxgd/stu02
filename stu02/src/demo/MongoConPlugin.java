package demo;

import com.jfinal.plugin.IPlugin;
import com.mongodb.MongoClient;

/**
 * ��������mongodb�Ĳ��
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
		// ���ӵ� mongodb ����
        mongoClient = new MongoClient(ip , port );
		return true;
	}

	@Override
	public boolean stop() {
		
		return false;
	}



}
