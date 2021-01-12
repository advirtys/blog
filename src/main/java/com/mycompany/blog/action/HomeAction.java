package com.mycompany.blog.action;

import com.mycompany.blog.dao.PostDAO;
import com.mycompany.blog.entity.Post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class HomeAction implements Action {
    ActionResult homePage;

    public HomeAction(String page) {
        homePage = new ActionResult(page);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        PostDAO postDAO = new PostDAO();
        List<Post> posts = postDAO.getAll();
        req.setAttribute("posts", posts);

        return homePage;
    }
}
