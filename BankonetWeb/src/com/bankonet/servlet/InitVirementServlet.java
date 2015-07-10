package com.bankonet.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bankonet.model.Client;
import com.bankonet.model.Compte;

/**
 * Servlet implementation class InitVirementServlet
 */
@WebServlet("/InitVirementServlet")
public class InitVirementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitVirementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
		{
		HttpSession a_sess = request.getSession(false);
		if(a_sess != null)
			{
			Client client = (Client) request.getSession().getAttribute("client");
			List<Compte> comptes = client.getComptes();
			request.setAttribute("comptes", comptes);
			this.getServletContext().getRequestDispatcher("/virement.jsp").forward(request, response);
			}
		else
			{
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}
	
}
