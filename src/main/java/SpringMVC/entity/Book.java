package SpringMVC.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "book")
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = false,updatable = false)
    private String name;
    @Column(name = "author",updatable = false,nullable = false)
    private String author;

    @Column(name = "created_at",updatable = false,nullable = false)
    private LocalDate createdAt;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    public Book(String name,String author,LocalDate createdAt){
        this.name = name;
        this.author = author;
        this.createdAt = createdAt;
    }


}
