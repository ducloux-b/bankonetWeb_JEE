package com.bankonet.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DetailCompteEpargneServlet
 */
@WebServlet("/DetailCompteEpargneServlet")
public class DetailCompteEpargneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailCompteEpargneServlet() {
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
			/*
			 * On devrait aller chercher le compte à afficher ici plutôt que dans le .jsp
			 * Et le transmettre lors du forward en utilisant
			 * 	request.setAttribute("compte",compte); (avec compte, l'objet compte récupéré qui correspond)
			 */
			this.getServletContext().getRequestDispatcher("/detailCompteEpargne.jsp").forward(request, response);
			}
		else
			{
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}
	
}
