package ua.vlasoveugene.fullspringbootproject.dao;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Repository;
import ua.vlasoveugene.fullspringbootproject.entity.Message;
import ua.vlasoveugene.fullspringbootproject.repository.MessageRepository;

import java.util.List;

@Repository
@AllArgsConstructor
public class MessageDaoImpl implements IMessageDao {
    @NonNull
    private MessageRepository repository;

    @Override
    public List<Message> getAllMessages() {
        return repository.findAll();
    }

    @Override
    public void saveMessage(Message message) {
        repository.save(message);
    }

    @Override
    public List<Message> getAllMessagesByTag(String tag) {
        return repository.findAllByTag(tag);
    }
}
