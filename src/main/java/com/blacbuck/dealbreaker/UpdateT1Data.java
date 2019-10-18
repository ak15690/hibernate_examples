package com.blacbuck.dealbreaker;

import lombok.Data;

@Data
public class UpdateT1Data {

    private Long id;
    private String name;

    public Long getId(){
        return id;
    }


    public String getName() {
        return this.name;
    }

    public void setId(Long id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
}

