package com.example.dentistmysql.test;

import com.example.dentistmysql.model.Users;
import com.example.dentistmysql.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class NewUserTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void addUserTest() {

        Users users = Users.builder()
                .id(10L)
                .role("ROLE_DENTIST")
                .enabled(1)
                .username("Dentysta")
                .password("123")
                .build();
        userRepository.save(users);

        Assertions.assertThat(users.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    @Rollback(value = false)
    public void updateUserTest() {
        Users users = userRepository.findByUsername("Dentysta").get();
        users.setPassword("dentist");
        Users updatedUser = userRepository.save(users);
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("dentist");
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void deleteUserTest() {
        Users users = userRepository.findByUsername("Dentysta").get();
        userRepository.delete(users);
    }
}
