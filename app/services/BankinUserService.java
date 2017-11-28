package services;

import exception.UserNotFoundException;
import model.UserAuthResponse;
import repositories.UserRepository;

import javax.inject.Inject;
import java.util.List;

public class BankinUserService implements UserService {

    private UserRepository userRepository;

    @Inject
    public BankinUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserAuthResponse authenticate(String mail) throws UserNotFoundException {
        return userRepository.getUser(mail);
    }

    @Override
    public List<UserAuthResponse> getAllUsers() {
        return userRepository.getAllUsers();
    }
}