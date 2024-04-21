package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDto;
import service.BoardService;

//@WebServlet("/boardList.do")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardListServlet........");
		
		BoardService boardService = new BoardService();
		List<BoardDto> boardList = boardService.getList();
		
		String path = request.getContextPath()+"/css/style.css";
		//System.out.println("ContextPath : " + path);
		request.setAttribute("cssPath", path);
		request.setAttribute("boardList", boardList);
		
		//response.sendRedirect("boardList.jsp");
		request.getRequestDispatcher("boardList.jsp").forward(request, response);
	
	}

}
