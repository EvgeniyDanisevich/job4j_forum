package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.Topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MemStore implements Store {
    private static final AtomicInteger TOPIC_ID = new AtomicInteger(1);
    private static final AtomicInteger POST_ID = new AtomicInteger(3);

    private final Map<Integer, Topic> topics = new ConcurrentHashMap<>();
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    {
        Topic topic = Topic.of(1, "Тема 1",
                "Описание темы 1");
        Post post1 = Post.of(1, "Пост 1");
        Post post2 = Post.of(2, "Пост 2");
        Post post3 = Post.of(3, "Пост 3");
        topic.setPosts(new ArrayList<>(Arrays.asList(post1, post2, post3)));
        posts.put(post1.getId(), post1);
        posts.put(post2.getId(), post2);
        posts.put(post3.getId(), post3);
        topics.put(topic.getId(), topic);
    }

    @Override
    public void save(Topic topic) {
        if (topic.getId() == 0) {
            topic = Topic.of(TOPIC_ID.incrementAndGet(), topic.getName(), topic.getDescription());
            topics.put(topic.getId(), topic);
        } else {
            Topic updated = topics.get(topic.getId());
            updated.setName(topic.getName());
            updated.setDescription(topic.getDescription());
        }
    }

    @Override
    public List<Topic> getTopics() {
        return new ArrayList<>(topics.values());
    }

    @Override
    public Topic getTopicById(int id) {
        Topic found = topics.get(id);
        return found != null ? found : Topic.of(0, "", "");
    }

    @Override
    public void save(Post post) {
        if (post.getId() == 0) {
            Topic topic = topics.get(post.getTopic().getId());
            Post saved = Post.of(POST_ID.incrementAndGet(), post.getText());
            saved.setTopic(topic);
            topic.getPosts().add(saved);
            posts.put(saved.getId(), saved);
        } else {
            posts.get(post.getId()).setText(post.getText());
        }
    }

    @Override
    public Post getPostById(int id) {
        Post found = posts.get(id);
        return found != null ? found : Post.of(0, "");
    }
}