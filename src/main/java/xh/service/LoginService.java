package xh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xh.mapper.UserMapper;
import xh.model.User;

@Service
public class LoginService {

    @Autowired
    private UserMapper userMapper;


    public User queryByLogin(String username, String password) {
        return userMapper.queryByLogin(username, password);
    }
}
