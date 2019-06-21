package Dao;

import java.sql.SQLException;
import java.util.List;

import javax.management.RuntimeErrorException;

import pojo.Student;
import Util.JdbcUtill;
import Util.myLinkedList;
public class StuDaoImpl implements StuDao{
	//增加学生信息
	@Override
	public int add_Info(int sid, String sname, double score1, double score2, double score3) {
		String sql="insert into sheet1 values(?,?,?,?,?)";	
		int result=JdbcUtill.executeDML(sql,sid,sname,score1,score2,score3);
		return result;
	}
	//删除学生信息
	
	@Override
	public int del_Info(int sid) {
		String sql="delete from sheet1 where sid=?";
		int result=JdbcUtill.executeDML(sql, sid);
		return result;
	}
	//修改学生信息
	@Override
	public int mod_Info(int sid, int score,int classId) {
		String clazz="";
		if(classId==1) {
			clazz="score1";
		}else if(classId==2) {
			clazz="score2";
		}else if(classId==3) {
			clazz="score3";
		}else {
			throw new RuntimeErrorException(null, "成绩科目编号错误");
		}
		String sql="update sheet1 set "+clazz+"=? where sid=?";
		int result = JdbcUtill.executeDML(sql,score,sid);
		return result;
	}

	//查询学生信息 by Id
	@Override
	public Student sel_InfoById(int sid) {
		String sql="select * from sheet1 where sid=?";
		return JdbcUtill.QueryI(sid);
		
	}
	//查询学生信息 by Name
		@Override
		public Student sel_InfoByName(String sname) {
			String sql="select * from sheet1 where sname=?";
			return JdbcUtill.QueryN(sname);
			
		}
	//查询所有学生成绩 降序
	@Override
	public myLinkedList<Student> sel_allInfo()  {
		String sql="select * from sheet1 order by (score1+score2+score3) desc";
		myLinkedList<Student> list=null;
		try {
			list=JdbcUtill.QueryAll(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
