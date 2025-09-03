package ba.sum.fpmoz.minibiblioteka.util;

import ba.sum.fpmoz.minibiblioteka.book.Book;
import ba.sum.fpmoz.minibiblioteka.book.BookRepository;
import ba.sum.fpmoz.minibiblioteka.user.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner init(RoleRepository roles, UserRepository users, PasswordEncoder pe, BookRepository books) {
        return args -> {
            Role admin = roles.findByName("ROLE_ADMIN").orElseGet(() -> {
                Role r = new Role(); r.setName("ROLE_ADMIN"); return roles.save(r);
            });
            Role user = roles.findByName("ROLE_USER").orElseGet(() -> {
                Role r = new Role(); r.setName("ROLE_USER"); return roles.save(r);
            });

            users.findByUsername("admin").orElseGet(() -> {
                AppUser u = new AppUser(); u.setUsername("admin"); u.setPassword(pe.encode("admin")); u.setEnabled(true);
                u.getRoles().add(admin); return users.save(u);
            });

            if (books.count() == 0) {
                Book b = new Book(); b.setTitle("Na Drini ćuprija"); b.setAuthor("Ivo Andrić"); b.setIsbn("9789531100451"); b.setYearPublished(1945);
                books.save(b);
            }
        };
    }
}
