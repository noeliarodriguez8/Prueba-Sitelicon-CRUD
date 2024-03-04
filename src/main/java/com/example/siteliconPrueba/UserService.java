package com.example.siteliconPrueba;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<UserEntity> getUsers() {
        return repository.findAll();
    }

    public UserEntity create(NewUser newUser) {
        return repository.saveAndFlush(new UserEntity(newUser.name(),
                newUser.lastName(), newUser.phoneNumber(), newUser.email(), newUser.password()));
    }

    public List<UserEntity> search(UserSearch UserSearch) {
        if (StringUtils.hasText(UserSearch.name()) //
                && StringUtils.hasText(UserSearch.lastName())) {
            return repository.findByNameContainsOrLastNameContainsAllIgnoreCase(UserSearch.name(),
                    UserSearch.lastName());
        }
        if (StringUtils.hasText(UserSearch.name())) {
            return repository.findByNameContainsIgnoreCase(UserSearch.name());
        }
        if (StringUtils.hasText(UserSearch.lastName())) {
            return repository.findByLastNameContainsIgnoreCase(UserSearch.lastName());
        }
        return Collections.emptyList();
    }
}
