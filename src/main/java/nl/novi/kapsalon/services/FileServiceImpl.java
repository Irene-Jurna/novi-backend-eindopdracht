package nl.novi.kapsalon.services;

import nl.novi.kapsalon.exceptions.ResourceNotFoundException;
import nl.novi.kapsalon.models.File;
import nl.novi.kapsalon.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepos;

    @Override
    public Iterable<File> getFiles() {
        return fileRepos.findAll();
    }

    @Override
    public Optional<File> getFileById(long id) {
        if (!fileRepos.existsById(id)) throw new ResourceNotFoundException("Foto niet gevonden, probeer een ander id");
        return fileRepos.findById(id);
    }

    @Override
    public boolean fileExistsById(long id) {
        return fileRepos.existsById(id);
    }

    @Override
    public long uploadFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            File newFileToStore = new File();
            newFileToStore.setFileName(fileName);
            newFileToStore.setFile(file.getBytes());
            File storedFile = fileRepos.save(newFileToStore);
            return storedFile.getId();
        } catch (IOException e) {
            throw new ResourceNotFoundException("Failed to upload file");
        }

    }

    @Override
    public void deleteFile(long id) {
        if (!fileRepos.existsById(id)) throw new ResourceNotFoundException("Deze foto staat niet in het systeem");
        fileRepos.deleteById(id);
    }
}
