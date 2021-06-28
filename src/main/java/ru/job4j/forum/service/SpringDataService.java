package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.Topic;
import ru.job4j.forum.repository.PostRepository;
import ru.job4j.forum.repository.TopicRepository;

import java.util.List;

@Service
public class SpringDataService implements ru.job4j.forum.service.Service {
    private final TopicRepository topicRepository;
    private final PostRepository postRepository;

    public SpringDataService(TopicRepository topicRepository, PostRepository postRepository) {
        this.topicRepository = topicRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void save(Topic topic) {
        topicRepository.save(topic);
    }

    @Override
    public List<Topic> getTopics() {
        return (List<Topic>) topicRepository.findAll();
    }

    @Override
    public Topic getTopicById(int id) {
        return topicRepository.findById(id).orElse(Topic.of(0, "", ""));
    }

    @Override
    public void save(Post post) {
        Topic topic = getTopicById(post.getTopic().getId());
        Post temp = getPostById(post.getId());
        temp.setTopic(topic);
        temp.setText(post.getText());
        postRepository.save(temp);
    }

    @Override
    public Post getPostById(int id) {
        return postRepository.findById(id).orElse(Post.of(0, ""));
    }
}