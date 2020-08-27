package com.qa.contentmanager.service;

import com.qa.contentmanager.domain.Content;
import com.qa.contentmanager.exceptions.ContentNotFoundException;
import com.qa.contentmanager.repo.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {

    private final ContentRepository repo;

    @Autowired
    public ContentService(ContentRepository repo) {
        this.repo = repo;
    }

    //read all content entries
    public List<Content> readAllContent() {
        return this.repo.findAll();
    }

    //create a content entry and save to repo
    public Content createContent(Content content) {
        return this.repo.save(content);
    }

    //read content found by id
    public Content findContentById(Long id) {
        return this.repo.findById(id).orElseThrow(ContentNotFoundException::new);
    }
}
