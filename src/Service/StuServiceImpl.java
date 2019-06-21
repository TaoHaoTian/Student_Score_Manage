package Service;

import java.util.Scanner;

import Dao.StuDao;
import Dao.StuDaoImpl;
import Util.myLinkedList;
import pojo.Student;

public class StuServiceImpl implements StuService {
	Scanner sc = new Scanner(System.in);
	// 创建Dao对象
	StuDao sd = new StuDaoImpl();

	// 增加学生信息
	@Override
	public void add_Info() {

		System.out.println("请输入学生学号");
		int sid = sc.nextInt();
		// 校验学号是否与数据库中重复
		myLinkedList<Student> list = sd.sel_allInfo();
		if (list != null) {
			Student s = null;
			for (int x = 0; x < list.size; x++) {
				s = list.get(x);
				if (sid == s.getSid()) {
					System.out.println("成绩单中已有此学号学生！");
					return;
				}
			}
		}
		System.out.println("请输入学生姓名");
		String sname = sc.next();
		System.out.println("请输入学生学科一成绩，若无成绩，请输入0");
		double score1 = sc.nextDouble();
		System.out.println("请输入学生学科二成绩，若无成绩，请输入0");
		double score2 = sc.nextDouble();
		System.out.println("请输入学生学科三一成绩，若无成绩，请输入0");
		double score3 = sc.nextDouble();
		int result = sd.add_Info(sid, sname, score1, score2, score3);
		if (result == 1) {
			System.out.println("增加成功:" );
			System.out.println("学号\t\t姓名\t学科一\t学科二\t学科三\t总成绩\t");
			System.out.println(sid +"\t"+ sname + "\t" + score1 + "\t" + score2 + "\t" + score3+"\t"+(score1+score2+score3));
		} else {
			System.out.println("添加失败，请重试！");
		}
	}

	// 删除学生信息
	@Override
	public void del_Info() {
		System.out.println("请输入要删除学生的学号");
		int sid = sc.nextInt();
		int result = sd.del_Info(sid);
		if (result == 1) {
			System.out.println("删除成功！");
		} else {
			System.out.println("此学生不存在");
		}
	}

	// 修改学生成绩
	@Override
	public void mod_Info() {
		System.out.println("请输入要修改成绩学生的学号");
		int sid = sc.nextInt();
		// 检验修改成绩时是否存在此学号的学生
		myLinkedList<Student> list = sd.sel_allInfo();
		boolean flagg = false;
		if (list != null) {
			Student s = null;
			for (int x = 0; x < list.size; x++) {
				s = list.get(x);
				if (sid == s.getSid()) {
					flagg = true;
					break;
				}
			}
		}
		if (flagg) {
			System.out.println("请输入要修改的科目(输入编号即可)，1：score1,2：score2,3:score3");
			int classId = sc.nextInt();
			Student s = sd.sel_InfoById(sid);
			if (classId == 1 || classId == 2 || classId == 3) {
				System.out.println("请输入该科目成绩");
				int score = sc.nextInt();
				int result = sd.mod_Info(sid, score, classId);
				if (result > 0) {
					System.out.println("修改成功！");
					sel_allInfo();
				} else {
					System.out.println("修改失败，请重试!");
				}
			} else {
				System.out.println("请输入正确的科目编号！是否继续？   1：继续，其他数字:退出");
				int flag = sc.nextInt();
				if (flag == 1) {
					mod_Info();
				}
			}
		} else {
			System.out.println("无此学生，请检查输入的学号！");
		}

	}

	// 查询学生信息 by Id
	@Override
	public void sel_InfoById() {
		System.out.println("请输入要查询学生的学号");
		int sid = sc.nextInt();
		Student s = sd.sel_InfoById(sid);
		if (s != null) {
			System.out.println("学号\t\t姓名\t学科一\t学科二\t学科三\t总成绩\t");
			System.out.print(s.getSid());
			System.out.print("\t");
			System.out.print(s.getSname());
			System.out.print("\t");
			System.out.print(s.getScore1());
			System.out.print("\t");
			System.out.print(s.getScore2());
			System.out.print("\t");
			System.out.print(s.getScore3());
			System.out.print("\t");
			System.out.print(s.getScore3() + s.getScore2() + s.getScore1());
			System.out.print("\t");
		} else {
			System.out.println("查无此人！");
		}
	}
	// 查询学生信息 by Name
		@Override
		public void sel_InfoByName() {
			System.out.println("请输入要查询学生的姓名");
			String sname = sc.next();
			Student s = sd.sel_InfoByName(sname);
			if (s != null) {
				System.out.println("学号\t\t姓名\t学科一\t学科二\t学科三\t总成绩\t");
				System.out.print(s.getSid());
				System.out.print("\t");
				System.out.print(s.getSname());
				System.out.print("\t");
				System.out.print(s.getScore1());
				System.out.print("\t");
				System.out.print(s.getScore2());
				System.out.print("\t");
				System.out.print(s.getScore3());
				System.out.print("\t");
				System.out.print(s.getScore3() + s.getScore2() + s.getScore1());
				System.out.print("\t");
			} else {
				System.out.println("查无此人！");
			}
		}
	// 查询所有学生成绩 降序
	@Override
	public void sel_allInfo() {
		myLinkedList<Student> list = null;
		list = sd.sel_allInfo();
		if (list == null) {
			System.out.println("目前系统内无学生信息");
		} else {
			System.out.println("学号\t\t姓名\t学科一\t学科二\t学科三\t总成绩\t");
			Student s = null;
			for (int x = 0; x < list.size; x++) {
				s = list.get(x);
				System.out.print(s.getSid());
				System.out.print("\t");
				System.out.print(s.getSname());
				System.out.print("\t");
				System.out.print(s.getScore1());
				System.out.print("\t");
				System.out.print(s.getScore2());
				System.out.print("\t");
				System.out.print(s.getScore3());
				System.out.print("\t");
				System.out.print(s.getScore3() + s.getScore2() + s.getScore1());
				System.out.println("\t");
			}
		}
	}

}
