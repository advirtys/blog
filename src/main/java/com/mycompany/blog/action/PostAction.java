package com.mycompany.blog.action;

import com.mycompany.blog.dao.PostDAO;
import com.mycompany.blog.entity.Post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostAction implements Action {
    ActionResult postPage;
    public PostAction(String page) {
        postPage = new ActionResult(page);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        PostDAO postDAO = new PostDAO();
        Post post = postDAO.getById(id);
        req.setAttribute("post", post);
        return postPage;
    }
}
