package ru.job4j.forum.model;

import java.text.SimpleDateFormat;
import java.util.*;

public class Topic {
    private int id;
    private String name;
    private String description;
    private Calendar created;
    private List<Post> posts;

    public static Topic of(int id, String name, String description) {
        Topic topic = new Topic();
        topic.id = id;
        topic.name = name;
        topic.description = description;
        topic.created = Calendar.getInstance();
        topic.posts = new ArrayList<>();
        return topic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getCreated() {
        return created;
    }

    public String getDate() {
        return new SimpleDateFormat("dd-MM-yyyy").format(created.getTime());
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Post> getPosts() {
        return posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Topic topic = (Topic) o;
        return id == topic.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}