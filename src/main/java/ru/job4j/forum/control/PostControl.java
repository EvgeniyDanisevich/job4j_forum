package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.Service;

@Controller
public class PostControl {
    private final Service service;

    public PostControl(Service service) {
        this.service = service;
    }

    @GetMapping("post/edit")
    public String create(@RequestParam("topicId") int topicId,
                         @RequestParam("postId") int postId,
                         Model model) {
        model.addAttribute("topic", service.getTopicById(topicId));
        model.addAttribute("post", service.getPostById(postId));
        return "post/edit";
    }

    @PostMapping("post/save")
    public String save(@ModelAttribute Post post) {
        service.save(post);
        return "redirect:/topic?id=" + post.getTopic().getId();
    }
}