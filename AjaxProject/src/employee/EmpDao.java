package employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpDao {

		Connection conn = null;

		public Connection getConnect() 
		{
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			try 
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, "hr", "hr");
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			} 
			return conn;
		}
		
		
		public Map<String, String> getJobCode() 
		{
			conn = getConnect();
			Map<String, String> map = new HashMap<>();
			String sql = "select job_id, job_title from jobs order by 1 ";
			try 
			{
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) 
				{
					map.put(rs.getString("job_id"), rs.getString("job_title"));
				}
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			} 
			finally 
			{
				try 
				{
					conn.close();
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			return map;
		}
		public void insertEmp(Employee emp) throws SQLException {				
			System.out.println(emp.getLastName() + " " + emp.getEmail() + " " + emp.getHireDate() + " " + emp.getJobId());
			conn = getConnect();

			String sql = "insert into employees (employee_id, last_name, email, hire_date, job_id) values(employees_seq.nextval,?,?,?,?)";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, emp.getLastName());
				pstmt.setString(2, emp.getEmail());
				pstmt.setString(3, emp.getHireDate());
				pstmt.setString(4, emp.getJobId());
				int r = pstmt.executeUpdate();
				System.out.println(r+"건 입력됌");
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				conn.close();
			}
		}
		
		
		public List<Employee> getEmpList(){
			conn = getConnect();
			List<Employee> list = new ArrayList<>();
			try {
			String sql = "select employee_id, first_name, email, salary from employees";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Employee emp = new Employee(rs.getInt("employee_id"),rs.getString("first_name"),
						rs.getString("email"),rs.getInt("salary"));
				list.add(emp);
			}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
}
