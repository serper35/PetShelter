package com.skypro.FirstTeamPetShelter.service;

import com.skypro.FirstTeamPetShelter.model.PetAvatar;

import java.util.Collection;

/**
 * Сервис аватаров питомцев
 */
public interface PetAvatarService {
    /**
     * Добавить аватар питомца в БД
     * @param petAvatar - Аватар питомца
     * @return          - Возвращает PetAvatar
     */
    PetAvatar addPetAvatar(PetAvatar petAvatar);

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
     * Получить все аватары питомцев
     * @return          - Возвращает Collection<PetAvatar>
     */
    Collection<PetAvatar> getAllPetAvatars();

    /**
     * Отредактировать аватар питомца
     * @param id        - ID аватара питомца
     * @return          - Возвращает PetAvatar
     * @throws com.skypro.FirstTeamPetShelter.exception.PetAvatarNotFoundException, если аватар питомца не найден
     */
    PetAvatar editPetAvatar(long id);

    /**
     * Удалить аватар питомца
     * @param id        - ID аватара питомца
     * @throws com.skypro.FirstTeamPetShelter.exception.PetAvatarNotFoundException, если аватар питомца не найден
     */
    void deletePetAvatar(long id);
}
