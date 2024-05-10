package com.skypro.FirstTeamPetShelter.service;

import com.skypro.FirstTeamPetShelter.model.PetAvatar;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Сервис аватаров питомцев
 */
public interface PetAvatarService {
    /**
     * Добавить аватар питомца в БД
     * @param petAvatarFile - Аватар питомца
     * @return          - Возвращает PetAvatarFile
     */
    void addPetAvatar(Long id, MultipartFile petAvatarFile) throws IOException;

    /**
     * Получить аватар питомца
     * @param id        - ID аватара питомца
     * @return          - Возвращает PetAvatar
     * @throws com.skypro.FirstTeamPetShelter.exception.PetAvatarNotFoundException, если аватар питомца не найден
     */
    PetAvatar getPetAvatarById(long id);

    /**
     * Получить аватар питомца по питомцу
     * @param pet_id    - ID питомца
     * @return          - Возвращает PetAvatar
     * @throws com.skypro.FirstTeamPetShelter.exception.PetAvatarNotFoundException, если аватар питомца не найден
     */
    PetAvatar getPetAvatarByPet(long pet_id);

    /**
     * Получить все аватары питомцев. При наличии большого объема питомцев использовать пагинацию.
     * @param pageNumber - номер страницы
     * @param pageSize - количество аватаров на страницу
     * @return          - Возвращает Collection<PetAvatar>
     */
    // todo проверить как пагинация работает в телеграм-боте
    Collection<PetAvatar> getAllPetAvatars(int pageNumber, int pageSize);

    /**
     * Отредактировать аватар питомца - реализация повторяет метод добавления аватара.
     * @param id        - ID аватара питомца
     * @return          - Возвращает PetAvatar
     * @throws com.skypro.FirstTeamPetShelter.exception.PetAvatarNotFoundException, если аватар питомца не найден
     */
    void editPetAvatar(Long id, MultipartFile petAvatarFile) throws IOException;

    /**
     * Удалить аватар питомца
     * @param id        - ID аватара питомца
     * @throws com.skypro.FirstTeamPetShelter.exception.PetAvatarNotFoundException, если аватар питомца не найден
     */
    void deletePetAvatar(long id);
}
