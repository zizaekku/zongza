package zizeaku.zongza.repository;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;

public interface UserRepository {
    User update(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
}
