### Приложение-голосовалка  @author Artem Faenko

**Стек технологий:** 
- Spring Boot + Spring Data JPA + Thymeleaf + сборщик Gradle
- jQuery(ajax), HTML, CSS

**Требования для запуска проекта:** 
- Backup базы MySQL лежит в папке DB_dump (username:"root", password:"root")
- Настройки соединения в файле resources/application.yml
 
**Скриншоты:** 
- Вводим название Темы нажимаем "OK", вводим варанты для голосования, нажимаем "ОК" и "Сохранить тему".
![CC0](https://github.com/webserverby/voting-springboot-data-jpa/blob/master/screenshots/1.png)

- Тему можно удалить, просмотреть и закрыть, тогда поле меняет цвет и вычеркивается.
![CC0](https://github.com/webserverby/voting-springboot-data-jpa/blob/master/screenshots/2.png)

- Голосование происходит путем нажатия на кнопку.
![CC0](https://github.com/webserverby/voting-springboot-data-jpa/blob/master/screenshots/3.png)

- В закрытых темах кнопки для голосования являются не активными.
![CC0](https://github.com/webserverby/voting-springboot-data-jpa/blob/master/screenshots/4.png)
