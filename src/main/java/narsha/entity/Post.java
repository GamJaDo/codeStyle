package narsha.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import narsha.dto.PostResponse;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class Post<U extends User, R extends PostResponse<U>> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private String postImageUrl;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private U author;

    public Post() {}

    public Post(String title, String content, U author) {
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.author = author;
    }

    public abstract R toDto();
}
