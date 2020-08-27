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

    public Content() {
    }

    public Content(String title, String contentType, String platform, String status, String postDate, String sponsored) {
        this.title = title;
        this.contentType = contentType;
        this.platform = platform;
        this.status = status;
        this.postDate = postDate;
        this.sponsored = sponsored;
    }


}