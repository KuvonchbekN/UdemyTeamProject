package by.KuvonchbekN.service;


import by.KuvonchbekN.model.Course;
import by.KuvonchbekN.model.User;
import by.KuvonchbekN.model.UserRole;

import java.util.List;
import java.util.UUID;

public class UserService implements BaseService<User, User, List<User>>{

    public UserService() {
        this.add(new User("admin", "admin", "root", UserRole.ADMIN));
    }

    @Override
    public User add(User user) {
        BaseService.userList.add(user);
        return user;
    }

    @Override
    public User get(UUID id) {

        return null;
    }

    public User getUser(String phoneNumber, String password) {
        for (User user: BaseService.userList) {
            if(user.getPhoneNumber().equals(phoneNumber) && user.getPassword().equals(password) && user.isActive())
                return user;
        }
        return null;
    }

    @Override
    public User check(String str) {
        for (User user: BaseService.userList) {
            if(user.getPhoneNumber().equals(str) && user.isActive())
                return user;

        }
        return null;
    }

    @Override
    public List<User> list(User user) {
        return BaseService.userList;
    }

    @Override
    public Boolean delete(UUID id) {
        int ind = 0;
        for (User user: BaseService.userList) {
            if(user.getId().equals(id) && user.isActive()){
                user.setActive(false);
                BaseService.userList.set(ind, user);
            }
            ind++;
        }
        return true;
    }

    public int sendSmsCode(){
        return (int)(Math.random() * 90000 + 10000);
    }
}
