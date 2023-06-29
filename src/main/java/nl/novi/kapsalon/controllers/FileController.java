package nl.novi.kapsalon.controllers;

import nl.novi.kapsalon.dtos.FileOutputDto;
import nl.novi.kapsalon.exceptions.ResourceNotFoundException;
import nl.novi.kapsalon.models.File;
import nl.novi.kapsalon.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.core.io.Resource;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("file")
@CrossOrigin
@PreAuthorize("hasAnyRole('ROLE_HAIRDRESSER', 'ROLE_OWNER')")
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
        FileOutputDto responseDto = fileService.getFileById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable long id) {

        Optional<File> storedFile = fileService.getOptionalFileById(id);

        if (storedFile.isPresent()) {
            File file = storedFile.get();

            ByteArrayResource resource = new ByteArrayResource(file.getFile());

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getFileName());
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

            return ResponseEntity.ok().headers(headers).body(resource);
        } else {
            throw new ResourceNotFoundException("Bestand niet gevonden");
        }
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) {
        long newId = fileService.uploadFile(file);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newId).toUri();
        return ResponseEntity.created(location).body(location);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFile() {
        return ResponseEntity.noContent().build();
    }
}
