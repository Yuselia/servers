# UI autotests for servers.com

UI автотесты проверяют сайт: https://portal.servers.com/

Позволяют проверить следующий функционал:

- Login, Logout;

- На странице /profile/contacts: добавление, редактирование и удаление контакта c заполнением всех полей, c выбором любой роли, отличной от Primary;

- Заказ облачного сервера;

- Частичная проверка работоспособности меню.

Автотесты запускаются в браузере GoogleChrome (не ниже v.113), убедитесь что данный браузер установлен.

# Настройка и установка

1. Убедитесь, что у вас установлена Java (обязательно нужен JDK), предпочтительно версии 1.8.

2. Убедитесь, что установлен Gradle.

3. Установите Node.js и менеджер пакетов npm (нужны для запуска отчетов в Allure).
Инструкция по установке https://nodejs.org/ru/download/package-manager/#macos
Или можно скачать установочный файл по ссылке https://nodejs.org/en/download/

4. Для генерации и просмотра результатов тестирования установите Allure.
Установите инструмент командной строки, инструкция: https://www.npmjs.com/package/allure-commandline
Команда:
`npm install -g allure-commandline --save-dev`

# Запуск тестов

1. Склонируйте репозиторий с тестами на свой компьютер.

2. Откройте командную строку и перейдите в директорию с тестами.

3. Чтобы установить все необходимые зависимости, собрать проект и запустить тесты выполните команду:
 `gradle build`

# Результаты тестов

Для отображения отчетов используется Allure.
После завершения тестов вы можете найти отчет о выполнении тестов в проекте в файле `reports/test/index.html`.
Отчет содержит информацию о результатах выполнения тестов и ошибки, если они были.
Отчет открывается в браузере с помощью команды:
`allure serve build/allure-results`


## Перезапуск упавших тестов

Эта команда запустит только те тесты, которые завершились неудачно в предыдущем запуске:
 `gradle test --rerun-tasks`