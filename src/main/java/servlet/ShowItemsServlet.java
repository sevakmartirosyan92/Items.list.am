package servlet;

import manager.ItemManager;
import model.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/items/show")
public class ShowItemsServlet extends HttpServlet {

    private final ItemManager itemManager = new ItemManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int category = Integer.parseInt(req.getParameter("category"));
        if (category == 0) {
            req.setAttribute("itemList", itemManager.getItems());
        } else {
            req.setAttribute("itemList", itemManager.getItemsByCategory(category));
        }
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }
}
