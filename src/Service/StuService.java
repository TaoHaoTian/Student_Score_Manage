package Service;

import java.sql.SQLException;

public interface StuService {
	//����ѧ����Ϣ
	void add_Info();
	//ɾ��ѧ����Ϣ
	void del_Info();
	//����ѧ���ɼ�
	void mod_Info();
	//��ѯѧ����Ϣ by Id
	void sel_InfoById();
	//��ѯѧ����Ϣ by Name
	void sel_InfoByName();
	//��ѯ����ѧ���ɼ� ����
	void sel_allInfo();
	
}
