package com.ariel.dev22.e_commerce_backend.product.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Category {
    BEAUTY("beauty"),
    FEMALE_FASHION("female_fashion"),
    MALE_FASHION("male_fashion"),
    SPORT("sport");

    private String categoryValue;

    public static Category getCategoryOf(String category){
        for (Category c : Category.values()){
            if (c.getCategoryValue().equals(category)) return c;
        }
        return null;
    }
}
