/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.ksm.models;

import lombok.Data;

/**
 *
 * @author Dony Tri P
 */
@Data
public class Person {
    
    private Integer id;
    private String firstName;
    private String lastName;
    private String gender;

    public Person() {
    }
}
