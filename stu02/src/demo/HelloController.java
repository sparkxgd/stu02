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
	 * ��¼ 1���ж��û��� 2���ж�����
	 */
	public void login() {
		// 1����ȡҳ���û���������
		String username = getPara("username");
		String password = getPara("password");
		// 2����mongodb���ݿ��ȡ�û���������

		MongoModel usermodel = new MongoModel("student", "userinfo");

		Document query = new Document();
		query.append("username", username);

		List<Document> list = usermodel.find(query);

		// 3���ж��û����Ƿ����
		if (list.size() > 0) {// �����û���
			// ��ȡ���ݿ������
			String pw = list.get(0).getString("passsword");
			// �ж�����
			if (password.equals(pw)) {// ������ȷ
				// ������ҳ��
				setAttr("result", 0);
				renderJson();
			} else {
				// ��ʾ�������������������
				setAttr("result", 1);
				renderJson();
			}

		} else {// �������û���
				// ��ʾ�û��������ڣ����������û���������
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
		String no = getPara("no");
		MongoModel stus = new MongoModel("student", "studentinfo");
		
		Document query = new Document();

		if(!no.equals("")) {
			query.append("no", no);
		}
		List<Document> list = stus.find(query);

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
		String no = getPara("no");
		String name = getPara("name");
		String sex = getPara("sex");
		int age = getParaToInt("age");

		// �ж����ݿ����Ƿ��Ѿ�����ѧ��
		MongoModel m = new MongoModel("student", "studentinfo");
		List<Document> list = m.find(new Document("no", no));
		if (list.size() > 0) {// ˵���Ѿ�����ѧ��
			setAttr("result", -1);
		} else {
			Document data = new Document();
			data.append("no", no);
			data.append("sex", sex);
			data.append("age", age);
			data.append("name", name);
			m.insertOne(data);
			setAttr("result", 0);// ��������
		}
		renderJson();
	}

	/**
	 * ɾ��
	 */
	public void delete() {
		String no = getPara("no");
		Document query = new Document();
		query.append("no", no);
		MongoModel m = new MongoModel("student", "studentinfo");
		m.deleteOne(query);
		redirect("openmain");

	}

	/**
	 * �򿪱༭ҳ��
	 */
	public void openedit() {
		String no = getPara("no");
		setAttr("no", no);
		renderFreeMarker("stuedit.html");
	}

	/**
	 * ��ȡѧ����Ϣ�������༭
	 */
	public void getstubyno() {
		String no = getPara("no");
		MongoModel m = new MongoModel("student", "studentinfo");
		List<Document> list = m.find(new Document("no", no));
		setAttr("m", list.get(0));
		renderJson();
	}

	public void updatastu() {

		String no = getPara("no");
		String name = getPara("name");
		String sex = getPara("sex");
		int age = getParaToInt("age");

		MongoModel m = new MongoModel("student", "studentinfo");
		Document data = new Document();
		data.append("no", no);
		data.append("sex", sex);
		data.append("age", age);
		data.append("name", name);
		m.updateOne(data);
		redirect("openmain");

	}
}
