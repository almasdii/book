package SpringMVC.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Setter
    @Column(name = "name",nullable = false)
    private String name;

    @Setter
    @Column(name = "born",nullable = false)
    private LocalDate born;

    @OneToMany(mappedBy = "person",fetch = FetchType.LAZY)
    private List<Book> books;

    public Person(String name,LocalDate born){
        this.name = name;
        this.born = born;
    }
}
