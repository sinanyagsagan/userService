package io.synansoft.userservice.service.impl;

import io.synansoft.userservice.model.Confirmation;
import io.synansoft.userservice.model.User;
import io.synansoft.userservice.repository.ConfirmationRepository;
import io.synansoft.userservice.repository.UserRepository;
import io.synansoft.userservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements io.synansoft.userservice.service.UserService {

    private final UserRepository userRepository;
    private final ConfirmationRepository confirmationRepository;
    private final EmailService emailService;
    @Override
    public User saveUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())){ throw new RuntimeException("Email already exists");}
        user.setEnabled(false);
        userRepository.save(user);

        Confirmation confirmation = new Confirmation(user);
        confirmationRepository.save(confirmation);

       /* TODO Send email to user with token */
      //  emailService.sendSimpleMailMessage(user.getName(), user.getEmail(), confirmation.getToken());
        emailService.sendHtmlEmail(user.getName(), user.getEmail(), confirmation.getToken());
        return user;
    }

    @Override
    public Boolean verifyToken(String token) {
        Confirmation confirmation = confirmationRepository.findByToken(token);
        User user = userRepository.findByEmailIgnoreCase(confirmation.getUser().getEmail());
        user.setEnabled(true);
        userRepository.save(user);
        return Boolean.TRUE;
    }
}
