package kz.iitu.springlab.web;

import kz.iitu.springlab.catalog.Book;
import kz.iitu.springlab.catalog.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookPageController {

    private final BookService service;

    public BookPageController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("books", service.findAll(null));
        return "books";
    }

    @PostMapping
    public String add(
            @RequestParam String title,
            @RequestParam String author,
            @RequestParam int year) {

        service.create(new Book(null, title, author, year));
        return "redirect:/books";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable long id) {
        service.delete(id);
        return "redirect:/books";
    }
}