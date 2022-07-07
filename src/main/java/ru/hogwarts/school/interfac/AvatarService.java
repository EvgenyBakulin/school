package ru.hogwarts.school.interfac;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;

import java.io.IOException;
import java.util.Collection;

public interface AvatarService {

    void uploadAvatar(Long id, MultipartFile file) throws IOException;

    Avatar findAvatar(Long id);

    Collection<Avatar> getAllAvatars(Integer pageNumber, Integer pageSize);

    int sum();
}
