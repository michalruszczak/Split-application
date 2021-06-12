package pl.ruszczak;

import pl.ruszczak.model.User;

public interface UserService {
    User findByUserName(String name);
    void saveUser(User user);
    User getCurrentUser();
}
