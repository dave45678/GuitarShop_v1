package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.CustomerDB;

/**
 * Servlet implementation class GetCustomerData
 */
@WebServlet("/GetCustomerData")
public class GetCustomerData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCustomerData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tableHead = "<tr><th>Customer Id</th><th>Customer Name</th><th>Email</th></tr>";
		String tableRow = "<tr><th>::id::</th><th><a href=\"GetCustomerDetail?id=::id::\">::name::</a></th><th>::email::</th></tr>";
		String builtTable = tableHead;
		
		List<model.Customer> custs = CustomerDB.retrieveAll();
		for(model.Customer cust : custs){
			builtTable += tableRow.replace("::id::",    Long.toString(cust.getCustomerId()))
							      .replace("::name::",  cust.getCustomerFirstName()+" "+cust.getCustomerLastName())
							      .replace("::email::", cust.getCustomerFax());
		}
		
		request.setAttribute("tabledata", builtTable);
		getServletContext().getRequestDispatcher("/customerdata.jsp")
			.forward(request, response);
		
	}

}
