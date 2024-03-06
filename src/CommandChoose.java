import java.util.Objects;
import java.util.Scanner;

public class CommandChoose {
    public static void chooseMain() {
        Menu.mainMenu();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String choose = scanner.nextLine();
            if (Objects.equals(choose.toLowerCase(), "list")) {
                DomParser.listAll();
            } else if (Objects.equals(choose.toLowerCase(), "list -s new")) {
                DomParser.listNew();
            } else if (Objects.equals(choose.toLowerCase(), "list -s in_progress")) {
                DomParser.listInProgress();
            } else if (Objects.equals(choose.toLowerCase(), "list -s done")) {
                DomParser.listDone();
            } else if (Objects.equals(choose.toLowerCase(), "create")) {
                DomXmlEditor.addElement();
            } else if (Objects.equals(choose.toLowerCase(), "edit")) {
                System.out.println("Введите ID");
                DomXmlEditor.updateElementValue(scanner.nextLine());
            } else if (Objects.equals(choose.toLowerCase(), "help")) {
                Menu.mainMenu();
            } else if (Objects.equals(choose.toLowerCase(), "remove")) {
                System.out.println("Введите ID");
                DomXmlEditor.deleteElementValue(scanner.nextLine());
            } else if (Objects.equals(choose.toLowerCase(), "close")) {
                System.out.println("До скорой встречи!");
                System.exit(0);
            } else {
                Menu.wrongCallMenu();
            }
        }
    }
}
