package ru.diasoft.spring.homework.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.diasoft.spring.homework.entity.User;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Sql({"classpath:schema.sql"})
@DisplayName("Репозиторий пользователей приложения")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("считает количество пользователей")
    void count() {
        User user = new User();
        user.setUserName("UserName");
        user.setPassword("Password");
        user.setIsActive(true);
        user.setRoles("USER");
        userRepository.save(user);

        Long count = userRepository.count();
        assertThat(count).isNotZero();
    }

    @Test
    @DisplayName("ищет список пользователей")
    void findAll() {
        User user = new User();
        user.setUserName("UserName");
        user.setPassword("Password");
        user.setIsActive(true);
        user.setRoles("USER");
        userRepository.save(user);

        List<User> list = userRepository.findAll();
        assertThat(list).isNotEmpty();
    }

    @Test
    @DisplayName("ищет пользователя по идентификатору")
    void findById() {
        User user = new User();
        user.setUserName("UserName");
        user.setPassword("Password");
        user.setIsActive(true);
        user.setRoles("USER");
        userRepository.save(user);

        Optional<User> optional = userRepository.findById(user.getUserId());
        assertThat(optional).isPresent();
    }

    @Test
    @DisplayName("ищет пользователя по логину")
    void findByUserName() {
        User user = new User();
        user.setUserName("UserNameZZ");
        user.setPassword("Password");
        user.setIsActive(true);
        user.setRoles("USER");
        userRepository.save(user);

        Optional<User> optional = userRepository.findByUserName("UserNameZZ");
        assertThat(optional).isPresent();
    }

    @Test
    @DisplayName("создает пользователя")
    void save() {
        User user = new User();
        user.setUserName("UserNameZZ");
        user.setPassword("Password");
        user.setIsActive(true);
        user.setRoles("USER");
        userRepository.save(user);

        Optional<User> optional = userRepository.findById(user.getUserId());
        assertThat(optional).isPresent();
    }

    @Test
    @DisplayName("обновляет пользователя")
    void update() {
        User user = new User();
        user.setUserName("UserNameZZ");
        user.setPassword("Password");
        user.setIsActive(true);
        user.setRoles("USER");
        userRepository.save(user);

        Optional<User> optional = userRepository.findById(user.getUserId());
        assertThat(optional).isPresent();

        optional.ifPresent(user1 -> {
            user1.setUserName("newName");
            userRepository.save(user1);

            Optional<User> optional2 = userRepository.findById(user1.getUserId());
            assertThat(optional2).isPresent();

            optional2.ifPresent( book2 -> {
                assertThat(book2).isEqualTo(user1);
            });
        });
    }

    @Test
    @DisplayName("удаляет пользователя")
    void deleteById() {
        User user = new User();
        user.setUserName("UserNameZZ");
        user.setPassword("Password");
        user.setIsActive(true);
        user.setRoles("USER");
        userRepository.save(user);

        Optional<User> optional = userRepository.findById(user.getUserId());
        assertThat(optional).isPresent();

        userRepository.deleteById(user.getUserId());

        Optional<User> optional2 = userRepository.findById(user.getUserId());
        assertThat(optional2).isNotPresent();
    }
}