package com.example.siteliconPrueba;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ContactService {

    private final ContactRepository repository;

    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }

    public List<ContactEntity> getContacts() {
        return repository.findAll();
    }

    public ContactEntity create(NewContact newContact) {
        return repository.saveAndFlush(new ContactEntity(newContact.name(),
                newContact.message(), newContact.email(), newContact.reason()));
    }

    public List<ContactEntity> search(ContactSearch ContactSearch) {
        if (StringUtils.hasText(ContactSearch.name())) {
            return repository.findByNameContainsIgnoreCase(ContactSearch.name());
        }
        return Collections.emptyList();
    }

}
