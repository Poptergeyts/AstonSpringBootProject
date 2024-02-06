package ru.poptergeyts.astonspringbootproject.applicationService;

import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.poptergeyts.astonspringbootproject.domainService.UserContainerService;
import ru.poptergeyts.astonspringbootproject.dto.ChangePasswordDto;
import ru.poptergeyts.astonspringbootproject.dto.UserDto;
import ru.poptergeyts.astonspringbootproject.exception.InvalidNewPasswordException;
import ru.poptergeyts.astonspringbootproject.exception.InvalidPasswordException;
import ru.poptergeyts.astonspringbootproject.exception.LoginAlreadyExistsException;
import ru.poptergeyts.astonspringbootproject.exception.LoginDoesNotExist;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class UserService {
    private final UserContainerService userContainerService;

    @Autowired
    public UserService(UserContainerService userContainerService) {
        this.userContainerService = userContainerService;
    }

    /*
    Метод, регистрирующий пользователя, при этом пароль шифруется через sha256
     */
    public String signUp(UserDto userDto) throws LoginAlreadyExistsException{
        if (userContainerService.hasUser(userDto)) {
            throw new LoginAlreadyExistsException();
        }

        userDto.setPassword(Hashing.sha256()
                .hashString(userDto.getPassword(), StandardCharsets.UTF_8)
                .toString());

        userContainerService.addUser(userDto);
        return "SUCCESS: User has been signed up.";
    }

    /*
    Метод для входа пользователя
     */
    public String signIn(UserDto userDto) throws LoginDoesNotExist, InvalidPasswordException{
        String passwordHash = Hashing.sha256()
                .hashString(userDto.getPassword(), StandardCharsets.UTF_8)
                .toString();

        if (passwordHash.equals(userContainerService.getUserPassword(userDto))) {
            return "SUCCESS: User has been signed in.";
        } else {
            throw new InvalidPasswordException();
        }
    }

    /*
    Метод для смены пароля пользователя
     */
    public String changePassword(ChangePasswordDto changePasswordDto)
            throws LoginDoesNotExist,
            InvalidPasswordException,
            InvalidNewPasswordException
    {
        UserDto userDto = changePasswordDto.getUserDto();
        if (!userContainerService.hasUser(userDto)) {
            throw new LoginDoesNotExist();
        }

        String oldPasswordHash = Hashing.sha256()
                .hashString(changePasswordDto.getOldPassword(), StandardCharsets.UTF_8)
                .toString();

        if (userContainerService.getUserPassword(userDto).equals(oldPasswordHash)) {
            if (changePasswordDto.getOldPassword().equals(changePasswordDto.getNewPassword())) {
                throw new InvalidNewPasswordException();
            } else {
                userContainerService.setUserPassword(userDto,
                        Hashing.sha256().hashString(changePasswordDto.getNewPassword(),
                                StandardCharsets.UTF_8).toString());
                return "SUCCESS: Password has been changed";
            }
        }
        throw new InvalidPasswordException();
    }

    /*
    Метод для вывода списка пользователей
     */
    public List<UserDto> getAll() {
        return userContainerService.getAll();
    }
}
