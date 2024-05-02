package com.skypro.FirstTeamPetShelter.service;

import com.skypro.FirstTeamPetShelter.model.Volunteer;

import java.util.Collection;

public interface VolunteerService {
    /**
     * Добавление нового волонтёра
     * @param volunteer     - волонтёр
     * @return              - Volunteer
     */
    Volunteer addVolunteer(Volunteer volunteer);

    /**
     * Получение волонтера из БД по ID
     * @param id            - ID волонтёра
     * @return              - Volunteer
     */
    Volunteer getVolunteerById(Long id);

    /**
     * Получение волонтёра по его телеграм ID
     * @param telegramID    - телеграм ID волонтёра
     * @return              - Volunteer
     */
    Volunteer getVolunteerByTelegramId(Long telegramID);

    /**
     * Список всех волонтёров
     * @return              - Collection<Volunteer>
     */
    Collection<Volunteer> getAllVolunteer();

    /**
     * Удаление волонтёра
     * @param id            - ID удаляемого волонтёра
     */
    void deleteVolunteer(Long id);

    /**
     * Редактирование волонтёра
     * @param volunteer     - редактируемый волонтёр
     * @return              - Volunteer
     */
    Volunteer editVolunteer(Volunteer volunteer);
}
