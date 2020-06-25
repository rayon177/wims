package scu.wims.service;

import scu.wims.entity.User;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @Description
 * @Author hyr
 * @Version
 * @Date 2020-05-31  22:45
 */
public interface MessageService {
    void send(ArrayList<User> users) throws IOException;
}
