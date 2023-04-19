package org.example.executor;

import org.example.dao.UserDao;
import org.example.entity.User;

import java.util.concurrent.Callable;

public class ExecutorServiceAddUser implements Callable<Integer> {
    User user;
    UserDao userDao;

    public ExecutorServiceAddUser(User user, UserDao userDao) {
        this.user = user;
        this.userDao = userDao;
    }

    @Override
    public Integer call() throws Exception {
        Integer rs = 0;
        rs = userDao.saveUser(user);
        System.out.println("thread handle this " + Thread.currentThread().getName() + user);
        return rs;
    }
}
