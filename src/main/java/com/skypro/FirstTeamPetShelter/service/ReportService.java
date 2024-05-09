package com.skypro.FirstTeamPetShelter.service;

import com.skypro.FirstTeamPetShelter.model.Report;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Сервис отчета усыновителя животного
 */
public interface ReportService {
    /**
     * Добавление отчета в БД
     * @param report    - Отчет
     * @return          - Возвращает Report
     */
    Report addReport(Report report);

    /**
     * Получить отчет из БД по его ID
     * @param id    - ID отчета
     * @return      - Возвращает Report
     * @throws com.skypro.FirstTeamPetShelter.exception.ReportNotFoundException, если отчет не найден
     */
    Report getReportById(long id);

    /**
     * Получить список отчетов на соответствующую дату
     * @param dateTime  - Дата
     * @return      - Возвращает Collection<Report>
     */
    Collection<Report> getReportsByDate(LocalDateTime dateTime);

    /**
     * Получение отчета по усыновителю, статусу
     *
     * @param adopter_id - ID усыновителя
     * @param isReview   - статус (true - рассмотрен, false - не рассмотрен)
     * @return - Report
     */
    Collection<Report> getReportsByAdopterAndReviewed(long adopter_id, boolean isReview);

    /**
     * Получить список отчетов по усыновителю
     * @param adopter_id   - ID усыновителя
     * @return          - Возвращает Collection<Report>
     */
    Collection<Report> getReportsByAdopter(long adopter_id);

    /**
     * Получить все отчеты
     * @return          - Возвращает список всех отчетов
     */
    Collection<Report> getAllReports();

    /**
     * Отредактировать отчет
     * @param id     - ID отчета
     * @param report - Report
     * @return       - Возвращает Report
     * @throws com.skypro.FirstTeamPetShelter.exception.ReportNotFoundException, если отчет не найден
     */
    Report editReport(long id, Report report);

    /**
     * Удалить отчет
     * @param id    - ID отчета
     * @throws com.skypro.FirstTeamPetShelter.exception.ReportNotFoundException, если отчет не найден
     */
    void deleteReport(long id);
}
