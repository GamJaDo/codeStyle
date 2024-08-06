package narsha.repository;

import narsha.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PostRepository<T extends Post<?, ?>> extends JpaRepository<T, Long> {
	
}
