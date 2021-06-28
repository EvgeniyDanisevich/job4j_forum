package ru.job4j.forum.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Topic;

public interface TopicRepository  extends CrudRepository<Topic, Integer> {
    @Override
    @Query("SELECT t FROM Topic t ORDER BY t.id")
    Iterable<Topic> findAll();
}