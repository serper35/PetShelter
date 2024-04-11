package com.skypro.FirstTeamPetShelter.service;

import com.skypro.FirstTeamPetShelter.model.Pet;

import java.util.Collection;

/**
 * Сервис животного
 */
public interface PetService {
    /**
     * Добавление нового животного в БД
     * @param pet   - Животное (Pet)
     * @return      - Возвращает Pet
     * @throws com.skypro.FirstTeamPetShelter.exception.PetAlreadyAddedException, если такое животное уже есть
     */
    Pet addPet(Pet pet);

    /**
     * Получение животного из БД
     * @param id    - ID животного
     * @return      - Возвращает Pet
     * @throws com.skypro.FirstTeamPetShelter.exception.PetNotFoundException, если животное не найдено
     */
    Pet getPet(long id);

    /**
     * Получение всех животных из БД
     * @return      - Возвращает коллекцию Pet
     */
    Collection<Pet> getAllPets();

    /**
     * Редактирование животного
     * @param id    - ID животного
     * @return      - Возвращает Pet
     * @throws com.skypro.FirstTeamPetShelter.exception.PetNotFoundException, если животное не найдено
     */
    Pet editPet(long id);

    /**
     * Удаление животного
     * @param id    - ID животного
     * @throws com.skypro.FirstTeamPetShelter.exception.PetNotFoundException, если животное не найдено
     */
    void deletePet(long id);
}
