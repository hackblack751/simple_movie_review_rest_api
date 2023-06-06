package com.huy.simple_movie_review.entities;
public enum Gender {
    FEMALE(0), MALE(1), OTHER(-1);

    private Integer value;

    Gender(Integer value){
        this.value = value;
    }

    public Integer getValue(){
        return value;
    }


    public static Gender fromValue(Integer value){
        for(Gender gender : Gender.values()){
            if(gender.value.equals(value))
                return gender;
        }

        throw new IllegalArgumentException("Invalid gender value");
    }




}
