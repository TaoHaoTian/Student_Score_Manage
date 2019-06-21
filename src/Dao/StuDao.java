package Dao;

import java.sql.SQLException;
import java.util.List;

import Util.myLinkedList;
import pojo.Student;

public interface StuDao {
		//����ѧ����Ϣ
		int add_Info(int sid,String sname,double score1,double score2,double score3);
		//ɾ��ѧ����Ϣ
		int del_Info(int sid);
		//����ѧ����Ϣ
		int mod_Info(int sid,int score, int classId);
		//��ѯѧ����Ϣ by Id
		Student sel_InfoById(int sid);
		//��ѯѧ����Ϣ by Name
		Student sel_InfoByName(String name);
		//��ѯ����ѧ���ɼ� ����
		myLinkedList<Student> sel_allInfo();
}
