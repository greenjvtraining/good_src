package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.NoticeDto;
import dto.ReplyDto;
import service.NoticeService;
import service.ReplyService;

@WebServlet("*.do")
public class FrontDemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	NoticeService noticeService = new NoticeService();
	ReplyService replyService = new ReplyService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String view = null;
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String com = uri.substring(contextPath.length());
		
		//String path = request.getContextPath()+"/css/style.css";
		//System.out.println("ContextPath : " + path);
		//request.setAttribute("cssPath", path);
		
		if(com.equals("/noticeList.do")) {
			System.out.println("NoticeListServlet........");
			
			List<NoticeDto> noticeList = noticeService.getNoticeList();
			
			request.setAttribute("noticeList", noticeList);
			view = "/noticeList.jsp";
			
		}else if(com.equals("/detail_notice.do")) {
			System.out.println("NoticeDetailServlet........");
			String snno = request.getParameter("nno");
			int nno = Integer.parseInt(snno);
			
			NoticeDto notice = noticeService.getNotice(nno);
			
			request.setAttribute("notice", notice);
			view = "/detail_notice.jsp";
			
		}else if(com.equals("/reg_reply.do")) {
			System.out.println("댓들 등록....");
			String snno = request.getParameter("nno");
			int nno = Integer.parseInt(snno);
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			
			ReplyDto reply = new ReplyDto(nno, content, writer, null);
			System.out.println("reply: " + reply);
			replyService.saveReply(reply);
			
			List<ReplyDto> replyList = replyService.getReplyList(nno);
			
			Gson gson = new Gson();
			String json_str = gson.toJson(replyList);
			System.out.println("replyList : " + json_str);
			
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(json_str);
			
		}
	
		// view에 담긴 문자열에 따라 포워딩 또는 리다이렉팅
        if (view.startsWith("redirect:")) {
            response.sendRedirect(view.substring(9));
        } else {
            request.getRequestDispatcher(view).forward(request, response);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
