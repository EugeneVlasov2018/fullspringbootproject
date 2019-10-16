package ua.vlasoveugene.fullspringbootproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.vlasoveugene.fullspringbootproject.entity.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    List<Message> findAllByTag(String tag);
}
