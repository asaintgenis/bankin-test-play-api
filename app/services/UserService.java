package services;

import exception.UserNotFoundException;
import model.UserAuthResponse;

import java.util.List;

public interface UserService {

    UserAuthResponse authenticate(String mail) throws UserNotFoundException;

    List<UserAuthResponse> getAllUsers();
}
