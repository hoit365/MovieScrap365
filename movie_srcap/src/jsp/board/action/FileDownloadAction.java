package jsp.board.action;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.action.Action;
import jsp.common.action.ActionForward;

public class FileDownloadAction implements Action
{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String fileName = request.getParameter("file_name");

		String folder = request.getServletContext().getRealPath("UploadFolder");
		String filePath = folder + "/" + fileName;

		try {
			File file = new File(filePath);
			byte b[] = new byte[(int) file.length()];
			
			response.reset();
			response.setContentType("application/octet-stream");
			
			String encoding = new String(fileName.getBytes("UTF-8"),"8859_1");
			
			response.setHeader("Content-Disposition", "attachment;filename="+ encoding);
			response.setHeader("Content-Length", String.valueOf(file.length()));
			
			if (file.isFile()) 
			{
				FileInputStream fileInputStream = new FileInputStream(file);
				ServletOutputStream servletOutputStream = response.getOutputStream();
				
				int readNum = 0;
				while ( (readNum = fileInputStream.read(b)) != -1) {
					servletOutputStream.write(b, 0, readNum);
				}
				
				servletOutputStream.close();
				fileInputStream.close();
			}
			
		} catch (Exception e) {
			System.out.println("Download Exception : " + e.getMessage());
		}

		return null;
	}

}
