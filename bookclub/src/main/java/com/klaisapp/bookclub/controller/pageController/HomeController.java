package com.klaisapp.bookclub.controller.pageController;

import com.klaisapp.bookclub.service.controller.page.HomeControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final HomeControllerService homeControllerService;
    @Autowired
    public HomeController(HomeControllerService homeControllerService) {
        this.homeControllerService = homeControllerService;
    }

    /**
     * Handles the GET request for the homepage
     *
     * @param model The Spring MVC model for rendering data
     * @return homepage template
     */
    @GetMapping("/")
    public String home(Model model) {
        addHeaderAttributes(model);
        addCardAttributes(model);
        return "index.html";
    }

    /**
     * Adds attributes related to the header section of the homepage to the model
     *
     * @param model The Spring MVC model for rendering data
     */
    private void addHeaderAttributes(Model model) {
        homeControllerService.addFormattedDate(model);
        homeControllerService.addDayOfWeek(model);
        homeControllerService.addUserData(model);
    }

    /**
     * Adds attributes related to the various cards on the homepage to the model.
     * Organized: by the order of cards
     *
     * @param model The Spring MVC model for rendering data
     */
    private void addCardAttributes(Model model) {
        homeControllerService.addPopularBook(model);
        homeControllerService.addPopularGenre(model);
        homeControllerService.addRecentBook(model);
        homeControllerService.addLiteraryQuote(model);
        homeControllerService.addFeelingCard(model);
        homeControllerService.addGreetingCard(model);
    }
}
