package com.qa.contentmanager.service;

import com.qa.contentmanager.domain.Sponsor;
import com.qa.contentmanager.exceptions.SponsorNotFoundException;
import com.qa.contentmanager.repo.SponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SponsorService {

    private final SponsorRepository repo;

    @Autowired
    public SponsorService (SponsorRepository repo) {
        this.repo = repo;
    }


    public List<Sponsor> readAllSponsor() {
        return this.repo.findAll();
    }

    public Sponsor createSponsor(Sponsor sponsor) {
        return this.repo.save(sponsor);
    }

    public Sponsor findSponsorById(Long id) {
        return this.repo.findById(id).orElseThrow(SponsorNotFoundException::new);
    }

    public Sponsor updateSponsor(Long id, Sponsor sponsor){
        Sponsor update = findSponsorById(id);
        update.setCompanyName(sponsor.getCompanyName());
        update.setPrimaryContactName(sponsor.getPrimaryContactName());
        update.setPrimaryContactEmail(sponsor.getPrimaryContactEmail());
        update.setPrimaryContactPhone(sponsor.getPrimaryContactPhone());
        update.setNotes(sponsor.getNotes());
        return this.repo.save(update);
    }

    public boolean deleteSponsorById(Long id){
        if(!this.repo.existsById(id)){
            throw new SponsorNotFoundException();
        }
        this.repo.deleteById(id);
        return this.repo.existsById(id);
    }

}
