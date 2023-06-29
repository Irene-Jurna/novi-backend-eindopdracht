package nl.novi.kapsalon.services;

import nl.novi.kapsalon.models.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface FileService {
    Iterable<File> getFiles();
    Optional<File> getFileById(long id);
    boolean fileExistsById(long id);
    long uploadFile(MultipartFile file);
    void deleteFile(long id);
}
