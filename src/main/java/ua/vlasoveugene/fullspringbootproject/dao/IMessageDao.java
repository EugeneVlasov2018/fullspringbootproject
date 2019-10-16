package ua.vlasoveugene.fullspringbootproject.dao;

import ua.vlasoveugene.fullspringbootproject.entity.Message;

import java.util.List;

public interface IMessageDao {
    List<Message> getAllMessages();

    void saveMessage(Message message);

    List<Message> getAllMessagesByTag(String tag);
}
