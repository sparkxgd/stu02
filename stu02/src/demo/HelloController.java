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
    * �򿪵�¼ҳ��
    */
   public void openlogin() {
   	render("login.html");
   }
   /**
    * ��¼
    * 1���ж��û���
    * 2���ж�����
    */
   public void login() {
	   //1����ȡҳ���û���������
	   String username=getPara("username");
	   String password=getPara("password");
	   //2����mongodb���ݿ��ȡ�û���������
	   
	   MongoModel usermodel=new MongoModel("student", "userinfo");
	   
	   Document query=new Document();
	   query.append("username", username);
	   
	   List<Document> list= usermodel.find(query);
	   
	   //3���ж��û����Ƿ����
	   if(list.size()>0) {//�����û���
		   //��ȡ���ݿ������
		   String pw=list.get(0).getString("passsword");
		   //�ж�����
		   if(password.equals(pw)) {//������ȷ
			   //������ҳ��
			   setAttr("result", 0);
			   renderJson();
		   }else {
			   //��ʾ�������������������
			   setAttr("result", 1);
			   renderJson();
		   }
		   
	   }else {//�������û���
		   //��ʾ�û��������ڣ����������û���������
		   setAttr("result", -1);
		   renderJson();
	   }
	   
   }
   /**
    * ����ҳ��
    */
   public void openmain() {
	   render("main.html");
   }
   /**
    * ��ȡѧ����Ϣ�б�
    */
   public void getstudents() {
	   MongoModel stus=new MongoModel("student", "studentinfo");
	   Document query=new Document();
	   
	   List<Document> list=stus.find(query);
	   
	   setAttr("list", list);
	   renderJson();
   }
   
   /**
    * �������Ϣҳ��
    */
   public void openadd() {
	   render("add.html");
   }
   /**
    * ������Ϣ
    */
   public void add() {
	   String no=getPara("no");
	   String name=getPara("name");
	   String sex=getPara("sex");
	   int age=getParaToInt("age");
   }
   
}
