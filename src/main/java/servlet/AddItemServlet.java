package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import manager.UserManager;
import model.Category;
import model.Item;
import model.User;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet(urlPatterns = "/item/add")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class AddItemServlet extends HttpServlet {

    private final CategoryManager categoryManager = new CategoryManager();
    private final ItemManager itemManager = new ItemManager();
    private final UserManager userManager = new UserManager();
    private static final String IMAGE_PATH = "C:\\Users\\37493\\Java 2022\\Items.list.am\\projectImages\\";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/addItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String title = req.getParameter("title");
        double price = Double.parseDouble(req.getParameter("price"));
        Category category = (Category) req.getSession().getAttribute("category");
        Part picUrl = req.getPart("picUrl");
        String fileName = null;
        if (picUrl.getSize() != 0) {
            long nanoTime = System.nanoTime();
            fileName = nanoTime + "_" + picUrl.getSubmittedFileName();
            picUrl.write(IMAGE_PATH + fileName);
        }
         itemManager.addItem(Item.builder()
                 .title(title)
                 .price(price)
                 .category(category)
                 .picUrl(fileName)
                 .user(user)
                 .build());
        req.setAttribute("itemAdded", "Item added");
        req.getRequestDispatcher("/").forward(req, resp);
    }
}
