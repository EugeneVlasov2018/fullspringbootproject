package ua.vlasoveugene.fullspringbootproject.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ua.vlasoveugene.fullspringbootproject.dao.IUserDao;
import ua.vlasoveugene.fullspringbootproject.entity.Role;
import ua.vlasoveugene.fullspringbootproject.entity.User;

import java.util.Collections;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class RegistrationService {
    private IUserDao userDao;
    private MailSender mailSender;

    public boolean saveNewUser(User innerUser) {

        User userFromDb = userDao.getCurrentUser(innerUser.getUsername());
        if(userFromDb!=null){
            return false;
        }

        innerUser.setActive(true);
        innerUser.setRoles(Collections.singleton(Role.USER));
        innerUser.setActivationCode(UUID.randomUUID().toString());
        userDao.saveNewUser(innerUser);

        if(!StringUtils.isEmpty(innerUser.getEmail())){
            String message = String.format(
                    "Hello, dear %s!\n" +
                    "Welcome to app and please visit next link:\n" +
                    "http://localhost:8080/activate/%s",
                    innerUser.getUsername(),
                    innerUser.getActivationCode()
            );

            mailSender.sendCode(innerUser.getEmail(),"Activation Code", message);
        }
        return true;
    }

    public boolean activateUser(String code) {
        User user = userDao.findByActivationCode(code);

        if(user == null) {
            return false;
        }

        user.setActivationCode(null);
        userDao.saveNewUser(user);

        return true;
    }
}
