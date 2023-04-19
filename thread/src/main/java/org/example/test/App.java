package org.example.test;

import org.example.dao.UserDao;
import org.example.entity.User;
import org.example.executor.ExecutorServiceAddUser;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) throws SQLException {
//        UserDao userDao = new UserDao();
//        List<User> users = userDao.getUsers();
//        System.out.println(users.size());
//        users.forEach(System.out::println);
//        System.out.println("leuleu");

//        User user = new User().setName("Henry").setEmaillAddress("dinhluyen45@gmail.com");
//        userDao.saveUser(user);
        UserDao userDao = new UserDao();
        List<User> users = new ArrayList<>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        File file = new File("userList.txt");
        BufferedReader bufferedReader = null;
        try {
            FileReader fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while((line = bufferedReader.readLine()) != null) {
                System.out.println(Thread.currentThread().getName() + " " + line);
                getListUser(line, users);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        for(User user : users) {
            ExecutorServiceAddUser executorServiceAddUser = new ExecutorServiceAddUser(user, userDao);
            try {
                executorService.submit(executorServiceAddUser);
                System.out.println("add succssfully " + " by ");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        executorService.shutdown();
        System.out.println("main end here!");
    }

    public static List<User> getListUser(String user, List<User> list) {
        String[] split = user.split(",");
        list.add(new User().
                setId(split[0])
                .setName(split[1])
                .setEmaillAddress(split[2]));
        return list;
    }
}
