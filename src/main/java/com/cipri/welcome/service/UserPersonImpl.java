package com.cipri.welcome.service;

import com.cipri.welcome.dto.RequestUpdateApplDTO;
import com.cipri.welcome.dto.UserDTO;
import com.cipri.welcome.entity.UserEntity;
import com.cipri.welcome.exception.NotFoundException;
import com.cipri.welcome.mapper.UserMapper;
import com.cipri.welcome.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPersonImpl implements IUserPersonService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserPersonImpl(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAll() {
        return userMapper.userEntityToDTO(userRepository.findAll());
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        UserEntity userEntity = userRepository.
                save(userMapper.useDTOToEntity(userDTO));
        return userMapper.userEntityToDTO(userEntity);
    }

    @Override
    public UserDTO getUser(Integer id) {
       Optional<UserEntity> optionalUserEntity =
               userRepository.findById(Long.valueOf(id));
       UserDTO userDTO = null;
        if(optionalUserEntity.isPresent()) {
            UserEntity userEntity = optionalUserEntity.get();
            userDTO = userMapper.userEntityToDTO(userEntity);
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

    @Override
    public boolean deleteUserByName(String name) {
        return userRepository.deleteByName(name)>0;
    }

    @Override
    public List<UserDTO> getUserByName(String name) {
       return userMapper
               .userEntityToDTO(userRepository.findByName(name));
    }

    @Override
    public List<UserDTO> getUserByNameContaining(String name) {
        return userMapper
                .userEntityToDTO(userRepository.findByNameContaining(name));
    }

    @Override
    public UserDTO updateApplByName(RequestUpdateApplDTO requestUpdateApplDTO) {
        boolean modificado = userRepository.updateApplByName(requestUpdateApplDTO.getName(), requestUpdateApplDTO.getAppl())>0;
        UserDTO userDTO=null;
        if(modificado) {
            userDTO = userMapper.userEntityToDTO(userRepository.findByName(requestUpdateApplDTO.getName())).get(0);
        }
        return userDTO;
    }
}