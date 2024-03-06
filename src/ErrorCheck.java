import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class ErrorCheck extends DomXmlEditor {
    static Scanner scanner = new Scanner(System.in);

    public static boolean isValidDateCheck(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public static String idUpdateErrorCheck(String id) {
        Document doc = DomXmlEditor.DocumentBuilderFactory();
        Scanner scanner = new Scanner(System.in);
        try {
            NodeList taskList = doc.getElementsByTagName("Task");
            int idMax = taskList.getLength();
            int idInt = Integer.parseInt(id);
            if (idInt > idMax || idInt < 1) {
                Menu.wrongCallMenu();
                System.out.println("Введите ID");
                DomXmlEditor.updateElementValue(scanner.nextLine());
            } else {
                return id;
            }
        } catch (Exception e) {
            Menu.wrongCallMenu();
        }
        return id;
    }

    public static boolean statusDoneCheck(String statusValue) {
        boolean doneFlag = false;
        if (Objects.equals(statusValue, "done")) {
            doneFlag = true;
        }
        return doneFlag;
    }

    public static String statusErrorCheck() {
        while (true) {
            String statusValue = scanner.nextLine();
            if (Objects.equals(statusValue, "in progress")) {
                return statusValue;
            } else if (Objects.equals(statusValue, "done")) {
                return statusValue;
            } else {
                Menu.wrongCallMenu();
            }
        }
    }

    public static int numberErrorCheck() {
        while (true) {
            try {
                String number = scanner.nextLine();
                return Integer.parseInt(number);
            } catch (Exception e) {
                Menu.wrongCallMenu();
            }
        }
    }

    public static String priorityErrorCheck() {
        while (true) {
            int priorityValue = numberErrorCheck();
            if (priorityValue > 10) {
                System.out.println("Неверный ввод, выберете число от 0 до 10");
                System.out.println("-------------");
            } else {
                return String.valueOf(priorityValue);
            }
        }
    }

    public static String captionErrorCheck() {
        while (true) {
            String captionValue = scanner.nextLine();
            if (captionValue.length() > 49) {
                System.out.println("Неверный ввод, введите не более 50 символов");
                System.out.println("-------------");
            } else {
                return captionValue;
            }
        }
    }

    public static String deadlineErrorCheck() {
        while (true) {
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String deadlineValue = scanner.nextLine();
            try {
                LocalDate date1 = LocalDate.parse(formater.format(date));
                LocalDate date2 = LocalDate.parse(deadlineValue);
                if (!ErrorCheck.isValidDateCheck(deadlineValue)) {
                    System.out.println("Неверный ввод, введите дату в формате \"yyyy-MM-dd\"");
                    System.out.println("-------------");
                } else if (date2.isBefore(date1)) {
                    System.out.println("Неверный ввод, введите дату позднее текущей даты");
                    System.out.println("-------------");
                } else {
                    return deadlineValue;
                }
            } catch (Exception exc) {
                Menu.wrongCallMenu();
            }
        }
    }
}
