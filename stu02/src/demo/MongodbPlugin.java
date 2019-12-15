package demo;

import com.jfinal.plugin.IPlugin;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * 链接mongodb的插件
 * @author Administrator
 *
 */
public class MongodbPlugin implements IPlugin{
	public static MongoDatabase mongoDatabase;//链接mongodb的数据库
	private String host;
	private int port;
	private String dbname;
	private MongoClient mongoClient;
	
	public MongodbPlugin(String host,int port,String dbname) {
		this.host=host;
		this.port=port;
		this.dbname=dbname;
	}
	@Override
	public boolean start() {
		try {
			// 连接到 mongodb 服务120.79.42.237
			mongoClient= new MongoClient(host,port);
			mongoDatabase=mongoClient.getDatabase(dbname);
	         // 连接到数据库
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean stop() {
		mongoClient.close();
		return true;
	}

}
