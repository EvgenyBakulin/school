package ru.hogwarts.school.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.interfac.AvatarService;
import ru.hogwarts.school.model.Avatar;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

@RestController
@RequestMapping("/student")
public class AvatarController {
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping(value = "/{id}/avatars", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatars(@PathVariable Long id, @RequestParam MultipartFile avatar) throws IOException {
        if (avatar.getSize() >= 1024 * 600) {
            return ResponseEntity.badRequest().body("file is too big");
        }
        avatarService.uploadAvatar(id, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/baseAvatar")
    public ResponseEntity<byte[]> downloadAvatarFromBase(@PathVariable Long id) throws IOException {
        Avatar avatar = avatarService.findAvatar(id);
        HttpHeaders head = new HttpHeaders();
        head.setContentType(MediaType.parseMediaType(avatar.getMediaTipe()));
        head.setContentLength(avatar.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(head).body(avatar.getData());
    }

    @GetMapping(value = "/{id}/fileAvatar")
    public void downloadAvatarFromFile(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.findAvatar(id);
        Path path = Path.of(avatar.getFilePath());
        try (InputStream is = Files.newInputStream(path);
             OutputStream out = response.getOutputStream();) {
            response.setStatus(200);
            response.setContentType(avatar.getMediaTipe());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(out);
        }
    }
    @GetMapping(value = "/avatars")
    public Collection<Avatar> getAvatars(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return avatarService.getAllAvatars(pageNumber,pageSize);
    }

}
