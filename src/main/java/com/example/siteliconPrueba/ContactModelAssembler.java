package com.example.siteliconPrueba;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class ContactModelAssembler implements RepresentationModelAssembler<ContactEntity, EntityModel<ContactEntity>> {

    @SuppressWarnings("null")
    @Override
    public EntityModel<ContactEntity> toModel(ContactEntity contact) {
        return EntityModel.of(contact,
                linkTo(methodOn(ContactController.class).one(contact.getContactID())).withSelfRel(),
                linkTo(methodOn(ContactController.class).all()).withRel("contacts"));
    }

}
