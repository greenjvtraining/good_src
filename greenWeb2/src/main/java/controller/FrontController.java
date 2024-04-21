package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.BoardDto;
import dto.MemberDto;
import service.BoardService;
import service.MemberService;

@WebServlet("/")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService boardService = new BoardService();
		
		String view = null;
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String com = uri.substring(contextPath.length());
		
		String path = request.getContextPath()+"/css/style.css";
		//System.out.println("ContextPath : " + path);
		request.setAttribute("cssPath", path);
		
		if(com.equals("/boardList.do")) {
			System.out.println("BoardListServlet........");
			
			List<BoardDto> boardList = boardService.getList();
			
			request.setAttribute("boardList", boardList);
			request.getRequestDispatcher("/boardList.jsp").forward(request, response);
			
		}else if(com.equals("/login.do")) {
			System.out.println("LoginServlet..........");
			
			boolean result = true;
			
			if(result) {
				HttpSession session = request.getSession();
				session.setAttribute("id", "user02");
				
				request.getRequestDispatcher("success.jsp").forward(request, response);
				//response.sendRedirect("success.jsp");
			}else {
				request.getRequestDispatcher("fail.jsp").forward(request, response);
				//response.sendRedirect("fail.jsp");
			}
			
		}else if(com.equals("/logout.do")) {
			System.out.println("LogoutServlet........");
			
			HttpSession session = request.getSession();
			session.invalidate();
			
			response.sendRedirect("boardList.do");
			
		}else if(com.equals("/regist_board_form.do")) {
			System.out.println("RegistBoardServlet......get....");
			HttpSession session = request.getSession();

			if(session.getAttribute("id") != null) {
				request.getRequestDispatcher("reg_board_form.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("loginForm.jsp").forward(request, response);
			}
			
			//response.sendRedirect("reg_board_form.jsp");
		}else if(com.equals("/regist_board.do")) {
			//게시물 DB등록 코드
			System.out.println("RegistBoardServlet...post.....");
			
			String savePath = "images";
			int fileSize = 5*1024*1024;
			String encType = "utf-8";
			
			ServletContext ctx = getServletContext();
			String uploadPath = ctx.getRealPath(savePath);
			System.out.println("서버상 실제 디렉토리 : " + uploadPath);
			
			MultipartRequest mr = new MultipartRequest(
					request,
					uploadPath,
					fileSize,
					encType,
					new DefaultFileRenamePolicy()
					);
					
			String title = mr.getParameter("title");
			String content = mr.getParameter("content");
			String writer = mr.getParameter("writer");
			String fileName = mr.getFilesystemName("uploadFile");
			if(fileName == null) {
				fileName = "angry-bull.png";
			}
			BoardDto board = new BoardDto(title, content, writer, fileName);
			boardService.saveBoard(board);
			
			//목록으로 이동
			response.sendRedirect("boardList.do");
			
		}else if(com.equals("/regist_member_form.do")) {
			response.sendRedirect("reg_member_form.jsp");
			
		}else if(com.equals("/regist_member.do")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String birth = request.getParameter("birth");
			String phone = request.getParameter("phone");
			String addr = request.getParameter("addr");
			String role = "junior";
			
			MemberDto member = new MemberDto(id, pw, name, birth, phone, addr, role);
			MemberService memberService = new MemberService();
			memberService.saveMember(member);
			
			response.sendRedirect("boardList.do");
			
		}else if(com.equals("/detail_board.do")) {
			String sbno = request.getParameter("bno");
			int bno = Integer.parseInt(sbno);
			
			BoardDto board = boardService.getBoard(bno);
			request.setAttribute("board", board);
			
			request.getRequestDispatcher("detail_board.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		System.out.println("post............");
		doGet(request, response);
	}

}
