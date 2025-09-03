package ba.sum.fpmoz.minibiblioteka.user;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name; // ROLE_ADMIN, ROLE_USER

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
