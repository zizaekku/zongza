package zizeaku.zongza.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import zizeaku.zongza.domain.User;

@Transactional
@RequiredArgsConstructor
@Repository
public class JpaUserRepository implements UserRepository {

    private final EntityManager em;
    
    @Override
    public void save(User user) {
        em.persist(user);        
    }

    @Override
    public User update(User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> findByEmail(String email) {
        return em.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList();
    }

    @Override
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

}
