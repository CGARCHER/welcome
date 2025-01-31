package com.cipri.welcome.service;

import com.cipri.welcome.dto.UserDTO;
import com.cipri.welcome.entity.UserEntity;
import com.cipri.welcome.exception.NotFoundException;
import com.cipri.welcome.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserPersonImpl implements IUserPersonService {
    private final UserRepository userRepository;

    public UserPersonImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().
                map(user ->
                        new UserDTO(user.getId().intValue(), user.getName(), user.getAppl()))
                .toList();

    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        UserEntity userEntity = userRepository.save(UserEntity.builder()
                .name(userDTO.getName())
                .appl(userDTO.getAppl()).build());

        return UserDTO.builder()
                .id(userEntity.getId().intValue())
                .name(userEntity.getName())
                .appl(userEntity.getAppl())
                .build();
    }

    @Override
    public UserDTO getUser(Integer id) {
       Optional<UserEntity> optionalUserEntity =
               userRepository.findById(Long.valueOf(id));
       UserDTO userDTO = null;
        if(optionalUserEntity.isPresent()) {
            UserEntity userEntity = optionalUserEntity.get();
            userDTO = UserDTO.builder()
                    .id(userEntity.getId().intValue())
                    .name(userEntity.getName())
                    .appl(userEntity.getAppl())
                    .build();
        }
        else{
            throw new NotFoundException("No existe persona con id:" + id);
        }
        return userDTO;
    }

    @Override
    public boolean deleteUser(Integer id) {
        userRepository.deleteById(Long.valueOf(id));
        return true;
    }
}
