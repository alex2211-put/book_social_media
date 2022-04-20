package ru.iliya.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.iliya.entities.User;
import ru.iliya.security.SecurityUserConverter;
import ru.iliya.services.FavouritesService;

@Controller
public class FavoritesController {

    @Autowired
    FavouritesService favouritesService;
    @Autowired
    SecurityUserConverter securityUserConverter;

    @GetMapping("/favorites")
    public String showFavorites(@AuthenticationPrincipal UserDetails currentUser,
                                Model model) {
        User user = securityUserConverter.getUserByDetails(currentUser);
        model.addAttribute("favorites", favouritesService.findFavouritesByUserID(user.getUserID()));
        return "favorites";
    }

    @RequestMapping("/favorites/{user}")
    public String showFavoritesParam(@PathVariable(name = "user") int userId,
                                Model model) {
        model.addAttribute("favorites", favouritesService.findFavouritesByUserID(userId));
        return "favorites";
    }
}
