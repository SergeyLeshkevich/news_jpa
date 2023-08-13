# news_jpa
В ходе разработки приложения, реализующего функционал новостного веб сайта использовался следующий technological stack:
• Серверные технологии: 
o Язык программирования:Java 17 
o Фреймворк: Spring boot 2.7.14 
o Система автоматической сборки: Maven 4.0.0 
o База данных: MySQL

• Клиентские технологии: 
o Языки разметки HTML (Thymeleaf)
o Некоторые стили CSS

Были продемонстрированы следующие умения:
• Строить модели (выделять классы, интерфейсы, их связи), разделять функционал между ними. 
• Работа с БД
• Использовать паттерны проектирования(MVC).
• Работа с фреймворком Spring:
o Spring Core
o Spring Security
o Spring Data JPA
o Spring Boot

• Был реализован следующий функционал:
На index странице:
o Отображение новостей в по дате их добавления
o Отображение трех новостей в по их рейтингу
o Возможность ознакомиться с выбранной новостной статьей подробнее на отдельной страницей и проголосовать за нее (для аутотенфицированных пользователей)

На странице all:
o Отображение всех новостей в отдельной таблице
o Возможность поиски новости по названию

При помощи Spring Security были реализованны аутотенфиция и авторизация пользователей (user, author, admin). 
В зависимости от роли доступны следующие возможности:
• Для неавтотенфицированных пользователей:
o Открыт доступ к главной странице и странице аутотенфикации и регистрации.
o Аутотенфикация и регистрация.

• Для всех аутотенфицированных пользователей:
o Возможность ознакомиться с новостью подробнее
o Проголосовать за новость
o Возможность поиски новости по названию
o Доступ к личному кабинету и возможность редактирования своих данных в нем

• Для пользователей с ролью author:
o Добавить новостную статью

• Для пользователей с ролью admin:
o Редактировать новостную статью
o Удалить новостную статью
o Зарегистрировать нового пользователя с ролью author




