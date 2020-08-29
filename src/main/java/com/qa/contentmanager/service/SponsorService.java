package com.qa.contentmanager.service;

import com.qa.contentmanager.domain.Sponsor;
import com.qa.contentmanager.dto.SponsorDTO;
import com.qa.contentmanager.exceptions.SponsorNotFoundException;
import com.qa.contentmanager.repo.SponsorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SponsorService {

    private final SponsorRepository repo;
    private final ModelMapper mapper;

    @Autowired
    public SponsorService (SponsorRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    private SponsorDTO mapToDTO(Sponsor sponsor){
        return this.mapper.map(sponsor, SponsorDTO.class);}

    public List<SponsorDTO> readAllSponsor(){
    return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }


    public SponsorDTO createSponsor(Sponsor sponsor) {
        return this.mapToDTO(this.repo.save(sponsor));
    }

    public SponsorDTO findSponsorById(Long id) {
        return this.mapToDTO(this.repo.findById(id).orElseThrow(SponsorNotFoundException::new));
    }

    public SponsorDTO updateSponsor(Long id, Sponsor sponsor){
        Sponsor update = this.repo.findById(id).orElseThrow(SponsorNotFoundException::new);;
        update.setCompanyName(sponsor.getCompanyName());
        update.setPrimaryContactName(sponsor.getPrimaryContactName());
        update.setPrimaryContactEmail(sponsor.getPrimaryContactEmail());
        update.setPrimaryContactPhone(sponsor.getPrimaryContactPhone());
        update.setNotes(sponsor.getNotes());
        return this.mapToDTO(this.repo.save(update));
    }

    public boolean deleteSponsorById(Long id){
        if(!this.repo.existsById(id)){
            throw new SponsorNotFoundException();
        }
        this.repo.deleteById(id);
        return this.repo.existsById(id);
    }

}
