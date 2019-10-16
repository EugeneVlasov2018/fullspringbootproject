package ua.vlasoveugene.fullspringbootproject.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ua.vlasoveugene.fullspringbootproject.entity.User;
import ua.vlasoveugene.fullspringbootproject.repository.UserRepo;

@Repository
@AllArgsConstructor
public class UserDaoImpl implements IUserDao {
    private UserRepo repository;

    @Override
    public User getCurrentUser(String login) {
        return repository.findByUsername(login);
    }

    @Override
    public void saveNewUser(User innerUser) {
        repository.save(innerUser);
    }

    @Override
    public Object getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User findByActivationCode(String code) {
        return repository.findByActivationCode(code);
    }
}
