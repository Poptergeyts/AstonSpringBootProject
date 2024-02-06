package ru.poptergeyts.astonspringbootproject.domainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.poptergeyts.astonspringbootproject.dto.UserDto;
import ru.poptergeyts.astonspringbootproject.entity.User;
import ru.poptergeyts.astonspringbootproject.exception.LoginAlreadyExistsException;
import ru.poptergeyts.astonspringbootproject.exception.LoginDoesNotExist;
import ru.poptergeyts.astonspringbootproject.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserContainerService {
    private final UserRepository userRepository;

    @Autowired
    public UserContainerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
    Метод для проверки находится ли пользователь в контейнере
     */
    public boolean hasUser(UserDto userDto) {
        return userRepository.findByLogin(userDto.getLogin()).isPresent();
    }

    public String getUserPassword(UserDto userDto) throws LoginDoesNotExist{
        Optional<User> user = userRepository.findByLogin(userDto.getLogin());

        if (!user.isPresent()) {
            throw new LoginDoesNotExist();
        }

        return user.get().getPassword();
    }

    public void setUserPassword(UserDto userDto, String newPassword) throws LoginDoesNotExist{
        Optional<User> user = userRepository.findByLogin(userDto.getLogin());

        if (!user.isPresent()) {
            throw new LoginDoesNotExist();
        }

        user.get().setPassword(newPassword);
        userRepository.save(user.get());
    }

    /*
    Метод для добавления пользователя в контейнер
     */
    public void addUser(UserDto userDto) throws LoginAlreadyExistsException{
        if (userRepository.findByLogin(userDto.getLogin()).isPresent()) {
            throw new LoginAlreadyExistsException();
        }

        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
    }

    /*
    Метод для получения всех пользователей из контейнера
     */
    public List<UserDto> getAll() {
        return mapListOfUsers(userRepository.findAll());
    }

    private List<UserDto> mapListOfUsers(List<User> users) {
        List<UserDto> usersDto = new ArrayList<>(users.size());
        for (User user : users) {
            usersDto.add(new UserDto(user.getLogin(), user.getPassword()));
        }
        return usersDto;
    }
}
