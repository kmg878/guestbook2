package kr.ac.sungkyul.guestbook.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.ac.sungkyul.guestbook.dao.GuestBookDao;
import kr.ac.sungkyul.guestbook.vo.GuestBookVo;


@WebServlet("/gb")
public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding("utf-8");
		
		String actionName = request.getParameter("a");
		 if ("deleteform".equals(actionName)){
			//forwarding
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/deleteform.jsp");
			rd.forward(request, response);
			
		}if("insert".equals(actionName)){
			request.setCharacterEncoding("utf-8");
	    	String name =request.getParameter("name");
	    	String passWord = request.getParameter("pass");
	    	String content = request.getParameter("content");
	    	
	    	GuestBookVo vo = new GuestBookVo();
	    	vo.setName(name);
	    	vo.setPassWord(passWord);
	    	vo.setContent(content);
	    	
	    	GuestBookDao dao = new GuestBookDao();
	    	 dao.insert(vo);
	    	
	    	response.sendRedirect("/guestbook2/gb");
			
		}else if("delete".equals(actionName)){
			request.setCharacterEncoding("utf-8");
	    	String passWord = request.getParameter("password");
	    	String no = request.getParameter("no");
	    	
	    	GuestBookVo vo = new GuestBookVo();
	    	vo.setNo(Long.parseLong(no));
	    	vo.setPassWord(passWord);
	    	
	    	
	    	GuestBookDao dao = new GuestBookDao();
	    	dao.delete(vo);
	    	
	    	response.sendRedirect("/guestbook2/gb");
			
			
		}else{
			GuestBookDao dao = new GuestBookDao();
			List<GuestBookVo> list =dao.getList();
			//request 범위(scope)에list 객체를 저장
			request.setAttribute("list",list);
			
			
			//forwarding
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
			rd.forward(request, response);
			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
