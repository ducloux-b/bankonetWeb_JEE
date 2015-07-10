package com.bankonet.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bankonet.model.BankonetException;
import com.bankonet.model.Client;
import com.bankonet.service.BanqueService;
import com.bankonet.service.BanqueServiceManager;

/**
 * Servlet implementation class TraiterLoginServlet
 */
@WebServlet("/traiterLogin")
public class TraiterLoginServlet extends HttpServlet
	{
	private static final long	serialVersionUID	= 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TraiterLoginServlet()
		{
		super();
		// TODO Auto-generated constructor stub
		}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void
			doGet(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException
		{
		// TODO Auto-generated method stub
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(	HttpServletRequest request,
									HttpServletResponse response)
			throws ServletException, IOException
		{
		
		}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
		{		
		String login= (String)request.getParameter("login");
		String motDePasse= (String)request.getParameter("motDePasse");
		
		BanqueService banqueService= BanqueServiceManager.getBanqueService();
		try
			{
			Client c= banqueService.findClient(login, motDePasse);
			
			HttpSession session = request.getSession(true); 
			session.setAttribute("client", c);
			
			//this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			
			this.getServletContext().getRequestDispatcher("/pagePrincipale.jsp").forward(request, response);
			}
		catch(BankonetException e)
			{
			// TODO Auto-generated catch block
			System.out.println("Connexion impossible!!!");
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(request, response);
			e.printStackTrace();
			}
		}
	}
