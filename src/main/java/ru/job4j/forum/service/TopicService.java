package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.Topic;
import ru.job4j.forum.repository.Store;

import java.util.List;

//@Service
public class TopicService implements ru.job4j.forum.service.Service {

    private final Store store;

    public TopicService(Store store) {
        this.store = store;
    }

    @Override
    public void save(Topic topic) {
        store.save(topic);
    }

    @Override
    public List<Topic> getTopics() {
        return store.getTopics();
    }

    @Override
    public Topic getTopicById(int id) {
        return store.getTopicById(id);
    }

    @Override
    public void save(Post post) {
        store.save(post);
    }

    @Override
    public Post getPostById(int id) {
        return store.getPostById(id);
    }
}