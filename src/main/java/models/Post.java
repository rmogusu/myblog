package models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Post {

    private String content;
    private static ArrayList<Post> instances = new ArrayList<>();
    private boolean published; //i’m new
    private LocalDateTime createdAt; //see constructor and my method
    private int id;

    public Post (String content){
        this.content = content;
        instances.add(this);
        this.published = false; //also new
        this.createdAt = LocalDateTime.now();
        this.id = instances.size();
    }

    public String getContent() {
        return content;
    }

    public static ArrayList<Post> getAll(){
        return instances;
    }

    public static void clearAllPosts(){
        instances.clear();
    }

    public boolean getPublished(){ //new too
        return this.published;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getId() {
        return id;
    }

    public static Post findById(int id){
        return instances.get(id-1); //why minus 1? See if you can figure it out.
    }

    public void update(String content) {
        this.content = content;
    }

    public void deletePost(){
        instances.remove(id-1); //same reason
    }

}
