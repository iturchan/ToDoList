public class Menu {

    public static void mainMenu() {
        System.out.println("Добро пожаловать в \"To Do List\"");
        System.out.println("-------------------------------");
        System.out.println("Вывод задач: \n" +
                "всех: list\n" +
                "новых: list -s new\n" +
                "в работе: list -s in_progress\n" +
                "выполненных: list -s done");
        System.out.println("Создать новую задачу: create");
        System.out.println("Редактировать: edit");
        System.out.println("Удалить: remove");
        System.out.println("Чтобы в любой момент получить справку по командам введите 'help'");
        System.out.println("Чтобы выйти из программы введите 'close'");
    }

    public static void updateMenu(String tagName) {
        System.out.println("Выберете, что делать с показателем " + tagName);
        System.out.println("1. Изменить показатель");
        System.out.println("2. Оставить без изменения");
    }

    public static void wrongCallMenu() {
        System.out.println("Неверный ввод");
        System.out.println("-------------");
    }
}
