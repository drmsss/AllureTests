Feature: Авторизация на сайте

  Scenario: Авторизация пользователя в Jira
    Given Пользователь перешел по ссылке "https://edujira.ifellow.ru/"
    Then  Пользователь авторизовался с логином "kukhar" и паролем "Qwerty123"

