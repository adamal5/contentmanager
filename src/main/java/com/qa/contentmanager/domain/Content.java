package com.qa.contentmanager.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Content {

    @Id
    @GeneratedValue
    private Long contentID;

    @Column
    private String title;

    @Column
    private String contentType;

    @Column
    private String platform;

    @Column
    private String status;

    @Column
    private String postDate;

    @Column
    private String sponsored;

}