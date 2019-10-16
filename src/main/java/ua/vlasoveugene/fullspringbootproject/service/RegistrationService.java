package ua.vlasoveugene.fullspringbootproject.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.vlasoveugene.fullspringbootproject.dao.IUserDao;
import ua.vlasoveugene.fullspringbootproject.entity.User;

@Service
@Transactional
@AllArgsConstructor
public class RegistrationService {
    private IUserDao userDao;

    @Transactional(readOnly = true)
    public User getCurrentUser(String login) {
        return userDao.getCurrentUser(login);
    }

    public void saveNewUser(User innerUser) {
        userDao.saveNewUser(innerUser);
    }
}
