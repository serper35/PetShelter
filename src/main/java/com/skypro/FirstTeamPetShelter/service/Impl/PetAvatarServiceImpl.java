package com.skypro.FirstTeamPetShelter.service.Impl;

import com.skypro.FirstTeamPetShelter.model.Pet;
import com.skypro.FirstTeamPetShelter.model.PetAvatar;
import com.skypro.FirstTeamPetShelter.repository.PetAvatarRepository;
import com.skypro.FirstTeamPetShelter.service.PetAvatarService;
import com.skypro.FirstTeamPetShelter.service.PetService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional // Для работы вместе с аннотацией @Lob
public class PetAvatarServiceImpl implements PetAvatarService {

    private static final Logger logger = LoggerFactory.getLogger(PetAvatarServiceImpl.class);

    @Value("${path.to.petAvatars.folder}")
    private String petAvatarsDir;

    private final PetService petService;
    private final PetAvatarRepository petAvatarRepository;

    public PetAvatarServiceImpl(PetService petService, PetAvatarRepository petAvatarRepository) {
        this.petService = petService;
        this.petAvatarRepository = petAvatarRepository;
    }

    @Override
    public void  addPetAvatar(Long id, MultipartFile petAvatarFile) throws IOException {
        logger.info("Log info: Method uploadAvatar is invoke.");
        Pet pet = petService.getPet(id);
        Path pathFile = Path.of(petAvatarsDir, pet.getPetType() + "_" + pet.getId() + "." + getExtension(petAvatarFile.getOriginalFilename()));
        Files.createDirectories(pathFile.getParent());
        Files.deleteIfExists(pathFile);
        try (InputStream inputStream = petAvatarFile.getInputStream();
             OutputStream outputStream = Files.newOutputStream(pathFile, CREATE_NEW);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 1024);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 1024);
        ) {
            bufferedInputStream.transferTo(bufferedOutputStream);
        }
        PetAvatar petAvatar = findPetAvatar(id);
        petAvatar.setPet(pet);
        petAvatar.setFilePath(pathFile.toString());
        petAvatar.setFileSize(petAvatarFile.getSize());
        petAvatar.setMediaType(petAvatarFile.getContentType());
        petAvatar.setSmallAvatar(petAvatarFile.getBytes());
        petAvatarRepository.save(petAvatar);
    }

    @Override
    public PetAvatar getPetAvatarById(long id) {
        logger.info("Log info: Method getPetAvatarById is invoke.");
        return petAvatarRepository.findById(id).orElse(null);
    }

    @Override
    public PetAvatar getPetAvatarByPet(long pet_id) {
        return null;
    }

    @Override
    public Collection<PetAvatar> getAllPetAvatars(int pageNumber, int pageSize) {
        logger.info("Log info: Method getAllPetAvatars is invoke.");
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return petAvatarRepository.findAll(pageRequest).getContent();
    }

    @Override
    public void  editPetAvatar(Long id, MultipartFile petAvatarFile) throws IOException { // Та же логика, что и при загрузке нового аватара, предлагаю удалить
        logger.info("Log info: Method uploadAvatar is invoke.");
        Pet pet = petService.getPet(id);
        Path pathFile = Path.of(petAvatarsDir, pet + "." + getExtension(petAvatarFile.getOriginalFilename()));
        Files.createDirectories(pathFile.getParent());
        Files.deleteIfExists(pathFile);
        try (InputStream inputStream = petAvatarFile.getInputStream();
             OutputStream outputStream = Files.newOutputStream(pathFile, CREATE_NEW);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 1024);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 1024);
        ) {
            bufferedInputStream.transferTo(bufferedOutputStream);
        }
        PetAvatar petAvatar = findPetAvatar(id);
        petAvatar.setPet(pet);
        petAvatar.setFilePath(pathFile.toString());
        petAvatar.setFileSize(petAvatarFile.getSize());
        petAvatar.setMediaType(petAvatarFile.getContentType());
        petAvatar.setSmallAvatar(petAvatarFile.getBytes());
        petAvatarRepository.save(petAvatar);
    }

    @Override
    // Удаляем по id аватара
    public void deletePetAvatar(long id) {
        logger.info("Log info: Method deletePetAvatar is invoke.");
        petAvatarRepository.deleteById(id);
    }

    private String getExtension(String fileName) {
        logger.info("Log info: Method getExtensions is invoke.");
        return fileName.substring(fileName.lastIndexOf(".") +1 );
    }

    public PetAvatar findPetAvatar (Long id)  {
        logger.info("Log info: Method findPetAvatar is invoke.");
        return petAvatarRepository.findById(id).orElse(new PetAvatar());
    }
}
