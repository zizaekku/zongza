package zizeaku.zongza.repository;

import java.util.Optional;
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
    
    // 회원가입
    @Override
    public void save(User user) {
        em.persist(user);        
    }

    // 비밀번호 변경
    @Override
    public void updatePassword(Long id, String password) {
        em.createQuery("update User set password = :password where id = :id")
            .setParameter("password", password)
            .setParameter("id", id)
            .executeUpdate();
        em.clear();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> result = em.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select * from User u", User.class)
            .getResultList();
    }

}
