package zizeaku.zongza.repository;

import zizeaku.zongza.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);

    // @Modifying
    // @Query("UPDATE users SET password = :password where id = :id")
    // void updatePassword(Long id, String password);

}
