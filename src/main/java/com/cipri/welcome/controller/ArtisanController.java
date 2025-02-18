package com.cipri.welcome.controller;

import com.cipri.welcome.dto.RequestUpdateApplDTO;
import com.cipri.welcome.dto.UserDTO;
import com.cipri.welcome.service.IUserPersonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class ArtisanController {
    private final IUserPersonService userPersonService;

    public ArtisanController(IUserPersonService userPersonService) {
        this.userPersonService = userPersonService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAll(){
        return ResponseEntity.ok(userPersonService.getAll());
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userPersonService.createUser(userDTO));
    }
    @GetMapping("/get")
    public ResponseEntity<UserDTO> getUser(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.FOUND).body(userPersonService.getUser(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userPersonService.deleteUser(id));
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<List<UserDTO>> getUserByName(@PathVariable String name){
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(userPersonService.getUserByName(name));
    }

    @GetMapping("/getByNameContaining/{name}")
    public ResponseEntity<List<UserDTO>> getUserByNameContaining(@PathVariable String name){
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(userPersonService.getUserByNameContaining(name));
    }

    @DeleteMapping("/deleteByName/{name}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userPersonService.deleteUserByName(name));
    }

    @PatchMapping("/updateByName")
    public ResponseEntity<UserDTO> updateUserApplByName(@RequestBody RequestUpdateApplDTO requestUpdateApplDTO){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userPersonService.updateApplByName(requestUpdateApplDTO));
    }
}
