package com.example.siteliconPrueba;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class UserModelAssembler implements RepresentationModelAssembler<UserEntity, EntityModel<UserEntity>> {

    @SuppressWarnings("null")
    @Override
    public EntityModel<UserEntity> toModel(UserEntity user) {
        return EntityModel.of(user, //
                linkTo(methodOn(UserController.class).one(user.getUserID())).withSelfRel(),
                linkTo(methodOn(UserController.class).all()).withRel("users"));
    }

}
