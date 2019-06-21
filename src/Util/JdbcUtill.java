package Util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import pojo.Student;




public class JdbcUtill {
	private static Logger log=Logger.getLogger(JdbcUtill.class);
	private static String driver;
	private static String username;
	private static String url;
	private static String password;

	static {
		// ����Properties����
		Properties p = new Properties();

		// ���²�����ʹ�����ռ����Ҳ���ó����ҵ�db�ļ� ��bin�ļ�����
		InputStream is = JdbcUtill.class.getResourceAsStream("/db.properties");
		
		try {
			p.load(is);// load����Զ�ȡ�ļ������Ϣ��
			driver = p.getProperty("driver");
			username = p.getProperty("username");
			url = p.getProperty("url");
			password = p.getProperty("password");
			// ��������
			Class.forName(driver);

		} catch (IOException e) {
			e.printStackTrace();
			log.fatal("�����ļ������쳣");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

		// ��ȡ���Ӷ���
	
		public static Connection getConnection() {
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return conn;
		}
	
		// ��ȡstatement
		public static Statement getStatement(Connection conn) {
			Statement st = null;
			try {
				st = conn.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			log.debug("��ѯ����Ա���ɹ�");
			return st;
		}
		
		//��ȡpreparedstatement
		public static java.sql.PreparedStatement getPreparedStatement(String sql,Connection conn) {
			java.sql.PreparedStatement ps=null;
			try {
				ps=conn.prepareStatement(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return  ps;
		}
		
		//ִ����ɾ�ĵķ���
		public static int executeDML(String sql,Object...objs) {
			Connection conn=getConnection();
			PreparedStatement ps=getPreparedStatement(sql, conn);
			int i=-1;
			try {
				//����Ϊ�ֶ��ύ
				conn.setAutoCommit(false);
				//���ò���
				for(int x=0;x<objs.length;x++) {
					ps.setObject(x+1,objs[x]);
				}
				i=ps.executeUpdate();
				conn.commit();
				delease(conn, ps);
				return i;
			} catch (SQLException e) {
				e.printStackTrace();
				conn.rollback();
			}finally {
				return i;
			}
			
			
		}
		
		//�ͷ���Դ�ķ���
		public static void delease(Connection conn,Statement st,ResultSet rs) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//����:��ѯѧ����Ϣ by sid
		public static Student QueryI(int sid) {
			Student s=null;
			Connection conn=getConnection();
			PreparedStatement ps=getPreparedStatement("select * from sheet1 where sid=?", conn);
			try {
				conn.setAutoCommit(false);
				ps.setObject(1, sid);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					s=new Student();
					s.setScore1(rs.getDouble("score1"));
					s.setScore2(rs.getDouble("score2"));
					s.setScore3(rs.getDouble("score3"));
					s.setSid(rs.getInt("sid"));
					s.setSname(rs.getString("sname"));
				}
					delease(conn, ps);
					return s;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}


		}
		//����:��ѯѧ����Ϣ by sname
				public static Student QueryN(String sname) {
					Student s=null;
					Connection conn=getConnection();
					PreparedStatement ps=getPreparedStatement("select * from sheet1 where sname=?", conn);
					try {
						conn.setAutoCommit(false);
						ps.setObject(1, sname);
						ResultSet rs = ps.executeQuery();
						if(rs.next()) {
							s=new Student();
							s.setScore1(rs.getDouble("score1"));
							s.setScore2(rs.getDouble("score2"));
							s.setScore3(rs.getDouble("score3"));
							s.setSid(rs.getInt("sid"));
							s.setSname(rs.getString("sname"));
						}
							delease(conn, ps);
							return s;
					} catch (SQLException e) {
						e.printStackTrace();
						return null;
					}
				}
		//���� ��ѯ����ѧ����Ϣ
		public static myLinkedList<Student> QueryAll(String sql) throws SQLException {
			//List stu
			myLinkedList<Student> list=new myLinkedList<Student>();
			Student s=null;
			//��ȡ���Ӷ����sql��������
			Connection conn=getConnection();
			PreparedStatement pst=getPreparedStatement(sql, conn);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				s=new Student();
				s.setScore1(rs.getDouble("score1"));
				s.setScore2(rs.getDouble("score2"));
				s.setScore3(rs.getDouble("score3"));
				s.setSid(rs.getInt("sid"));
				s.setSname(rs.getString("sname"));
				list.add(s);
			}
			return list;
		}
		//�����ͷ���Դ�ķ���
		public static void delease(Connection conn,Statement st) {
			
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
