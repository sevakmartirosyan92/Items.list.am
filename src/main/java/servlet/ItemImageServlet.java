package servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(urlPatterns = "/itemImage")
public class ItemImageServlet extends HttpServlet {

    private static final String IMAGE_PATH = "C:\\Users\\37493\\Java 2022\\Items.list.am\\projectImages\\";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemImage = req.getParameter("itemImage");
        String filePath = IMAGE_PATH + itemImage;
        File fileName = new File(filePath);
        if (fileName.exists()) {
            try (FileInputStream inputStream = new FileInputStream(fileName)) {
                resp.setContentType("image/jpeg");
                resp.setContentLength((int) fileName.length());

                ServletOutputStream outputStream = resp.getOutputStream();

                int reader;
                byte[] buffer = new byte[4096];
                while ((reader = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, reader);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}