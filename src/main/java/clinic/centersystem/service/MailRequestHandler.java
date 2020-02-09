package clinic.centersystem.service;

import clinic.centersystem.dto.request.MailRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class MailRequestHandler {

    @Autowired
    private EmailService emailService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(MailRequestDTO mailRequestDTO) {
        emailService.sendMailTo(mailRequestDTO.getReciever(), mailRequestDTO.getSubject(), mailRequestDTO.getAnswer());
    }
}
