import models.Post;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;


public class App {


    public static void main(String[] args) { //typge “psvm + tab” to autocreate this :)
        staticFileLocation ("/public");
        get("/posts/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "post-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process new post form

        post("/posts/new", (request, response) ->{
            Map<String, Object> model = new HashMap<>();
            String content = request.queryParams("content");
            Post newPost = new Post(content);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show all posts

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Post> posts = Post.getAll();
            model.put("posts", posts);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


        //get: show an individual post

        get("/posts/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfPostToFind = Integer.parseInt(request.params("id")); //pull id - must match route segment
            Post foundPost = Post.findById(idOfPostToFind);    //use it to find post
            model.put("post", foundPost);   //add it to model for template to display
            return new ModelAndView(model, "post-detail.hbs");  //individual post page.
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a post

        get("/posts/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfPostToEdit = Integer.parseInt(request.params("id"));
            Post editPost = Post.findById(idOfPostToEdit);
            model.put("editPost", editPost);
            return new ModelAndView(model, "post-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to update a post

        post("/posts/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String newContent = request.queryParams("content");
            int idOfPostToEdit = Integer.parseInt(request.params("id"));
            Post editPost = Post.findById(idOfPostToEdit);
            editPost.update(newContent); //don’t forget me
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete an individual post
    }
}