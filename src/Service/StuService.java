package Service;

import java.sql.SQLException;

public interface StuService {
	//增加学生信息
	void add_Info();
	//删除学生信息
	void del_Info();
	//插入学生成绩
	void mod_Info();
	//查询学生信息 by Id
	void sel_InfoById();
	//查询学生信息 by Name
	void sel_InfoByName();
	//查询所有学生成绩 降序
	void sel_allInfo();
	
}
