package main;

import java.util.Scanner;

import Service.StuService;
import Service.StuServiceImpl;

public class test {
	public static void main(String[] args) {
		//创建服务层对象
		StuService ss=new StuServiceImpl();
		do{
			System.out.println();
			System.out.println("******欢迎访问学生成绩管理系统:请选择服务********");
			System.out.println("1.阅览所有成绩");
			System.out.println("2.查询学生成绩");
			System.out.println("3.登记学生成绩");
			System.out.println("4.修改学生成绩");
			System.out.println("5.删除学生成绩");
			System.out.println("6.退出系统");
			Scanner sc=new Scanner(System.in);
			int ch=sc.nextInt();
			switch (ch) {
			case 1:
				ss.sel_allInfo();
				break;
			case 2:
				System.out.println("(输入1):按照姓名查询 -----(输入2):按照学号查询");
				int choose=sc.nextInt();
				if(choose==1) {
				ss.sel_InfoByName();
				}else if(choose==2){
					ss.sel_InfoById();
				}
				else
					System.out.println("输入错误");
				break;
			case 3:
				ss.add_Info();
				break;
			case 4:
				ss.mod_Info();
				break;
			case 5:
				ss.del_Info();
				break;
			case 6:
				System.out.println("谢谢使用");
				return;
			default:
				System.out.println("输入有误,请重新选择");
				break;
			}
		}while(true);
	}
}
