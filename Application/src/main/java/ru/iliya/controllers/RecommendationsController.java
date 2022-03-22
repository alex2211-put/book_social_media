package ru.iliya.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.iliya.RecommendationsService;

@Controller
public class RecommendationsController {
    @Autowired
    RecommendationsService recommendationsService;

    @GetMapping("/recs")
    public String showFavorites(@RequestParam(name = "user") int userId,
                                Model model) {
        model.addAttribute("reccoms",
                recommendationsService.findRecommendationsByUserID(userId));
        return "recommendations";
    }
}
