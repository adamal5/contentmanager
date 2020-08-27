package com.qa.contentmanager.rest;

import com.qa.contentmanager.domain.Content;
import com.qa.contentmanager.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContentController {

    private final ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    //specifically used for read functionality/ browsers only able to work with getmapping
    @GetMapping("/")
    public List<Content> getAllContent(){
        return this.contentService.readAllContent();
    }

    // url for adding notes.. requestbody should include a note could be void depends on what you want to di with your abb
    @PostMapping("/createContent")
    public Content createContent(@RequestBody Content content){
        return this.contentService.createContent(content);
    }

    //long name must be same as end of url e.g id here
    @DeleteMapping("/delete/{id}")
    public Boolean deleteContent(@PathVariable Long id){
        return this.contentService.deleteContentById(id);
    }

    @GetMapping("/getContentById/{id}")
    public Content getContentById(@PathVariable Long id){
        return this.contentService.findContentById(id);
    }

    @PutMapping("/updateContent/{id}")
    public Content updateContent(@PathVariable Long id, @RequestBody Content content){
        return this.contentService.updateContent(id, content);
    }

}