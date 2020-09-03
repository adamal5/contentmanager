package com.qa.contentmanager.rest;

import com.qa.contentmanager.domain.Sponsor;
import com.qa.contentmanager.dto.SponsorDTO;
import com.qa.contentmanager.service.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<SponsorDTO>> getAllSponsor(){
        return ResponseEntity.ok(this.sponsorService.readAllSponsor());
    }

    @PostMapping("/createSponsor")
    public ResponseEntity<SponsorDTO> createSponsor(@RequestBody Sponsor sponsor){
        return new ResponseEntity<SponsorDTO>(this.sponsorService.createSponsor(sponsor),HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteSponsor/{id}")
    public ResponseEntity<?> deleteSponsor(@PathVariable Long id){
        return this.sponsorService.deleteSponsorById(id)
                ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                : ResponseEntity.noContent().build();
    }

    @GetMapping("/getSponsorById/{id}")
    public ResponseEntity <SponsorDTO> getSponsorById(@PathVariable Long id){
        return ResponseEntity.ok(this.sponsorService.findSponsorById(id));
    }

    @PutMapping("/updateSponsor/{id}")
    public ResponseEntity <SponsorDTO> updateSponsor(@PathVariable Long id, @RequestBody Sponsor sponsor){
        return ResponseEntity.ok(this.sponsorService.updateSponsor(id, sponsor));
    }

}
