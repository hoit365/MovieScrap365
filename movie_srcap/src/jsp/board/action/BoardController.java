package jsp.board.action;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.action.Action;
import jsp.common.action.ActionForward;

public class BoardController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private HashMap<String,Action> commandMap;
	
	public void init(ServletConfig config) throws ServletException {	
        loadProperties("jsp/board/properties/BoardCommand");
    }

	private void loadProperties(String filePath) 
	{
		commandMap = new HashMap<String, Action>();
		
		ResourceBundle rb = ResourceBundle.getBundle(filePath);
		Enumeration<String> actionEnum = rb.getKeys(); // Ű���� �����´�.
		 
		while (actionEnum.hasMoreElements()) 
		{
			String command = actionEnum.nextElement(); 
			String className = rb.getString(command); 
			
			try {
				 Class actionClass = Class.forName(className); // Ŭ���� ����
				 Action actionInstance = (Action)actionClass.newInstance(); // Ŭ������ ��ü�� ����
				 
				 if(className.equals("jsp.board.action.BoardFormChangeAction")){
					 BoardFormChangeAction bf = (BoardFormChangeAction)actionInstance;
					 bf.setCommand(command);
				 }
				 
				 commandMap.put(command, actionInstance);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			doProcess(request,response);
	}  	

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			doProcess(request,response);
	}

	private void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String requestURI = request.getRequestURI();
		int cmdIdx = requestURI.lastIndexOf("/") + 1;
		String command = requestURI.substring(cmdIdx);

		ActionForward forward = null;
		Action action = null;
		
		try {
			action = commandMap.get(command);
			
			if (action == null) {
                System.out.println("��ɾ� : "+command+"�� �߸��� ����Դϴ�.");
                return;
            }

			forward = action.execute(request, response);
			
			if(forward != null){
				if (forward.isRedirect()) {
					response.sendRedirect(forward.getNextPath());
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher(forward.getNextPath());
					dispatcher.forward(request, response);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end doProcess	  	      	
}
