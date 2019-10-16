package ua.vlasoveugene.fullspringbootproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ua.vlasoveugene.fullspringbootproject.entity.User;

@Component
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String login);
}
