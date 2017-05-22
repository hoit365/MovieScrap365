package jsp.board.comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.comment.model.CommentDAO;
import jsp.common.action.Action;
import jsp.common.action.ActionForward;

public class CommentDeleteAction implements Action
{
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		int comment_num = Integer.parseInt(request.getParameter("comment_num"));
		
		CommentDAO dao = CommentDAO.getInstance();
		boolean result = dao.deleteComment(comment_num);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		if(result) out.println("1");
		
		out.close();
		return null;
	}
}
