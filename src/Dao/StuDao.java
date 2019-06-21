package Dao;

import java.sql.SQLException;
import java.util.List;

import Util.myLinkedList;
import pojo.Student;

public interface StuDao {
		//增加学生信息
		int add_Info(int sid,String sname,double score1,double score2,double score3);
		//删除学生信息
		int del_Info(int sid);
		//插入学生信息
		int mod_Info(int sid,int score, int classId);
		//查询学生信息 by Id
		Student sel_InfoById(int sid);
		//查询学生信息 by Name
		Student sel_InfoByName(String name);
		//查询所有学生成绩 降序
		myLinkedList<Student> sel_allInfo();
}
