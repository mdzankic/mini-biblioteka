package ba.sum.fpmoz.minibiblioteka.book;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookRepository repo;
    public BookController(BookRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Book> all() { return repo.findAll(); }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Book create(@RequestBody Book b) { return repo.save(b); }

    @GetMapping("/{id}")
    public ResponseEntity<Book> one(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book nb) {
        return repo.findById(id).map(b -> {
            b.setTitle(nb.getTitle());
            b.setAuthor(nb.getAuthor());
            b.setIsbn(nb.getIsbn());
            b.setYearPublished(nb.getYearPublished());
            return ResponseEntity.ok(repo.save(b));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (repo.existsById(id)) { repo.deleteById(id); return ResponseEntity.noContent().build(); }
        return ResponseEntity.notFound().build();
    }
}
