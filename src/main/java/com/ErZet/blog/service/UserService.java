package com.ErZet.blog.service;

import com.ErZet.blog.dto.LoginUserRequest;
import com.ErZet.blog.dto.RegisterUserRequest;
import com.ErZet.blog.exception.UserAlreadyRegisterException;
import com.ErZet.blog.jpa.UserRepository;
import com.ErZet.blog.model.User;
import com.ErZet.blog.utils.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    //@Autowired
    //private EmailUtils emailUtils;
    @Value("${spring.mail.username}")
    private String formEmailId;
    @Value("${app.mail.verification-link}")
    private String mailVerificationLink;
    @Autowired
    private AppUtils appUtils;

    public User registerUser(RegisterUserRequest registerUserRequest) {
        User userTemp = userRepository.findByUserName(registerUserRequest.getUserName());
        if(!Objects.isNull(userTemp)) throw new UserAlreadyRegisterException("Email is used. Please try with forgot password.");

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        BeanUtils.copyProperties(registerUserRequest, user);

        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setIsAccountVerify(0);
        user.setIsSocialRegister(0);
        user.setPassword(bCryptPasswordEncoder.encode(registerUserRequest.getPassword()));
        user.setOtp(appUtils.getFourDigitNumber());

        User saveUser = userRepository.save(user);

        String emailVerificationFullLink = mailVerificationLink+user.getOtp()+saveUser.getUserId();
        //emailUtils.sendMail(... to_do)

        log.info("Email verification link : "+ emailVerificationFullLink);
        return user;
    }

    public User login(LoginUserRequest loginUserRequest) throws  Exception {
        //TODO
        return null;
    }

    public User verifyEmailId(Integer otp, String userId) {
        //TODO
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //TODO
        return null;
    }
}
