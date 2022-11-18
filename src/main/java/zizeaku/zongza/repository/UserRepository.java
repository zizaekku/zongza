package zizeaku.zongza.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import zizeaku.zongza.domain.User;

@Repository
public interface UserRepository {
    void save(User user);
    void updatePassword(Long id, String password);
    Optional<User> findByEmail(String email);
    User findById(Long id);
    List<User> findAll();
}
