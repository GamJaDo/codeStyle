package narsha.repository;

import narsha.entity.Board;
import narsha.enums.BoardTag;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;


public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findAllByBoardTag(BoardTag boardTag, Pageable pageable);
}
