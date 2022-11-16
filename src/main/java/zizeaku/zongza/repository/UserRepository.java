package zizeaku.zongza.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import zizeaku.zongza.domain.User;

@Repository
public interface UserRepository {
    void save(User user);
    User update(User user);
    List<User> findByEmail(String email);
    User findById(Long id);
    List<User> findAll();
}
