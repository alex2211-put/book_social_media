package ru.iliya.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.iliya.services.RecommendationsService;

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

    @RequestMapping("/recs/{user}")
    public String showFavoritesParams(@PathVariable(name = "user") int userId,
                                Model model) {
        model.addAttribute("reccoms",
                recommendationsService.findRecommendationsByUserID(userId));
        return "recommendations";
    }
}
