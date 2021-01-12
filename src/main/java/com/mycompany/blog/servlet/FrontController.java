package com.mycompany.blog.servlet;

import com.mycompany.blog.action.Action;
import com.mycompany.blog.action.ActionFactory;
import com.mycompany.blog.action.ActionResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name = "Controller", urlPatterns = "/do/*")
public class FrontController extends HttpServlet {
    private ActionFactory factory;
    @Override
    public void init() throws ServletException {
        factory = new ActionFactory();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = factory.getAction(req);
        ActionResult res = action.execute(req, resp);
        doForwardOrRedirect(res, req, resp);
    }

    private void doForwardOrRedirect(ActionResult result, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (result.isRedirect()) {
            resp.sendRedirect(result.getView());
        } else {
            String path = "/WEB-INF/jsp/" + result.getView() + ".jsp";
            req.getRequestDispatcher(path).forward(req, resp);
        }
    }
}
