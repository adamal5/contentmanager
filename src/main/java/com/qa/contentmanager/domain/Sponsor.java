package com.qa.contentmanager.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Sponsor {

    @Id
    @GeneratedValue
    private Long SponsorID;

    @Column(nullable = false)
    private String companyName;

    @Column (nullable = false)
    private String primaryContactName;

    @Column (nullable = false, unique = true)
    private String primaryContactEmail;

    @Column
    private String primaryContactPhone;

    @Column
    private String notes;


    public Sponsor(){}



}
