import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DbConnect {

	Connection conn1=null;
	Statement stmt=null;
	String user,pass;
	public DbConnect(String string, String pas) {
		// TODO Auto-generated constructor stub
	
			user=string;
			pass=pas;
	}
	public Connection login() {
		// TODO Auto-generated constructor stub
	
			
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn1=DriverManager.getConnection("jdbc:mysql://cs99.bradley.edu:3306/"+user, user, pass);
			}catch(Exception e){}
		
		return conn1;
	}


	public ResultSet show_tablename()
	{
		Connection con=login();
		ResultSet rs = null;
		try
		{
			
		stmt = con.createStatement();
	      String sql;
	      sql = "show tables";
	       rs = stmt.executeQuery(sql);
		}catch(Exception e){}
	    return rs;
	}
	public ResultSet gettable(String string) {
		// TODO Auto-generated method stub
		Connection conn=login();
		ResultSet rs1=null;
		try {
			 rs1 = stmt.executeQuery("select * from "+string);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs1;
	}
	
	public int getcolumnnuber(String string) {
		// TODO Auto-generated method stub

		ResultSet rs=null;
		int column = 0;
		try
		{
		rs=gettable(string);
		ResultSetMetaData rsmd = rs.getMetaData();

		 column=rsmd.getColumnCount();
		}catch(Exception e){}
		return column;
	}
	public String operate_row(String sqlStatement) {
		// TODO Auto-generated method stub
		Connection conn=login();
		String ss=null;
		int i = 0;
		try {
			stmt=conn.createStatement();
			  i =stmt.executeUpdate(sqlStatement);
			  ss="Updated";
			 } catch (SQLException e) {
			// TODO Auto-generated catch block
			ss=e.toString();
		}
		
		return ss;
	}
	public String updaterow(String txt) {
		// TODO Auto-generated method stub
		Connection conn=login();
		String ss=null;
		int i=0;
		try{
			stmt=conn.createStatement();
			  i =stmt.executeUpdate(txt);
			  ss="Row Deleted";
			}
			catch(Exception e){
				ss=e.toString();
			}
		
		return ss;
	}
	public ResultSet undochanges(String table) {
		// TODO Auto-generated method stub
		Connection conn=login();
		ResultSet rs=null;
		try {
			
			conn.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
		
	}
	public ArrayList<String> multiplecolumns(Object[] i) {
		// TODO Auto-generated method stub
		int k=i.length;
		ArrayList<String> colum=new ArrayList<String>();
		
		for(int j=0;j<k;j++){
			ResultSet rs=gettable(i[j].toString());
			try {
				ResultSetMetaData rsmd=rs.getMetaData();
				for(int p=0;p<rsmd.getColumnCount();p++){
					colum.add(i[j].toString()+"."+rsmd.getColumnLabel(p+1));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return colum;
	}
	public ResultSet getresult(String query) {
		// TODO Auto-generated method stub
		Connection conn=login();
		ResultSet rs1=null;
		try {
			 rs1 = stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs1;}
	
}
