package ru.job4j.forum.model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name = "topic")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private Calendar created = Calendar.getInstance();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topic")
    private List<Post> posts = new ArrayList<>();

    public static Topic of(int id, String name, String description) {
        Topic topic = new Topic();
        topic.id = id;
        topic.name = name;
        topic.description = description;
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