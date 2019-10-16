package ua.vlasoveugene.fullspringbootproject.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.vlasoveugene.fullspringbootproject.dao.IUserDao;
import ua.vlasoveugene.fullspringbootproject.entity.User;

@Service
@AllArgsConstructor
@Transactional
public class UserService implements UserDetailsService {
    private IUserDao dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return dao.getCurrentUser(username);
    }

    public Object getAllUsers() {
        return dao.getAllUsers();
    }

    public void saveUser(User user) {
        dao.saveNewUser(user);
    }
}
