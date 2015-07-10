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
import com.bankonet.model.Compte;
import com.bankonet.model.Virement;
import com.bankonet.service.BanqueService;
import com.bankonet.service.BanqueServiceManager;

/**
 * Servlet implementation class TraiterVirementServlet
 */
@WebServlet("/TraiterVirementServlet")
public class TraiterVirementServlet extends HttpServlet
	{
	private static final long	serialVersionUID	= 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TraiterVirementServlet()
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
		HttpSession a_sess= request.getSession(false);
		if(a_sess != null)
			{
			int cptSourceId = Integer.parseInt(request.getParameter("compteSource"));
			int cptDestinationId = Integer.parseInt(request.getParameter("compteDestination"));
			float montant = Float.parseFloat(request.getParameter("montant"));
			
			Client client = (Client) request.getSession().getAttribute("client");
			Compte cptSource = client.getCompte(cptSourceId);
			Compte cptDest = client.getCompte(cptDestinationId);
			
			BanqueService banqueService = BanqueServiceManager.getBanqueService();
			Virement virement= null;
			try
				{
				virement= banqueService.effectuerVirement(cptSource, cptDest, montant);
				}
			catch(BankonetException e)
				{
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			
			request.setAttribute("virement", virement);
			this.getServletContext()
					.getRequestDispatcher("/pagePrincipale")
					.forward(request, response);
			}
		else
			{
			this.getServletContext().getRequestDispatcher("/login.jsp")
					.forward(request, response);
			}
		}

	}
