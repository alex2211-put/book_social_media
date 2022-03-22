package ru.iliya.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.iliya.FavouritesService;

@Controller
public class FavoritesController {
    @Autowired
    FavouritesService favouritesService;

    @GetMapping("/favorites")
    public String showFavorites(@RequestParam(name = "user") int userId,
                                Model model) {
        model.addAttribute("favorites", favouritesService.findFavouritesByUserID(userId));
        return "favorites";
    }
}
