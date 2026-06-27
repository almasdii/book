package SpringMVC.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",updatable = false,nullable = false)
    private String name;

    @Column(name = "born",nullable = false,updatable = false)
    private LocalDate born;

    @OneToMany(mappedBy = "person",fetch = FetchType.LAZY)
    private List<Book> books;
}
