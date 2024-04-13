package com.skypro.FirstTeamPetShelter.service.Impl;

import com.skypro.FirstTeamPetShelter.exception.InfoMessageNotFoundException;
import com.skypro.FirstTeamPetShelter.model.Info;
import com.skypro.FirstTeamPetShelter.repository.InfoRepository;
import com.skypro.FirstTeamPetShelter.service.InfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class InfoServiceImpl implements InfoService {
    private final InfoRepository infoRepository;

    private final Logger logger = LoggerFactory.getLogger(InfoServiceImpl.class);

    public InfoServiceImpl(InfoRepository infoRepository) {
        this.infoRepository = infoRepository;
    }

    @Override
    public Info addInfo(Info info) {
        return infoRepository.save(info);
    }

    @Override
    public String getMessage(long id) {
        Info info = infoRepository.findById(id).orElseThrow(() -> {
            logger.error("Невозможно получить информацию! Строка с ID: {} не найдена!", id);
            return new InfoMessageNotFoundException("Невозможно получить информацию! Строка с ID: " + id + " не найдена!");
        });
        return info.getMessage();
    }

    @Override
    public String getMessage(String keyWord) {
        Info info = infoRepository.findByKeyWord(keyWord).orElseThrow(() -> {
            logger.error("Невозможно получить информацию! Строка с keyWord: {} не найдена.", keyWord);
            return new InfoMessageNotFoundException("Невозможно получить информацию! Строка с keyWord: " + keyWord + " не найдена.");
        });
        return info.getMessage();
    }

    @Override
    public Collection<Info> getAllInfo() {
        return infoRepository.findAll().stream().toList();
    }

    @Override
    public void deleteInfo(long id) {
        logger.warn("Внимание! Информация с ID: {} удаляется!", id);
        if (infoRepository.findById(id).isEmpty()) {
            logger.error("Удаление информации не удалось! Строка с ID: {} не найдена!", id);
            throw new InfoMessageNotFoundException("Удаление информации не удалось! Строка с ID: " + id + " не найдена!");
        }
        infoRepository.deleteById(id);
    }

    @Override
    public void deleteInfo(String keyWord) {
        logger.warn("Внимание! Информация с keyWord: {} удаляется!", keyWord);
        if (infoRepository.findByKeyWord(keyWord).isEmpty()) {
            logger.error("Удаление информации не удалось! Строка с keyWord: {} не найдена!", keyWord);
            throw new InfoMessageNotFoundException("Удаление информации не удалось! Строка с keyWord: " + keyWord + " не найдена!");
        }
        infoRepository.deleteByKeyWord(keyWord);
    }
}
