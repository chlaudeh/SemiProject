package test.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.BoardDao;


@WebServlet("/board/delete")
public class DeleteServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String num=req.getParameter("num");
		BoardDao dao=new BoardDao(); 
		int n=dao.delete(num);
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/board/list");
		}else {
			req.setAttribute("code","fail");
			req.getRequestDispatcher("/board/result.jsp").forward(req, resp);
		}
	}
}










