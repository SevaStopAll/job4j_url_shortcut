# Проект "Url_shortcut."

## Описание проекта.

Данный проект создан для освоения 6 принципов RESTful архитектуры клиент-серверного приложения.

Универсальный интерфейс взаимодействия. (Uniform Interface)

Запросы без состояния. (Stateless)

Поддержка кеширования. (Cacheable)

Клиент-серверная ориентация.

Поддержка слоев (Layered System)

Возможность выполнять код на стороне клиента (Code on Demand)

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
    
    
![image](https://user-images.githubusercontent.com/115623776/232193804-ad28023f-c4df-4a9d-958e-986e2a430987.png)


    
    3. При отправке кода в ответ пользователь (даже не зарегистрированный) получит обратно URL, привязанный к коду.
    
![image](https://user-images.githubusercontent.com/115623776/232193826-eeebb74c-a407-4e7c-afcc-59a72b61d3c5.png)



    4. Также пользователь может получить статистику по частоте запроса сайтов по кодам. 

![image](https://user-images.githubusercontent.com/115623776/232193830-4e5b17d8-7c6e-4d87-a9ad-36b370de0542.png)


## Контакты.
@vsevolod_prof
![@vsevolod_prof](https://camo.githubusercontent.com/6f137f6e48f123181ee64838b8aa29e5e3cf4e69a8999e7056f4df2e3331c4b9/68747470733a2f2f696d672e736869656c64732e696f2f7374617469632f76313f7374796c653d666f722d7468652d6261646765266d6573736167653d54656c656772616d26636f6c6f723d323641354534266c6f676f3d54656c656772616d266c6f676f436f6c6f723d464646464646266c6162656c3d)  
