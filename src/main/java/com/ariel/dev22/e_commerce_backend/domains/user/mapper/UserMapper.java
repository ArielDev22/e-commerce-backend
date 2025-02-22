package com.ariel.dev22.e_commerce_backend.domains.user.mapper;

import com.ariel.dev22.e_commerce_backend.domains.user.model.User;
import com.ariel.dev22.e_commerce_backend.domains.user.model.dto.UserData;
import com.ariel.dev22.e_commerce_backend.infra.utils.FileConverter;

public class UserMapper {
    public static UserData toUserData(User user) {
        String birthdate;
        String telephone;

        if (user.getBirthdate() == null) {
            birthdate = "";
        } else {
            birthdate = user.getBirthdate().toString();
        }

        if (user.getTelephone() == null) {
            telephone = "";
        } else {
            telephone = user.getTelephone();
        }

        byte[] imageBytes = FileConverter.loadImage(user.getProfileImage().getImageUrl());

        return new UserData(user.getId(), user.getName(), user.getEmail(), telephone, birthdate, imageBytes);
    }
}
