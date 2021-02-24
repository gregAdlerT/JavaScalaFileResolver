package com.example.demo.dao;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Greg Adler
 */
@Entity
@Data
@NoArgsConstructor
public class DataModel {
    @GeneratedValue
    @Id
    private long id;
    private String content;
}
