### Test KODE
### Декомпозиция
Приложение содержит 2 основных экрана(***Главный*** &***Детали***) и переход между ними, также есть логика обновления данных, фильтрации и поиска.

При входе в приложение у нас возможны следующие варианты, поэтому стоит учесть обработку ошибок
![image](https://user-images.githubusercontent.com/76943234/150544322-cdd1f457-97d8-4810-9849-4df2fd2cf495.png)

> Далее для каждой задачи я буду определять примерное время её  выполнение и сложность по формуле:= Задача(время, сложность от 0 до 10)
#### Экран "Главный"
<img src="https://user-images.githubusercontent.com/76943234/150545401-bfed28dc-f687-4dee-b617-c6cee062b807.png" width="336" align="right" hspace="20">

+ Верстка
  + список пользователей (30мин, 4сл)
  + табы с названием департаментов (30 мин, 5сл)
  + меню поиска (30 мин, 5сл)
  + выбор фильтра (1 час, 7сл)
+ Логика
  + загрузка пользователей с фотографией
+ Переходы
  + при нажатие на пользователя -> экран деталей (30мин, 3сл)
  + при нажатии на иконку фильтра -> fragment filter (40мин, 6сл)
+ Особенности
  + Возможность искать пользователей (2ч, 7сл)
  + Возможность обновить данные (1ч, 6сл)
+ Обработка ошибок
  + при пустом ответе с сервера показывать соответствующую иконку

 #### Экран "Детали"
<img src="https://user-images.githubusercontent.com/76943234/150551718-ee2dadbe-2341-48d3-8773-4091ec8afa91.png" width="336" align="right" hspace="20">

+ Верстка
  + Информация о пользователи
  + звонилка
+ Логика
  + загрузка пользователя с фотографией
+ Переходы
  + при нажатие на иконку "назад" -> главный экран
+ Особенности
  + Возможность позвонить по номеру ( 2часа, 7сл)
  + Когда хотим позвонить задний экран становится мутным (2часа, 8сл)
+ Обработка ошибок
  + не предполагает

## Предположительное время выполнения в мире где отстутсвуют баги ~ 30 часов