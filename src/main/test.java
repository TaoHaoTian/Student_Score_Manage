package main;

import java.util.Scanner;

import Service.StuService;
import Service.StuServiceImpl;

public class test {
	public static void main(String[] args) {
		//������������
		StuService ss=new StuServiceImpl();
		do{
			System.out.println();
			System.out.println("******��ӭ����ѧ���ɼ�����ϵͳ:��ѡ�����********");
			System.out.println("1.�������гɼ�");
			System.out.println("2.��ѯѧ���ɼ�");
			System.out.println("3.�Ǽ�ѧ���ɼ�");
			System.out.println("4.�޸�ѧ���ɼ�");
			System.out.println("5.ɾ��ѧ���ɼ�");
			System.out.println("6.�˳�ϵͳ");
			Scanner sc=new Scanner(System.in);
			int ch=sc.nextInt();
			switch (ch) {
			case 1:
				ss.sel_allInfo();
				break;
			case 2:
				System.out.println("(����1):����������ѯ -----(����2):����ѧ�Ų�ѯ");
				int choose=sc.nextInt();
				if(choose==1) {
				ss.sel_InfoByName();
				}else if(choose==2){
					ss.sel_InfoById();
				}
				else
					System.out.println("�������");
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
				System.out.println("ллʹ��");
				return;
			default:
				System.out.println("��������,������ѡ��");
				break;
			}
		}while(true);
	}
}
