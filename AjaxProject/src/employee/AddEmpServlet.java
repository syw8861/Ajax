package employee;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AddEmpServlet")
public class AddEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AddEmpServlet() 
    {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response); //포스트를 호출하더라도 여기안에서 다시 겟을 호출해서 사용한다.
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String lastName = request.getParameter("last_name");
		String email = request.getParameter("email");
		String hireDate = request.getParameter("hire_date");
		String jobId = request.getParameter("job_id");
		
		Employee emp = new Employee(email,hireDate,lastName,jobId);
		EmpDao dao = new EmpDao();
		try {
			dao.insertEmp(emp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("employeeList.html").forward(request, response);
		
	}
}
