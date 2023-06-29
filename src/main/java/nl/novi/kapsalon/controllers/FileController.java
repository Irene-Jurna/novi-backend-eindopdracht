package nl.novi.kapsalon.controllers;

import nl.novi.kapsalon.dtos.FileInputDto;
import nl.novi.kapsalon.models.File;
import nl.novi.kapsalon.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("file")
@CrossOrigin
public class FileController {

    @Autowired
    FileService fileService;

    @GetMapping
    public ResponseEntity<Object> getFiles() {
        Iterable<File> files = fileService.getFiles();
        return ResponseEntity.ok().body(files);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getFileInfo(@PathVariable long id) {
        return ResponseEntity.noContent().build();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) {
        long newId = fileService.uploadFile(file);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newId).toUri();
        return ResponseEntity.created(location).body(location);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteFile() {
        return ResponseEntity.noContent().build();
    }
}
