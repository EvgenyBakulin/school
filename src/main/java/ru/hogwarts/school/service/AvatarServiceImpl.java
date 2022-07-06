package ru.hogwarts.school.service;

import liquibase.pro.packaged.W;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.exeption.BadRequestExeption;
import ru.hogwarts.school.exeption.WrongIDExeption;
import ru.hogwarts.school.interfac.AvatarService;
import ru.hogwarts.school.interfac.StudentService;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;


import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.function.Supplier;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarServiceImpl implements AvatarService {

    @Value("${school.avatars.dir.path}")
    private String avatarsDir;
    private final StudentService studentService;
    private final AvatarRepository avatarRepository;

    Logger logger = LoggerFactory.getLogger(AvatarServiceImpl.class);


    public AvatarServiceImpl(StudentService studentService, AvatarRepository avatarRepository) {
        this.studentService = studentService;
        this.avatarRepository = avatarRepository;

    }

    public void uploadAvatar(Long id, MultipartFile file) throws IOException {
        logger.info("Вызван метод uploadAvatar");
        Student student = studentService.getStudent(id);
        if (studentService.getStudent(id) == null) {
            logger.error("Нет студента с id "+id);
            throw new WrongIDExeption();
        }
        Path pathFile = Path.of(avatarsDir, id + "." + getExtension(file.getOriginalFilename()));
        Files.createDirectories(pathFile.getParent());
        Files.deleteIfExists(pathFile);

        try (InputStream is = file.getInputStream();
             OutputStream out = Files.newOutputStream(pathFile, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bout = new BufferedOutputStream(out, 1024)) {
            bis.transferTo(bout);
        }
        Avatar avatar = new Avatar();
        avatar.setStudent(student);
        avatar.setFilePath(pathFile.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaTipe(file.getContentType());
        avatar.setData(generateImageData(pathFile));
        avatarRepository.save(avatar);

    }

    public Collection<Avatar> getAllAvatars(Integer pageNumber, Integer pageSize){
        logger.info("Вызван метод getAllAvatars");
        if ((long)(pageNumber-1)*pageSize>avatarRepository.count())
        {
            logger.error("Неверный запрос");
            throw new BadRequestExeption();
        }
        else {
            PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
            return avatarRepository.findAll(pageRequest).getContent();
        }
    }

    public Avatar findAvatar(Long id) {
        logger.info("Вызван метод findAvatar");
        return avatarRepository.findByStudentId(id).orElseThrow(()->{logger.error("");
                                                                      throw new WrongIDExeption();
        });
    }


    private byte[] generateImageData(Path filePath) throws IOException {
        try (InputStream is = Files.newInputStream(filePath);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             ByteArrayOutputStream baout = new ByteArrayOutputStream()) {
            BufferedImage image = ImageIO.read(bis);
            int height = image.getHeight() / (image.getWidth() / 100);
            BufferedImage preview = new BufferedImage(100, height, image.getType());
            Graphics2D graphics = preview.createGraphics();
            graphics.drawImage(image, 0, 0, 100, height, null);
            graphics.dispose();
            ImageIO.write(preview, getExtension(filePath.getFileName().toString()), baout);
            return baout.toByteArray();
        }
    }

    private String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }
}

