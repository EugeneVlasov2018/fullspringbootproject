package ua.vlasoveugene.fullspringbootproject.dao;

import ua.vlasoveugene.fullspringbootproject.entity.User;

public interface IUserDao {
    User getCurrentUser(String login);

    void saveNewUser(User innerUser);

    Object getAllUsers();
}
