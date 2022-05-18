package com.accenture.gradetool.domain.subject.user;

import com.accenture.gradetool.core.generic.AbstractEntityServiceImpl;
import org.slf4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractEntityServiceImpl<User, UserRepository> implements UserService {

    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(Logger logger, UserRepository repository, BCryptPasswordEncoder passwordEncoder) {
        super(logger, repository);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected User preCreate(User user) {
        encodePassword(user);

        return user;
    }

    @Override
    public void changePassword(User user, Password password) {
        if (passwordEncoder.matches(password.getOldPassword(), user.getPassword())) {
            encodePassword(user.setPassword(password.getNewPassword()));

            save(user);
        }
    }

    @Override
    public void encodePassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.debug("Loading {} with email '{}'", singleEntityName(), email);

        return repository.findByEmailAndDeletedFalse(email)
                         .map(UserDetailsImpl::new)
                         .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmailAndDeletedFalse(email);
    }
}
