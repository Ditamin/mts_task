<h1>Парсер тарифов</h1>

Spring Boot приложения для парсинга тарифов с сайта "https://moscow.megafon.ru/tariffs/all/". 
Программа запускает сервер, который возвращает список доступных тарифов в формате json при get запросе по адресу "http://localhost:8081/tariffs".

<h3>Особенности</h3>
- Приложение написано с помощью Spring Boot.
- Для хранения данных используется docker контейнер PostgreSQL
- Для работы с бд используется JPA.
- Для парсинга страниц используется Selenium и Jsoup.

<h3>Запуск</h3>

1. Необходимо запустить docker-контейнер с базой данных PostgreSQL, с помощью команды:
```bash
  docker compose up -d
```
2. Необходимо запустить само приложение TariffParserApplication.java, например, с помощью команды:
```bash
  mvn spring-boot:run
```

<h3>Пример ответа</h3>

```json

[
  {
    "id": 3703,
    "name": "Тариф 100 ГБ",
    "description": "100 ГБ скорость 5 МегаСил на выбор",
    "price": "1300 ₽",
    "minutesCount": 0,
    "gbCount": 100,
    "url": "https://moscow.megafon.ru/tariffs/all//tarif_100gb.html"
  },
  {
    "id": 3704,
    "name": "МегаФон Kids",
    "description": "100 минут 10 ГБ Защита от рекламных звонков и SMS Блокировка нежелательного контента",
    "price": "750 ₽",
    "minutesCount": 100,
    "gbCount": 10,
    "url": "https://moscow.megafon.ru/tariffs/all//megafon_kids.html"
  }
]
```
