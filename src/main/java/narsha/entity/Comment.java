package narsha.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Comment 와 Board 의 관계 설정
    @Setter
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Setter
    private String contents = "";

    public Comment( User user, Board board, String contents) {
        this.user = user;
        this.board = board;
        this.contents = contents;
    }

    // Default constructor
    public Comment() {}
}



