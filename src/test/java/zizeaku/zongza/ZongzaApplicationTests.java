package zizeaku.zongza;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class ZongzaApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("== Password Encoding ==");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("1234"));
	}

}
