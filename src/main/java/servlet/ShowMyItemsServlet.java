package servlet;

import manager.ItemManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/myItems/show")
public class ShowMyItemsServlet extends HttpServlet {

    private final ItemManager itemManager = new ItemManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("itemList", itemManager.getItemsByUserId(user.getId()));
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
