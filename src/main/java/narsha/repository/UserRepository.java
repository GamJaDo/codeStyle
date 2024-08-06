package narsha.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import narsha.entity.User;

@NoRepositoryBean
public interface UserRepository<T extends User> extends JpaRepository<T, Long> {

	Optional<T> findByAccount(String account);
    boolean existsByAccount(String account);
}
