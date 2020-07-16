package employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetEmpServlet
 */
@WebServlet("/GetEmpServlet")
public class GetEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetEmpServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
//		out.write("[{\"id\":\"users1\",\"first_name\":\"hong\",\"age\":30},");
//		out.write("{\"id\":\"users1\",\"first_name\":\"hong\",\"age\":30}]");
		EmpDao dao = new EmpDao();
		List<Employee> list = dao.getEmpList();
		
		int cnt = 0;
		int rowCnt = list.size();
		out.write("[");
		for(Employee emp : list) {
			out.write("{\"id\":\""+emp.getEmployeeId()+"\","+
					  "\"first_name\":\""+emp.getFirstName()+"\","+
					  "\"email\":\""+emp.getEmail()+"\","+
				      "\"salary\":\""+emp.getSalary()+"\"}");
			
			if(++cnt != rowCnt) {
				out.write(",");
			}
		}
		out.write("]");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
