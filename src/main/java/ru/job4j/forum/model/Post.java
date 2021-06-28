package ru.job4j.forum.model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text;
    private Calendar created = Calendar.getInstance();
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Topic topic;

    public static Post of(int id, String text) {
        Post post = new Post();
        post.id = id;
        post.text = text;
        return post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Calendar getCreated() {
        return created;
    }

    public String getDate() {
        return new SimpleDateFormat("dd-MM-yyyy, HH:mm").format(created.getTime());
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}