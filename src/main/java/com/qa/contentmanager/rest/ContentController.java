package com.qa.contentmanager.rest;

import com.qa.contentmanager.domain.Content;
import com.qa.contentmanager.dto.ContentDTO;
import com.qa.contentmanager.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<ContentDTO>> getAllContent() {
        return ResponseEntity.ok(this.contentService.readAllContent());
    }

    // url for adding notes.. requestbody should include a note could be void depends on what you want to di with your abb
    @PostMapping("/createContent")
    public ResponseEntity<ContentDTO> createContent(@RequestBody Content content) {
        return new ResponseEntity<ContentDTO>(this.contentService.createContent(content), HttpStatus.CREATED);
    }

    //long name must be same as end of url e.g id here
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteContent(@PathVariable Long id){
        return this.contentService.deleteContentById(id)
                ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                : ResponseEntity.noContent().build();
    }

    @GetMapping("/getContentById/{id}")
    public ResponseEntity<ContentDTO> getContentById(@PathVariable Long id){
        return ResponseEntity.ok(this.contentService.findContentById(id));
    }

    @PutMapping("/updateContent/{id}")
    public ResponseEntity <ContentDTO> updateContent(@PathVariable Long id, @RequestBody Content content){
        return ResponseEntity.ok(this.contentService.updateContent(id, content));
    }

}