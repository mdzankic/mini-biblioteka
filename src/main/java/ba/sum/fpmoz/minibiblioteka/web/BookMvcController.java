package ba.sum.fpmoz.minibiblioteka.web;

import ba.sum.fpmoz.minibiblioteka.book.Book;
import ba.sum.fpmoz.minibiblioteka.book.BookRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookMvcController {

    private final BookRepository bookRepository;

    public BookMvcController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public String list(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "books";
    }

    @GetMapping("/books/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String newForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("formTitle", "Dodaj knjigu");
        return "book-form";
    }

    @PostMapping("/books")
    @PreAuthorize("hasRole('ADMIN')")
    public String create(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/books/{id}/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public String editForm(@PathVariable Long id, Model model) {
        Book b = bookRepository.findById(id).orElseThrow();
        model.addAttribute("book", b);
        model.addAttribute("formTitle", "Uredi knjigu");
        return "book-form";
    }

    @PostMapping("/books/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String update(@PathVariable Long id, @ModelAttribute Book updated) {
        Book b = bookRepository.findById(id).orElseThrow();
        b.setTitle(updated.getTitle());
        b.setAuthor(updated.getAuthor());
        b.setIsbn(updated.getIsbn());
        b.setYearPublished(updated.getYearPublished());
        bookRepository.save(b);
        return "redirect:/books";
    }

    @PostMapping("/books/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }
}
