package com.qa.contentmanager.dto;

import java.util.ArrayList;
import java.util.List;

public class SponsorDTO {

    private Long SponsorID;
    private String companyName;
    private String primaryContactName;
    private String primaryContactEmail;
    private String primaryContactPhone;
    private String notes;
    private List<ContentDTO> content = new ArrayList<>();


    public SponsorDTO(){}

    public SponsorDTO(String companyName, String primaryContactName, String primaryContactEmail, String primaryContactPhone, String notes) {
        super();
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

    public String getCompanyName() { return companyName; }

    public void setCompanyName(String companyName) { this.companyName = companyName; }

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

    public List<ContentDTO> getContent() { return content; }

    public void setContent(List<ContentDTO> content) { this.content = content; }
}
