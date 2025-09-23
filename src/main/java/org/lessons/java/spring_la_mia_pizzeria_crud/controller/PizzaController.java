package org.lessons.java.spring_la_mia_pizzeria_crud.controller;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<Pizza> pizze = pizzaRepository.findAll();
        model.addAttribute("pizze", pizze);
        return "index";
    }

    @GetMapping("/pizza/{id}")
    public String showPizza(@PathVariable("id") Integer id, Model model) {
        Pizza pizza = pizzaRepository.findById(id.longValue()).orElse(null);
        model.addAttribute("pizza", pizza);
        return "show";
    }

    // Ricerca per nome o descrizione
    @GetMapping("/search")
    public String searchByNameOrDescrizione(@RequestParam("keyword") String keyword, Model model) {
        List<Pizza> pizze = pizzaRepository.findByNomeContainingIgnoreCaseOrDescrizioneContainingIgnoreCase(keyword,
                keyword);
        model.addAttribute("pizze", pizze);
        return "index";
    }
}
