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
		// 创建Properties对象
		Properties p = new Properties();

		// 以下操作即使工作空间更改也会让程序找到db文件 在bin文件夹下
		InputStream is = JdbcUtill.class.getResourceAsStream("/db.properties");
		
		try {
			p.load(is);// load后可以读取文件里的信息了
			driver = p.getProperty("driver");
			username = p.getProperty("username");
			url = p.getProperty("url");
			password = p.getProperty("password");
			// 加载驱动
			Class.forName(driver);

		} catch (IOException e) {
			e.printStackTrace();
			log.fatal("配置文件加载异常");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

		// 获取连接对象
	
		public static Connection getConnection() {
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return conn;
		}
	
		// 获取statement
		public static Statement getStatement(Connection conn) {
			Statement st = null;
			try {
				st = conn.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			log.debug("查询所有员工成功");
			return st;
		}
		
		//获取preparedstatement
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
		
		//执行增删改的方法
		public static int executeDML(String sql,Object...objs) {
			Connection conn=getConnection();
			PreparedStatement ps=getPreparedStatement(sql, conn);
			int i=-1;
			try {
				//设置为手动提交
				conn.setAutoCommit(false);
				//设置参数
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
		
		//释放资源的方法
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
		
		//方法:查询学生信息 by sid
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
		//方法:查询学生信息 by sname
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
		//方法 查询所有学生信息
		public static myLinkedList<Student> QueryAll(String sql) throws SQLException {
			//List stu
			myLinkedList<Student> list=new myLinkedList<Student>();
			Student s=null;
			//获取连接对象和sql操作对象
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
		//重载释放资源的方法
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
