package com.example.siteliconPrueba;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

  private final UserService userService;
  private final ContactService contactService;

  public ApiController(UserService userService, ContactService contactService) {
    this.userService = userService;
    this.contactService = contactService;
  }

  @GetMapping("/api/users")
  public List<UserEntity> allUsers() {
    return userService.getUsers();
  }

  @GetMapping("/api/contacts")
  public List<ContactEntity> allContacts() {
    return contactService.getContacts();
  }

  @PostMapping("/api/users")
  public UserEntity newUser(@RequestBody NewUser newUser) {
    return userService.create(newUser);
  }

  @PostMapping("/api/contacts")
  public ContactEntity newContact(@RequestBody NewContact newContact) {
    return contactService.create(newContact);
  }
}