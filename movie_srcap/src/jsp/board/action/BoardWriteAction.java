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

public class BoardWriteAction implements Action
{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
				
		// ���ε� ���� ������
		int fileSize= 5*1024*1024;
		// ���ε�� ���� ������
		String uploadPath = request.getServletContext().getRealPath("/UploadFolder");

		try {
			
			// ���Ͼ��ε� 
			MultipartRequest multi = new MultipartRequest
					(request, uploadPath, fileSize, "UTF-8", new DefaultFileRenamePolicy());

			// �����̸� ��������
			String fileName = "";
			Enumeration<String> names = multi.getFileNames();
			if(names.hasMoreElements())
			{
				String name = names.nextElement();
				fileName = multi.getFilesystemName(name);
			}
			
			BoardDAO dao = BoardDAO.getInstance();
			BoardBean border = new BoardBean();
			
			border.setBoard_num(dao.getSeq()); // �������� ������ ����
			border.setBoard_id(multi.getParameter("board_id")); // ���簪
			border.setBoard_subject(multi.getParameter("board_subject"));
			border.setBoard_content(multi.getParameter("board_content"));
			border.setBoard_file(fileName);
		
			boolean result = dao.boardInsert(border);
			
			if(result){
				forward.setRedirect(true);
				forward.setNextPath("BoardListAction.bo");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�� �ۼ� ���� : " + e.getMessage());
		}
		return forward;
	}
}
