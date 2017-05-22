package jsp.board.action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jsp.board.model.BoardBean;
import jsp.board.model.BoardDAO;
import jsp.common.action.Action;
import jsp.common.action.ActionForward;

public class BoardUpdateAction implements Action
{
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("sssss");
		ActionForward forward = new ActionForward();
		
		String pageNum = request.getParameter("page");
		
		int fileSize= 5*1024*1024;
		String uploadPath = request.getServletContext().getRealPath("/UploadFolder");
		
		try {
			MultipartRequest multi = new MultipartRequest
					(request, uploadPath, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			
			int num = Integer.parseInt(multi.getParameter("board_num")); 
			String subject = multi.getParameter("board_subject");	
			String content = multi.getParameter("board_content");	
			String existFile = multi.getParameter("existing_file");	
			
			BoardBean border = new BoardBean();
			border.setBoard_num(num);
			border.setBoard_subject(subject);
			border.setBoard_content(content);
			
			Enumeration<String> fileNames = multi.getFileNames();
			if(fileNames.hasMoreElements())
			{
				String fileName = fileNames.nextElement();
				String updateFile = multi.getFilesystemName(fileName);
				
				if(updateFile == null)	
					border.setBoard_file(existFile);
				else	
					border.setBoard_file(updateFile);
			}
			
			BoardDAO dao = BoardDAO.getInstance();
			boolean result = dao.updateBoard(border);
			
			if(result){
				forward.setRedirect(true); 
				forward.setNextPath("BoardListAction.bo?page="+pageNum); 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�� ���� ���� : " + e.getMessage());
		}

		return forward;
	}
}
