package jsp.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.comment.model.CommentBean;
import jsp.board.comment.model.CommentDAO;
import jsp.board.model.BoardBean;
import jsp.board.model.BoardDAO;
import jsp.common.action.Action;
import jsp.common.action.ActionForward;

public class BoardDetailAction implements Action
{
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		// �Ķ���ͷ� �Ѿ�� �۹�ȣ�� �����´�.
		String num = request.getParameter("num");
		int boardNum = Integer.parseInt(num);
		
		String pageNum = request.getParameter("pageNum");
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardBean board = dao.getDetail(boardNum);
		boolean result = dao.updateCount(boardNum);
		
		// �Խñ� ��ȣ�� �̿��Ͽ� �ش� �ۿ� �ִ� ��� ����� �����´�.
		CommentDAO commentDAO = CommentDAO.getInstance();
		ArrayList<CommentBean> commentList = commentDAO.getCommentList(boardNum);
		
		// ����� 1���� �ִٸ� request�� commentList�� �����Ѵ�.
		if(commentList.size() > 0)	request.setAttribute("commentList", commentList);
		
		request.setAttribute("board", board);
		request.setAttribute("pageNum", pageNum);
			
		if(result){
			forward.setRedirect(false); // �ܼ��� ��ȸ�̹Ƿ�
			forward.setNextPath("BoardDetailForm.bo");
		}
		
		return forward;
	}
}
