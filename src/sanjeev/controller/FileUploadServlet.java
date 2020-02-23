package sanjeev.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.sanjeev.data.FileUploadDAO;

/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/FileUploadServlet")
@MultipartConfig(maxFileSize = 16177215) // upload file's size up to 16MB
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FileUploadDAO fileUploadDao;

	@Override
	public void init() {
		fileUploadDao = new FileUploadDAO();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, IOException {
		
		// gets values of text fields
				String firstName = request.getParameter("firstName");
				String lastName = request.getParameter("lastName");
				System.out.println(firstName + "  "+ lastName);
				InputStream inputStream = null; // input stream of the upload file

				String message = null;
				// obtains the upload file part in this multipart request
				Part filePart = request.getPart("photo");
				
				if (filePart != null) {
					// prints out some information for debugging
					System.out.println(filePart.getName());
					System.out.println(filePart.getSize());
					System.out.println(filePart.getContentType());

					// obtains input stream of the upload file
					inputStream = filePart.getInputStream();
				}

				// sends the statement to the database server
				int row = 0;
				try {
					row = fileUploadDao.addUser(firstName, lastName, inputStream);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (row > 0) {
					message = "File uploaded and saved into database";
					System.out.println(message);
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
