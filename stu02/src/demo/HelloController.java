package demo;
import com.jfinal.core.Controller;
import java.util.List;
import org.bson.Document;
public class HelloController extends Controller {
    public void index() {
       renderText("Hello JFinal World.");
    }
   public void mongodbtest() {
	   render("querylist.html");
   }
   /**
    * 打开登录页面
    */
   public void openlogin() {
   	render("login.html");
   }
   /**
    * 登录
    * 1、判断用户名
    * 2、判断密码
    */
   public void login() {
	   //1、获取页面用户名和密码
	   String username=getPara("username");
	   String password=getPara("password");
	   //2、从mongodb数据库获取用户名和密码
	   
	   MongoModel usermodel=new MongoModel("student", "userinfo");
	   
	   Document query=new Document();
	   query.append("username", username);
	   
	   List<Document> list= usermodel.find(query);
	   
	   //3、判断用户名是否存在
	   if(list.size()>0) {//存在用户名
		   //获取数据库的密码
		   String pw=list.get(0).getString("passsword");
		   //判断密码
		   if(password.equals(pw)) {//密码正确
			   //进入主页面
			   setAttr("result", 0);
			   renderJson();
		   }else {
			   //提示密码错误，重新输入密码
			   setAttr("result", 1);
			   renderJson();
		   }
		   
	   }else {//不存在用户名
		   //提示用户名不存在，重新输入用户名和密码
		   setAttr("result", -1);
		   renderJson();
	   }
	   
   }
   /**
    * 打开主页面
    */
   public void openmain() {
	   render("main.html");
   }
   /**
    * 获取学生信息列表
    */
   public void getstudents() {
	   MongoModel stus=new MongoModel("student", "studentinfo");
	   Document query=new Document();
	   
	   List<Document> list=stus.find(query);
	   
	   setAttr("list", list);
	   renderJson();
   }
   
   /**
    * 打开添加信息页面
    */
   public void openadd() {
	   render("add.html");
   }
   /**
    * 保存信息
    */
   public void add() {
	   String no=getPara("no");
	   String name=getPara("name");
	   String sex=getPara("sex");
	   int age=getParaToInt("age");
   }
   
}
