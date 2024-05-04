-- liquibase formatted sql

-- changeset v.aliev:1
INSERT INTO info (key_word, message) VALUES ('Start', 'Здравствуйте, {username}! Наш сервис позволяет найти себе нового четвероногого друга. Вы можете выбрать приют просто щелкнув по кнопкам ниже, а также оставить свои контакты для связи с нашими волонтерами или позвать волонтера на помощь. Для этого воспользуйтесь соответствующими пунктами меню бота.');
INSERT INTO info (key_word, message) VALUES ('StartNotContacted', 'Здравствуйте, {username}! С вами ещё не связывались? Вы оставляли следующие контактные данные для связи {usercontact}. Проверьте их правильность и скорректируйте если необходимо.');
INSERT INTO info (key_word, message) VALUES ('Error', 'Извините, что-то пошло не так. Попробуйте вернуться позже. Приносим свои извинения за предоставленные неудобства. Наши программисты уже работают над исправлением проблемы.');
INSERT INTO info (key_word, message) VALUES ('HelloVolunteer', 'Здравствуйте, {username}! Хотите сегодня поработать?');
INSERT INTO info (key_word, message) VALUES ('CallingUsers', 'Вот список новых пользователей, которые хотят связаться с волонтёром. Выберите пользователя, с которым готовы поговорить.');
INSERT INTO info (key_word, message) VALUES ('NotCallingUsers', 'Пользователям, которым нужна помощь нет.');
INSERT INTO info (key_word, message) VALUES ('CallingAdopters', 'Вот список усыновителей, которые хотят связаться с волонтёром. Выберите усыновителя, с которым готовы поговорить.');
INSERT INTO info (key_word, message) VALUES ('NotCallingAdopters', 'Усыновителей, которым нужна помощь нет.');
INSERT INTO info (key_word, message) VALUES ('UsersBecomeAdoptive', 'Список пользователей, которые хотят усыновить животное. Выберите кому усыновление уже одобрено.');
INSERT INTO info (key_word, message) VALUES ('NotUsersBecomeAdoptive', 'Новых усыновителей нет.');
INSERT INTO info (key_word, message) VALUES ('AdoptersReportCheck', 'Список усыновителей, чьи отчёты нужно проверить сегодня.');
INSERT INTO info (key_word, message) VALUES ('NotAdoptersReportCheck', 'Все отчеты на сегодня проверены');

-- changeset v.aliev:2
ALTER TABLE shelter ALTER COLUMN shelter_info TYPE varchar(512);
ALTER TABLE shelter ALTER COLUMN security_contact_detail TYPE varchar(2048);
ALTER TABLE shelter ALTER COLUMN safety_information TYPE varchar(65535);

-- changeset v.aliev:3
INSERT INTO shelter (shelter_name, shelter_info, security_contact_detail, safety_information) VALUES ('Собачий рай', 'Время работы: с 08:00 до 18:00 по будням. Адрес: ул. Собачников, дом 3.', 'Для того чтобы выписать пропуск для вашего автомобиля на территорию приюта свяжитесь с охранной по телефону: +7(123)456-78-90', 'На территории приюта запрещено курить, кроме мест, специально для этого предназначенных. Кроме того запрещается создавать громкие шумы (гудок машины, крики, ругань) - это может повлиять на наших подопечных. Некоторые собаки в нашем приюте новенькие и не приучены к людям, поэтому будьте аккуратны с ними. Не суйте руки между прутьями вольеров и не подкармливайте наших подопечных!');

-- changeset v.aliev:4
INSERT INTO shelter (shelter_name, shelter_info, security_contact_detail, safety_information) VALUES ('Мягкие лапки', 'Время работы: с 09:00 до 19:00 по будням и с 10:00 до 15:00 в субботу и в воскресенье. Адрес: Кошачий переулок, 2.', 'Для того чтобы выписать пропуск на автомобиль свяжитесь с охранной приюта по электронной почте security@lapki.cat', 'У наших кошечек довольно острые коготочки, поэтому не рекомендуем просовывать в клетки пальцы и близко к ним подходить во избежание зацепа выступающих частей одежды лапками наших кошек. Запрещено курить и быть в алкогольном и/или наркотическом опьянении.');

-- changeset v.aliev:5
INSERT INTO info (key_word, message) VALUES ('HelloAdopter', 'Здравствуйте, {username}! Надеемся у вас с вашим четвероногим другом всё хорошо!');

-- changeset v.aliev:6
INSERT INTO info (key_word, message) VALUES ('ReportIsReviewedFalse', 'Уважаемый(ая) {username}, ваш(и) отчёт(ы) скоро будут проверен(ы) волонтёрами.');
INSERT INTO info (key_word, message) VALUES ('ReportsIsReviewedTrue', 'Все отчёты на сегодня проверены.');
INSERT INTO info (key_word, message) VALUES ('ReportNotSend', 'Вы ещё не отправили отчёт сегодня.');