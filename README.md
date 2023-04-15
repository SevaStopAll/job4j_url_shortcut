# Проект "Url_shortcut."

## Описание проекта.

Данный проект создан в рамках тестового задания по освоению принципов RESTful архитектуры клиент-серверного приложения.

## Стек.

- **Java 17**
- **Spring Boot 2.7.10**
- **PostgreSQL 14**
- **lombok**


## Требования.

- **Java 17**
- **Maven 3.8**
- **PostgresSQL 14**

## Запуск проекта.

Запуск производится через Main /src/main/java/ru.job4.url_shortcut

## Взаимодействие с приложением.
    1. Пользователь может зарегистрировать свой сайт в системе, получая в ответ сгенерированные логин и пароль.

![image](https://user-images.githubusercontent.com/115623776/232193657-c8d47bd5-d90e-4899-96b8-3edecf405f6a.png)


    2. После авторизации (и получения токена) пользователь сможет зарегистрировать сайт по URL и получить уникальный код.
    
    ![image](https://user-images.githubusercontent.com/115623776/232193684-9a1f518f-080b-45d1-b29e-062f5d81b874.png)

    
    3. При отправке кода в ответ пользователь (даже не зарегистрированный) получит обратно URL, привязанный к коду.
    
    ![image](https://user-images.githubusercontent.com/115623776/232193703-5bf0ddd0-14e7-4452-9bfe-045d419b47ab.png)


    4. Также пользователь может получить статистику по частоте запроса сайтов по кодам. 
    ![image](https://user-images.githubusercontent.com/115623776/232193739-bd6092ab-2ca6-40ee-9f1a-8f41902aafe7.png)


## Контакты.
