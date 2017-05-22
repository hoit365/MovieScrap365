package jsp.board.action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.model.BoardBean;
import jsp.board.model.BoardDAO;
import jsp.common.action.Action;
import jsp.common.action.ActionForward;

public class BoardListAction implements Action
{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		int spage = 1;
		String page = request.getParameter("page");

		if(page != null && !page.equals(""))	spage = Integer.parseInt(page);
		
		String opt = request.getParameter("opt");
		String condition = request.getParameter("condition");
		
		HashMap<String, Object> listOpt = new HashMap<String, Object>();
		listOpt.put("opt", opt);
		listOpt.put("condition", condition);
		
		BoardDAO dao = BoardDAO.getInstance();
		int listCount = dao.getBoardListCount(listOpt);
		
		int maxPage = (int)(listCount/10.0 + 0.9);

		if(spage > maxPage) spage = maxPage;
		listOpt.put("start", spage*10-9);
		
		ArrayList<BoardBean> list =  dao.getBoardList(listOpt);
	
		int startPage = (int)(spage/5.0 + 0.8) * 5 - 4;
		int endPage = startPage + 4;
		if(endPage > maxPage)	endPage = maxPage;
		
		request.setAttribute("spage", spage);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);

		request.setAttribute("list", list);
		
		forward.setRedirect(false);
		forward.setNextPath("BoardListForm.bo");
		
		return forward;
	}

}
