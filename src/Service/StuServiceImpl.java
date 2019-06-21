package Service;

import java.util.Scanner;

import Dao.StuDao;
import Dao.StuDaoImpl;
import Util.myLinkedList;
import pojo.Student;

public class StuServiceImpl implements StuService {
	Scanner sc = new Scanner(System.in);
	// ����Dao����
	StuDao sd = new StuDaoImpl();

	// ����ѧ����Ϣ
	@Override
	public void add_Info() {

		System.out.println("������ѧ��ѧ��");
		int sid = sc.nextInt();
		// У��ѧ���Ƿ������ݿ����ظ�
		myLinkedList<Student> list = sd.sel_allInfo();
		if (list != null) {
			Student s = null;
			for (int x = 0; x < list.size; x++) {
				s = list.get(x);
				if (sid == s.getSid()) {
					System.out.println("�ɼ��������д�ѧ��ѧ����");
					return;
				}
			}
		}
		System.out.println("������ѧ������");
		String sname = sc.next();
		System.out.println("������ѧ��ѧ��һ�ɼ������޳ɼ���������0");
		double score1 = sc.nextDouble();
		System.out.println("������ѧ��ѧ�ƶ��ɼ������޳ɼ���������0");
		double score2 = sc.nextDouble();
		System.out.println("������ѧ��ѧ����һ�ɼ������޳ɼ���������0");
		double score3 = sc.nextDouble();
		int result = sd.add_Info(sid, sname, score1, score2, score3);
		if (result == 1) {
			System.out.println("���ӳɹ�:" );
			System.out.println("ѧ��\t\t����\tѧ��һ\tѧ�ƶ�\tѧ����\t�ܳɼ�\t");
			System.out.println(sid +"\t"+ sname + "\t" + score1 + "\t" + score2 + "\t" + score3+"\t"+(score1+score2+score3));
		} else {
			System.out.println("���ʧ�ܣ������ԣ�");
		}
	}

	// ɾ��ѧ����Ϣ
	@Override
	public void del_Info() {
		System.out.println("������Ҫɾ��ѧ����ѧ��");
		int sid = sc.nextInt();
		int result = sd.del_Info(sid);
		if (result == 1) {
			System.out.println("ɾ���ɹ���");
		} else {
			System.out.println("��ѧ��������");
		}
	}

	// �޸�ѧ���ɼ�
	@Override
	public void mod_Info() {
		System.out.println("������Ҫ�޸ĳɼ�ѧ����ѧ��");
		int sid = sc.nextInt();
		// �����޸ĳɼ�ʱ�Ƿ���ڴ�ѧ�ŵ�ѧ��
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
			System.out.println("������Ҫ�޸ĵĿ�Ŀ(�����ż���)��1��score1,2��score2,3:score3");
			int classId = sc.nextInt();
			Student s = sd.sel_InfoById(sid);
			if (classId == 1 || classId == 2 || classId == 3) {
				System.out.println("������ÿ�Ŀ�ɼ�");
				int score = sc.nextInt();
				int result = sd.mod_Info(sid, score, classId);
				if (result > 0) {
					System.out.println("�޸ĳɹ���");
					sel_allInfo();
				} else {
					System.out.println("�޸�ʧ�ܣ�������!");
				}
			} else {
				System.out.println("��������ȷ�Ŀ�Ŀ��ţ��Ƿ������   1����������������:�˳�");
				int flag = sc.nextInt();
				if (flag == 1) {
					mod_Info();
				}
			}
		} else {
			System.out.println("�޴�ѧ�������������ѧ�ţ�");
		}

	}

	// ��ѯѧ����Ϣ by Id
	@Override
	public void sel_InfoById() {
		System.out.println("������Ҫ��ѯѧ����ѧ��");
		int sid = sc.nextInt();
		Student s = sd.sel_InfoById(sid);
		if (s != null) {
			System.out.println("ѧ��\t\t����\tѧ��һ\tѧ�ƶ�\tѧ����\t�ܳɼ�\t");
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
			System.out.println("���޴��ˣ�");
		}
	}
	// ��ѯѧ����Ϣ by Name
		@Override
		public void sel_InfoByName() {
			System.out.println("������Ҫ��ѯѧ��������");
			String sname = sc.next();
			Student s = sd.sel_InfoByName(sname);
			if (s != null) {
				System.out.println("ѧ��\t\t����\tѧ��һ\tѧ�ƶ�\tѧ����\t�ܳɼ�\t");
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
				System.out.println("���޴��ˣ�");
			}
		}
	// ��ѯ����ѧ���ɼ� ����
	@Override
	public void sel_allInfo() {
		myLinkedList<Student> list = null;
		list = sd.sel_allInfo();
		if (list == null) {
			System.out.println("Ŀǰϵͳ����ѧ����Ϣ");
		} else {
			System.out.println("ѧ��\t\t����\tѧ��һ\tѧ�ƶ�\tѧ����\t�ܳɼ�\t");
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
