package com.example.siteliconPrueba;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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
class ContactController {

    private final ContactRepository repository;
    private final ContactModelAssembler assembler;

    ContactController(ContactRepository repository, ContactModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/contacts-search-name")
    public List<ContactEntity> getContactsByName(@RequestParam(value = "name") String name) {
        return repository.findByNameContainsIgnoreCase(name);
    }

    @SuppressWarnings("null")
    @GetMapping("/contacts")
    CollectionModel<EntityModel<ContactEntity>> all() {
        List<EntityModel<ContactEntity>> contacts = repository.findAll().stream()
                .map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(contacts,
                linkTo(methodOn(ContactController.class).all()).withSelfRel());
    }

    @SuppressWarnings("null")
    @PostMapping("/contacts")
    ContactEntity newContact(@RequestBody ContactEntity newContact) {
        return repository.save(newContact);
    }

    @SuppressWarnings("null")
    @GetMapping("/contacts/{contactID}")
    EntityModel<ContactEntity> one(@PathVariable Integer contactID) {
        ContactEntity contact = repository.findById(contactID)
                .orElseThrow(() -> new ContactNotFoundException(contactID));
        return assembler.toModel(contact);
    }

    @SuppressWarnings("null")
    @PutMapping("/contacts/{contactID}")
    ContactEntity replaceContact(@RequestBody ContactEntity newContact, @PathVariable Integer contactID) {
        return repository.findById(contactID)
                .map(contact -> {
                    contact.setName(newContact.getName());

                    return repository.save(contact);
                })
                .orElseGet(() -> {
                    newContact.setContactID(contactID);
                    return repository.save(newContact);
                });
    }

    // @DeleteMapping("/contacts/{contactID}")
    // void deleteContact(@PathVariable Integer contactID) {
    // repository.deleteByID(contactID);
    // }
}
