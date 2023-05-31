# UI autotests for servers.com

UI автотесты проверяют сайт: https://portal.servers.com/

Позволяют проверить следующий функционал:

- Login, Logout;

- На странице /profile/contacts: добавление, редактирование и удаление контакта c заполнением всех полей, c выбором любой роли, отличной от Primary;

- Заказ облачного сервера;

- Частичная проверка работоспособности меню.

Автотесты запускаются в браузере Firefox. Перед запуском убедитесь, что он установлен.

# Настройка и запуск тестов

1. Убедитесь, что у вас установлены Java и Gradle.

2. Склонируйте репозиторий с тестами на свой компьютер.

3. Откройте командную строку и перейдите в директорию с тестами.

4. Чтобы установить все необходимые зависимости, собрать проект и запустить тесты выполните команду:
 `gradle build`

## Результаты тестов

После завершения тестов вы можете найти отчет о выполнении тестов в проекте в файле `build/reports/test/test/index.html`.
Отчет содержит информацию о результатах выполнения тестов и ошибки, если они были.

## Перезапуск упавших тестов

Эта команда запустит только те тесты, которые завершились неудачно в предыдущем запуске:
 `gradle test --rerun-tasks`