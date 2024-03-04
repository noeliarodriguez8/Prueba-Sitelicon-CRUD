package com.example.siteliconPrueba;

import java.util.List;
//import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
class UserController {

    private final UserRepository repository;
    private final UserModelAssembler assembler;
    // private static final String template = "Hello, %s!";
    // private final AtomicInteger counter = new AtomicInteger();

    UserController(UserRepository repository, UserModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/users-search-name-lastname")
    public List<UserEntity> getUsersByNameAndLastName(@RequestParam(value = "name") String name,
            @RequestParam(value = "lastName") String lastName) {
        return repository.findByFullName(name, lastName);
    }

    @GetMapping("/users-search-name")
    public List<UserEntity> getUsersByName(@RequestParam(value = "name") String name) {
        return repository.findByNameContainsIgnoreCase(name);
    }

    @SuppressWarnings("null")
    @GetMapping("/users")
    CollectionModel<EntityModel<UserEntity>> all() {
        List<EntityModel<UserEntity>> Users = repository.findAll().stream()
                .map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(Users,
                linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @SuppressWarnings("null")
    @PostMapping("/users")
    UserEntity newUser(@RequestBody UserEntity newUser) {
        return repository.save(newUser);
    }

    // Single item
    // @SuppressWarnings("null")
    // @GetMapping("/Users/{UserID}")
    // UserEntity one(@PathVariable Integer UserID) {
    // return repository.findById(UserID).orElseThrow(() -> new
    // UserNotFoundException(UserID));
    // }

    @SuppressWarnings("null")
    @GetMapping("/users/{userID}")
    EntityModel<UserEntity> one(@PathVariable Integer userID) {
        UserEntity user = repository.findById(userID).orElseThrow(() -> new UserNotFoundException(userID));
        return assembler.toModel(user);
    }

    @SuppressWarnings("null")
    @PutMapping("/users/{userID}")
    UserEntity replaceUser(@RequestBody UserEntity newUser, @PathVariable Integer userID) {

        return repository.findById(userID)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setLastName(newUser.getLastName());
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setUserID(userID);
                    return repository.save(newUser);
                });
    }

    // @DeleteMapping("/users/{userID}")
    // void deleteUser(@PathVariable Integer userID) {
    // repository.deleteByID(userID);
    // }

}
