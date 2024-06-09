package ua.vholovetskyi.bookshop.email.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailMessageService {

    private final EmailSender emailSender;
    private final EmailRepository emailRepository;

    public List<MessageEntity> findAllByStatus(MessageStatus status) {
        return emailRepo.findAllByStatus(status);
    }

}
