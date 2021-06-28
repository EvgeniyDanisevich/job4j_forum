package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Topic;
import ru.job4j.forum.service.Service;

@Controller
public class TopicControl {
    private final Service service;

    public TopicControl(Service service) {
        this.service = service;
    }

    @GetMapping("topic/edit")
    public String create(@RequestParam("id") int id, Model model) {
        model.addAttribute("topic", service.getTopicById(id));
        return "topic/edit";
    }

    @PostMapping("topic/save")
    public String save(@ModelAttribute Topic topic) {
        service.save(topic);
        return "redirect:/";
    }

    @GetMapping("/topic")
    public String topic(@RequestParam("id") int id, Model model) {
        Topic topic = service.getTopicById(id);
        model.addAttribute("topic", topic);
        model.addAttribute("posts", topic.getPosts());
        return "topic/topic";
    }
}