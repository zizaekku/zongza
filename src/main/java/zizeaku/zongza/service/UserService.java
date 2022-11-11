package zizeaku.zongza.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.List;

import zizeaku.zongza.domain.User;
import zizeaku.zongza.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
    PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public String save(User user) {
        String enccodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(enccodedPassword);
        userRepository.save(user);
        return "생성 성공";
    }

    public Boolean login(User user) {
        String email = user.getEmail();
        String formPassword = user.getPassword();
        User result = isUserExists(email);
        if (isPasswordCorr(result, formPassword)) {
            System.out.println("리턴 찐");
            return true;
        } else{
            System.out.println("리턴 에러");
            return false;
        }
    }

    
    /** 유저 이메일로 존재하는지 확인하는 함수
     * @param user
     */
    private User isUserExists(String email) {
        List<User> findUsers = userRepository.findByEmail(email);
        if(findUsers.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }
        return findUsers.get(0);
    }

    private Boolean isPasswordCorr(User result, String formPassword) {
        String jjinPassword = result.getPassword();
        System.out.println(jjinPassword);
        System.out.println(formPassword);

        // 암호화 비밀번호 비교하기
        if (passwordEncoder.matches(formPassword, jjinPassword)) {
            System.out.println("진짜???");
            return true;
        } else{
            System.out.println("아니.");
            return false;
        }

    }
}
