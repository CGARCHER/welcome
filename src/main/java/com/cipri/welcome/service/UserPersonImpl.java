package com.cipri.welcome.service;

import com.cipri.welcome.dto.UserDTO;
import com.cipri.welcome.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserPersonImpl implements IUserPersonService {

    private Map<Integer, UserDTO> users;

    public UserPersonImpl() {
        //HARDCODE-TEMPORAL
        initData();
    }

    @Override
    public List<UserDTO> getAll() {
        return this.users.values().stream().toList();
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        userDTO.setId(this.calculateNextKey());
        users.put(userDTO.getId(), userDTO);
        return userDTO;
    }

    @Override
    public UserDTO getUser(Integer id) {
        if(!users.containsKey(id)) {
            throw new NotFoundException("No existe persona con id:" + id);
        }
        return users.get(id);
    }

    @Override
    public boolean deleteUser(Integer id) {
        return users.remove(id)!=null;
    }

    private void initData(){
        this.users = new HashMap<>();
        UserDTO userDTO = new UserDTO(1, "Carlos","Alcaraz");
        this.users.put(1, userDTO);
        this.users.put(2, UserDTO.builder().id(2).name("Novak").appl("Djokovic").build());
    }

    private Integer calculateNextKey(){
        Integer max_value = 0;
        for(Integer key : this.users.keySet()){
            if(key > max_value){
                max_value = key;
            }
        }
        return max_value + 1;
    }
}
