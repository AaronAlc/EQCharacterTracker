package com.eq.charactertracker.service;

import com.eq.charactertracker.repo.UserRepo;
import com.eq.charactertracker.entity.UserEntity;
import com.eq.charactertracker.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    public User createUser(User user) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        userEntity = userRepo.save(userEntity);
        user = modelMapper.map(userEntity, User.class);
        return user;
    }

    public User getUserById(Long userId) {
        UserEntity userEntity = userRepo.getReferenceById(userId);
        User user = modelMapper.map(userEntity, User.class);
        return user;
    }

    public List<User> getAllUsers() {
        List userList = new ArrayList();
        List<UserEntity> userEntityList = userRepo.findAll();
        userEntityList.stream().forEach(ue -> userList.add(modelMapper.map(ue, User.class)));
        return userList;
    }
}
