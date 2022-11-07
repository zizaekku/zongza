package zizeaku.zongza.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.apache.catalina.User;

public class JpaUserRepository implements UserRepository {

    private final EntityManager em;
    
    public JpaUserRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public User update(User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
