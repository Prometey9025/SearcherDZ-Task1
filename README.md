Задание:
Имеется корневая папка. В этой папке могут находиться текстовые файлы, а также другие папки. 
В других папках также могут находится текстовые файлы и папки (уровень вложенности может оказаться любым). 
Найти все текстовые файлы, отсортировать их по имени и склеить содержимое в один текстовый файл.

Ход выполнения задания:
1) Я создаю класс Searcher c 3 параметрами:  directory - директория для поиска файлов txt, filesList - лист всех найденных файлов, 
resultPath - путь, по которому будет разполагаться итоговый файл с найденными txt файлами
2) Создаю метод input для ввода директории из консоли и для проверки корректности ввода
3) Создаю метод search для поиска файлов в директории
4) Создаю метод sort для сортировки файлов по названию
5) Создаю метод write для записи найденных файлов в итоговый файл

Для запуска программы нужно создать обычный проект по Java в среде разработки и скачать или скопировать код с репозитория 
