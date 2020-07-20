package employee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/UpdateEmpSal")
public class UpdateEmpSal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UpdateEmpSal() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String salary = request.getParameter("salary");
		String empId = request.getParameter("empId"); 
		EmpDao dao = new EmpDao();
		dao.updateSal(empId, salary);
		
//		String action = request.getParameter("action"); //하나의 서블릿에 여러가지 처리를 해주고 싶을때 if를 써서 사용 할 수 있다.
//		if(action.equals("update")) 
//		{
//			EmpDao dao = new EmpDao();
//			dao.updateSal(empId, salary);
//		}
		
		
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
