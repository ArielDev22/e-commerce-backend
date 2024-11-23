package com.ariel.dev22.e_commerce_backend.domains.product.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Category {
    BEAUTY("beauty"),
    FEMALE_FASHION("female_fashion"),
    MALE_FASHION("male_fashion"),
    SPORT("sport");

    private String value;

    public static Category getCategoryOf(String category){
        for (Category c : Category.values()){
            if (c.getValue().equals(category)) return c;
        }
        return null;
    }
}
