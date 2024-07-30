package narsha.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import narsha.enums.BoardTag;
//import narsha.enums.UserTag;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "boards")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    // 제목
    @Setter
    private String title = "";

    // 내용
    @Setter
    private String contents = "";

    // 생성 시간
    private final LocalDateTime createDt = LocalDateTime.now();

    // 수정 시간
    private LocalDateTime editDt = LocalDateTime.now();

    // UserTag enum 값 추가
    @Enumerated(EnumType.STRING)
    private BoardTag boardTag;

    // 엔티티가 수정될 때 자동으로 갱신
    @PreUpdate
    protected void onUpdate() {
        this.editDt = LocalDateTime.now();
    }

    public Board(){}
    public Board(User user, String title, String contents, BoardTag boardTag){
        this.user = user;
        this.title = title;
        this.contents = contents;
        this.boardTag = boardTag;
    }

    // Board 와 Comment 의 관계 설정
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Comment> comments = new ArrayList<>();
}
