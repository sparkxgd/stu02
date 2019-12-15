package demo;
import java.util.List;

import org.bson.Document;

import com.jfinal.core.Controller;
public class HelloController extends Controller {
    public void index() {
       renderText("Hello JFinal World.");
    }
    /**
     * 打开登录页面
     */
    public void openlogin() {
    	render("login.html");
    }
    /**
     * 登录
     */
    public void login() {
    	//1、判断用户名是否存在？
    	//2、如果存在，就判断密码？
    	//3、如果密码正确，就跳入主页面
    	//4、如果密码错误，就回到登录页面，提示密码错位
    	//5、如果用户不存在，就回到登录页面，提示用户不存在
    	
    	String username=getPara("username");
    	String password=getPara("password");
    	
    	//模拟mongodb数据库的用户名和密码
    	StudentModel stus=new StudentModel();
    	Document query=new Document();
    	query.append("username", username);
    	
    	List<Document> docs=stus.find(query);
    	
    	if(docs.size()>0) {//说明用户名存在
    		String pw=docs.get(0).getString("passsword");
    		if(password.equals(pw)) {
    			setAttr("result", 0);//密码正确
    			renderJson();
    		}else {
    			setAttr("result", 1);//密码错误
    			renderJson();
    		}
    	}else {
    		setAttr("result", -1);//用户名不存在
			renderJson();
    	}
    }
    /**
     * 进入主页面
     */
    public void main() {
    	render("main.html");
    }
    /**
     * 获取学生信息列表
     */
    public void getstudents() {
    	String no=getPara("no","");
    	StudentModel stu=new StudentModel();
    	List<Document> list=stu.query(no);//到mongodb数据拿数据
    	setAttr("list", list);
    	renderJson();   
    	
    }
    /**
     * 打开添加
     */
    public void openaddstu() {
    	render("addstu.html");
    }
    /**
     * 保存学生信息
     */
    public void savestu() {
    	String no=getPara("no");
    	String name=getPara("name");
    	String sex=getPara("sex");
    	String age=getPara("age");
    	
    	StudentModel m=new StudentModel();
    	
    	Document doc=new Document();
    	doc.append("no", no);
    	doc.append("name", name);
    	doc.append("sex", sex);
    	doc.append("age", age);
    	
    	m.insert(doc);
    	redirect("main");
    }
    /**
     * 删除学生信息
     */
    public void delstu() {
    	String no=getPara("no");
    	
    	StudentModel m=new StudentModel();
    	Document doc=new Document();
    	doc.append("no", no);
    	
    	m.delete(doc);
    	
    	renderJson();
    	
    }
    
	/**
	 * 打开编辑页面
	 */
	public void openedit() {
		String no = getPara("no");
		setAttr("no", no);
		renderFreeMarker("stuedit.html");
	}

	/**
	 * 获取学生信息，用来编辑
	 */
	public void getstubyno() {
		String no = getPara("no");
		StudentModel m = new StudentModel();
		List<Document> list = m.find(new Document("no", no));
		setAttr("m", list.get(0));
		renderJson();
	}

	public void updatastu() {

		String no = getPara("no");
		String name = getPara("name");
		String sex = getPara("sex");
		int age = getParaToInt("age");

		StudentModel m = new StudentModel();
		Document data = new Document();
		data.append("no", no);
		data.append("sex", sex);
		data.append("age", age);
		data.append("name", name);
		m.updataOne(data);
		redirect("main");

	}
}
