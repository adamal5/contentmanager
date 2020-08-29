package com.qa.contentmanager.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "sponsor", fetch = FetchType.LAZY)
    private List<Content> content = new ArrayList<>();


    public Sponsor(){}

    public Sponsor(String companyName, String primaryContactName, String primaryContactEmail, String primaryContactPhone, String notes) {
        this.companyName = companyName;
        this.primaryContactName = primaryContactName;
        this.primaryContactEmail = primaryContactEmail;
        this.primaryContactPhone = primaryContactPhone;
        this.notes = notes;
    }

    public Long getSponsorID() {
        return SponsorID;
    }

    public void setSponsorID(Long sponsorID) {
        SponsorID = sponsorID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPrimaryContactName() {
        return primaryContactName;
    }

    public void setPrimaryContactName(String primaryContactName) {
        this.primaryContactName = primaryContactName;
    }

    public String getPrimaryContactEmail() {
        return primaryContactEmail;
    }

    public void setPrimaryContactEmail(String primaryContactEmail) {
        this.primaryContactEmail = primaryContactEmail;
    }

    public String getPrimaryContactPhone() {
        return primaryContactPhone;
    }

    public void setPrimaryContactPhone(String primaryContactPhone) {
        this.primaryContactPhone = primaryContactPhone;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Content> getContent() { return content; }

    public void setContent(List<Content> content) { this.content = content; }
}
