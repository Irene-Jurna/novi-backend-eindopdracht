package nl.novi.kapsalon.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileInputDto {
    private String fileName;
    private MultipartFile file;
}
