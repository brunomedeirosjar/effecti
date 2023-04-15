package com.bids.effecti.service.impl;

import com.bids.effecti.entity.Employer;
import com.bids.effecti.entity.SendEmail;
import com.bids.effecti.model.Channel;
import com.bids.effecti.model.EmailProperties;
import com.bids.effecti.model.Item;
import com.bids.effecti.repository.EmployerRepository;
import com.bids.effecti.repository.SendEmailRepository;
import com.bids.effecti.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class SendEmailServiceImpl implements SendEmailService {

    @Autowired
    private SendEmailRepository repository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private ServletContext servletContext;


    Logger logger = Logger.getLogger(String.valueOf(SendEmailServiceImpl.class));

    public void sendConfigEmail(EmailProperties emailProperties) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("bruno.silva.medeiros.1@gmail.com");
        message.setTo(emailProperties.getToEmail());
        message.setText(emailProperties.getBody());
        message.setSubject(emailProperties.getSubject());
        javaMailSender.send(message);

    }

    @Override
    public String sendEmail(Channel channel) throws Exception {
        String isSend = null;
        List<Employer> employerList = employerRepository.findAll();
        List<Item> items = channel.getItems();
        items = items.stream().filter(i -> i.getTitle().contains("Divulgado Aguardando Abertura") && !i.getTitle().contains("Inexigibilidade ")).collect(Collectors.toList());
        for (Employer employer : employerList) {
            for (Item item : items) {
                SendEmail sendEmail = new SendEmail();
                sendEmail = repository.findByEmployerIdAndGuid(employer.getId(), new Long(item.getGuid()));
                if (sendEmail == null) {
                    montageEmail(item, employer);
                    sendConfigEmail(new EmailProperties(new String[]{employer.getEmail()}, item.getTitle(), item.getContent_html()));
                    save(new SendEmail(null, true, false, new Long(item.getGuid()), employer, item.getUrl()));
                    isSend = "Email enviado com sucesso.";
                }
            }
        }
        if (isSend == null){
            isSend = "Não foi localizado empragador/licitações disponiveis pra envio.";
        }
        return isSend;
    }

    private static void montageEmail(Item item, Employer employer) {
        String body = "Olá " + employer.getCommercialName()
                + "! Tudo bem? \n\r"
                + "Passando apenas para apresentar a licitação: "
                + item.getGuid() + ".  Segue abaixo algumas informações: \n\r" + item.getContent_html()
                + "\n\r" + "Para maiores informações: "
                + "http://localhost:8090/sendEmail/return/" +employer.getId()+"/"+item.getGuid()+ "\n\r"
                + "Atenciosamente \n"
                + "Effecti\n\r";
        item.setContent_html(body);
    }

    @Override
    public List<SendEmail> getAll() {
        return repository.findAll();
    }

    public SendEmail save(SendEmail sendEmail) {
        return repository.save(sendEmail);
    }

    @Override
    public SendEmail findSendEmail(Long idSendEmail) {
        return repository.findById(idSendEmail).get();
    }

    @Override
    public String findReturn(Long idEmployer, Long guild) throws Exception {
        SendEmail sendEmail = new SendEmail();
        sendEmail = repository.findByEmployerIdAndGuid(idEmployer, guild);
        if (sendEmail == null) {
            throw new Exception("Not registered employer or there are no bids registered");
        }
        sendEmail.setIsReturn(true);
        sendEmail = save(sendEmail);
        return sendEmail.getUrl();
    }


}
