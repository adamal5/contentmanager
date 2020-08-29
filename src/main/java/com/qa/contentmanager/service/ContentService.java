package com.qa.contentmanager.service;

import com.qa.contentmanager.domain.Content;
import com.qa.contentmanager.dto.ContentDTO;
import com.qa.contentmanager.exceptions.ContentNotFoundException;
import com.qa.contentmanager.repo.ContentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentService {

    private final ContentRepository repo;
    private final ModelMapper mapper;

    @Autowired
    public ContentService(ContentRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    private ContentDTO mapToDTO(Content content){
        return this.mapper.map(content, ContentDTO.class);
    }

    //read all content entries
    public List<ContentDTO> readAllContent() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    //create a content entry and save to repo
    public ContentDTO createContent(Content content) {
        return this.mapToDTO(this.repo.save(content));
    }

    //read content found by id
    public ContentDTO findContentById(Long id) {
        return this.mapToDTO(this.repo.findById(id)
                .orElseThrow(ContentNotFoundException::new));
    }

    //update content by id
    public ContentDTO updateContent(Long id, Content content){
        Content update = this.repo.findById(id).orElseThrow(ContentNotFoundException::new);
        update.setTitle(content.getTitle());
        update.setContentType(content.getContentType());
        update.setPlatform(content.getPlatform());
        update.setStatus(content.getStatus());
        update.setPostDate(content.getPostDate());
        update.setNotes(content.getNotes());
        return this.mapToDTO(this.repo.save(update));
    }

    public boolean deleteContentById(Long id){
        if(!this.repo.existsById(id)){
            throw new ContentNotFoundException();
        }
        this.repo.deleteById(id);
        return this.repo.existsById(id);
    }

}
