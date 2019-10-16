package ua.vlasoveugene.fullspringbootproject.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.vlasoveugene.fullspringbootproject.dao.IMessageDao;
import ua.vlasoveugene.fullspringbootproject.entity.Message;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class GreetingService {
    @NonNull
    private IMessageDao messageDao;

    @Transactional(readOnly = true)
    public List<Message> getAllMessages(){
        return messageDao.getAllMessages();
    }

    public void saveMessage(Message message) {
        messageDao.saveMessage(message);
    }

    public List<Message> getAllMessagesByTag(String tag) {
        return messageDao.getAllMessagesByTag(tag);
    }
}
