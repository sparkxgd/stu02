package demo;
import com.jfinal.core.Controller;
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
	   String user="201702";
	   String pw="123";
	   //3���ж��û����Ƿ����
	   if(username.equals(user)) {//�����û���
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
   
}
