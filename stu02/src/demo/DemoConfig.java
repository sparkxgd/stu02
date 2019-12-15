package demo;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.template.Engine;
public class DemoConfig extends JFinalConfig {
    public void configConstant(Constants me) {
       me.setDevMode(true);
    }
    public void configRoute(Routes me) {
    	me.setBaseViewPath("/WEB-INF");
       me.add("/hello", HelloController.class,"/page");
    }
    public void configEngine(Engine me) {}
    public void configPlugin(Plugins me) {
    	MongodbPlugin mp=new MongodbPlugin("120.79.42.237",27017,"student");
    	me.add(mp);
    }
    public void configHandler(Handlers me) {}
	@Override
	public void configInterceptor(Interceptors arg0) {
		
	}
}
