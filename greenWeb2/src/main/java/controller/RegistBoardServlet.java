package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/regist_board.do")
public class RegistBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//글작성하기 링크를 누르면 로그인 한 사용자이면 게시글 등록 폼으로 이동하고,
	//로그인 되어 있지 않은 사용자라면 로그인 페이지로 이동한다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RegistBoardServlet......get....");
		HttpSession session = request.getSession();
		
		if(session.getAttribute("id") != null) {
			request.getRequestDispatcher("reg_board_form.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("loginForm.jsp").forward(request, response);
		}
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RegistBoardServlet.....post.....");
		
		
		
		
		//request.getRequestDispatcher("boardList.do").forward(request, response);
		response.sendRedirect("boardList.do");
	}

}
