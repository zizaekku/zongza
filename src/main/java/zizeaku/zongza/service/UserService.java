package zizeaku.zongza.service;

import org.springframework.stereotype.Service;

import java.util.List;

import zizeaku.zongza.domain.User;
import zizeaku.zongza.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String save(User user) {
        userRepository.save(user);
        return "생성 성공";
    }

    public User login(User user) {
        isUserExists(user);
        isPasswordCorr(user);
        System.out.println(user);
        return user;
    }

    
    /** 유저 이메일로 존재하는지 확인하는 함수
     * @param user
     */
    private void isUserExists(User user) {
        String email = user.getEmail();
        List<User> findUsers = userRepository.findByEmail(email);
        if(findUsers.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }
    }

    private void isPasswordCorr(User user) {
        Long id = user.getId();
        System.out.println("id");
        System.out.println(id);
        User result = userRepository.findById(id);
        System.out.println(user.getPassword());
        System.out.println(result.getPassword());
        System.out.println(user.getPassword() == result.getPassword());
        if(user.getPassword() != result.getPassword()) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
    }
}
