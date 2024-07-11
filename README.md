# Телеграм бот FirstTeam PetShelter

К нам обратился приют животных из Астаны, который 
хочет помочь людям, которые задумываются о том, чтобы забрать собаку или кошку домой. Для многих их клиентов это первый опыт, а обращений к ним каждый день поступает так много, что они не успевают их обрабатывать вручную.

Руководство приюта обратилось к нам за помощью: они попросили написать телеграмм-бота, который сможет отвечать на популярные вопросы людей о том, что нужно знать и уметь, чтобы забрать животное из приюта.

Также волонтеры приюта в течение месяца просят новых хозяев присылать ежедневный отчет о том, как животное приспосабливается к новой обстановке. Эту часть работы заказчик тоже попросил передать боту.

При разработке использовались следующие инструменты и технологии (библиотеки):

- IntelliJ IDEA
- Java 17
- [Java Telegram Bot API (версия 7.1.0)](https://github.com/pengrad/java-telegram-bot-api)
- SpringBoot (версия 3.2.3)
- Swagger
- Liquibase
- Postgresql
- Сборка с помощью Maven

## Скриншоты

<details>
    <summary>Начало работы с ботом, вывод собачьего приюта и информации о нём</summary>

![Start-DogShelter-ShelterInfo.png](images/Start-DogShelter-ShelterInfo.png)
</details>
<details>
    <summary>Расписание и схема проезда + Контакты для пропуска + ТБ + Выбор питомца</summary>

![Расписание и схема проезда + Контакты для пропуска + ТБ + Выбор питомца](images/Contacts-SafetyInfo-SelectionPet.png)
</details>
<details>
<summary>Ввод номера телефона (корректный)</summary>

![PhoneNumberCorrect.png](images/PhoneNumberCorrect.png)
</details>
<details>
<summary>Ввод номера телефона (не корректный)</summary>

![PhoneNumberIncorrect.png](images/PhoneNumberIncorrect.png)
</details>
<details>
<summary>Список питомцев кошачьего приюта и меню для выбранной кошки</summary>

![SelectCat.png](images/SelectCat.png)
</details>
<details>
<summary>Список питомцев собачьего приюта и меню для выбранной собаки</summary>

![SelectDog.png](images/SelectDog.png)
</details>
