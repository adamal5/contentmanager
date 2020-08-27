package com.qa.contentmanager.rest;

import com.qa.contentmanager.domain.Sponsor;
import com.qa.contentmanager.service.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SponsorController {

    private final SponsorService sponsorService;

    @Autowired
    public SponsorController(SponsorService sponsorService) {
        this.sponsorService = sponsorService;
    }

    @GetMapping("/sponsor")
    public List<Sponsor> getAllSponsor(){
        return this.sponsorService.readAllSponsor();
    }

    @PostMapping("/createSponsor")
    public Sponsor createSponsor(@RequestBody Sponsor sponsor){
        return this.sponsorService.createSponsor(sponsor);
    }

    @DeleteMapping("/deleteSponsor/{id}")
    public Boolean deleteSponsor(@PathVariable Long id){
        return this.sponsorService.deleteSponsorById(id);
    }

    @GetMapping("/getSponsorById/{id}")
    public Sponsor getSponsorById(@PathVariable Long id){
        return this.sponsorService.findSponsorById(id);
    }

    @PutMapping("/updateSponsor/{id}")
    public Sponsor updateSponsor(@PathVariable Long id, @RequestBody Sponsor sponsor){
        return this.sponsorService.updateSponsor(id, sponsor);
    }

}
