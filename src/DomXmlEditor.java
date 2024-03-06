import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class DomXmlEditor {
    //Необходим точный путь до xml файла
    private static final String filePath = "D:/IlyaJava/javaidea/Task List/src/ToDoList.xml";
    private static final File xmlFile = new File(filePath);
    static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    static DocumentBuilder builder;


    protected static Document DocumentBuilderFactory() {

        Document doc = null;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return doc;
    }

    // Добавление новой задачи с вводом параметров (Статус по умолчанию = new, Дата выполнения = 0)
    public static void addElement() {
        Document doc = DomXmlEditor.DocumentBuilderFactory();
        try {

            Scanner scanner = new Scanner(System.in);
            NodeList taskList = doc.getElementsByTagName("Task");
            Element root = doc.getDocumentElement();
            Element task = doc.createElement("Task");
            root.appendChild(task);
            task.setAttribute("id", String.valueOf(taskList.getLength()));
            task.setAttribute("hiddenStatus", "new");
            Element caption = doc.createElement("Caption");
            Element description = doc.createElement("Description");
            Element priority = doc.createElement("Priority");
            Element deadline = doc.createElement("Deadline");
            Element status = doc.createElement("Status");
            Element complete = doc.createElement("Complete");

            System.out.println("Введите значение Caption");
            caption.appendChild(doc.createTextNode(ErrorCheck.captionErrorCheck()));


            System.out.println("Введите значение Description");
            description.appendChild(doc.createTextNode(scanner.nextLine()));

            System.out.println("Введите значение Priority");
            priority.appendChild(doc.createTextNode(ErrorCheck.priorityErrorCheck()));


            System.out.println("Введите значение Deadline");
            deadline.appendChild(doc.createTextNode(ErrorCheck.deadlineErrorCheck()));


            status.appendChild(doc.createTextNode("new"));
            complete.appendChild(doc.createTextNode("0"));

            task.appendChild(caption);
            task.appendChild(description);
            task.appendChild(priority);
            task.appendChild(deadline);
            task.appendChild(status);
            task.appendChild(complete);

            DomXmlEditor.saveChanges(doc);

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    //Добавление нового 1 элемента в каждый Task
    public static void addElementInEach(Document doc) {
        NodeList taskList = doc.getElementsByTagName("Task");
        Element task = null;
        boolean flag = false;
        //проходим по каждому элементу Task
        for (int i = 0; i < taskList.getLength(); i++) {
            task = (Element) taskList.item(i);
            NodeList taskChildList = task.getChildNodes();
            for (int j = 0; i < taskChildList.getLength(); j++) {
                Node taskChild = taskChildList.item(j);
                if (taskChild.getNodeType() == Node.ELEMENT_NODE) {
                    Element childElement = (Element) taskChild;
                    if (Objects.equals(((Element) taskChild).getTagName(), "Complete")) {
                        flag = true;
                    }
                }
                if (flag) {
                    Element completeElement = doc.createElement("Complete1");
                    completeElement.appendChild(doc.createTextNode("123"));
                    task.appendChild(completeElement);
                }
            }
        }
    }

    // Изменение полей задачи по её ID
    public static void updateElementValue(String taskId) {
        Document doc = DomXmlEditor.DocumentBuilderFactory();
        boolean doneFlag = false;
        try {
            Scanner scanner = new Scanner(System.in);
            // проходим по каждому элементу Task
            NodeList taskList = doc.getElementsByTagName("Task");
            for (int i = 0; i < taskList.getLength(); i++) {
                Node task = taskList.item(i);
                if (task.getNodeType() == Node.ELEMENT_NODE) {
                    Element taskElement = (Element) task;
                    String id = taskElement.getAttribute("id");
                    if (id.equals(ErrorCheck.idUpdateErrorCheck(taskId))) {
                        NodeList taskChildList = task.getChildNodes();
                        for (int j = 0; j < taskChildList.getLength(); j++) {
                            Node taskChild = taskChildList.item(j);
                            if (taskChild.getNodeType() == Node.ELEMENT_NODE) {
                                Element childElement = (Element) taskChild;
                                Node name = childElement.getFirstChild();
                                boolean flag = true;
                                while (flag) {
                                    if (Objects.equals(childElement.getTagName(), "Status")) {
                                        Menu.updateMenu(childElement.getTagName());
                                        int number = ErrorCheck.numberErrorCheck();
                                        if (number == 1) {
                                            System.out.println("Введите новое значение    " + (childElement.getTagName()));
                                            String statusValue = ErrorCheck.statusErrorCheck();
                                            name.setNodeValue(statusValue);
                                            doneFlag = ErrorCheck.statusDoneCheck(statusValue);
                                            flag = false;
                                        } else if (number == 2) {
                                            flag = false;
                                        } else {
                                            Menu.wrongCallMenu();

                                        }
                                    } else if (Objects.equals(childElement.getTagName(), "Complete") && doneFlag) {
                                        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                                        Date date = new Date();
                                        name.setNodeValue(formater.format(date));
                                        flag = false;
                                    } else if (Objects.equals(childElement.getTagName(), "Complete") && !doneFlag) {
                                        flag = false;
                                    } else if (Objects.equals(childElement.getTagName(), "Priority")) {
                                        Menu.updateMenu(childElement.getTagName());
                                        int number = ErrorCheck.numberErrorCheck();
                                        if (number == 1) {
                                            System.out.println("Введите новое значение    " + (childElement.getTagName()));
                                            name.setNodeValue(ErrorCheck.priorityErrorCheck());
                                            flag = false;
                                        } else if (number == 2) {
                                            flag = false;
                                        } else {
                                            Menu.wrongCallMenu();
                                        }
                                    } else if (Objects.equals(childElement.getTagName(), "Caption")) {
                                        Menu.updateMenu(childElement.getTagName());
                                        int number = ErrorCheck.numberErrorCheck();
                                        if (number == 1) {
                                            System.out.println("Введите новое значение    " + (childElement.getTagName()));
                                            name.setNodeValue(ErrorCheck.captionErrorCheck());
                                            flag = false;
                                        } else if (number == 2) {
                                            flag = false;
                                        } else {
                                            Menu.wrongCallMenu();

                                        }
                                    } else if (Objects.equals(childElement.getTagName(), "Deadline")) {
                                        Menu.updateMenu(childElement.getTagName());
                                        int number = ErrorCheck.numberErrorCheck();
                                        if (number == 1) {
                                            System.out.println("Введите новое значение    " + (childElement.getTagName()));
                                            name.setNodeValue(ErrorCheck.deadlineErrorCheck());
                                            flag = false;
                                        } else if (number == 2) {
                                            flag = false;
                                        } else {
                                            Menu.wrongCallMenu();

                                        }
                                    } else {
                                        Menu.updateMenu(childElement.getTagName());
                                        int number = ErrorCheck.numberErrorCheck();
                                        if (number == 1) {
                                            System.out.println("Введите новое значение    " + (childElement.getTagName()));
                                            name.setNodeValue(scanner.next());
                                            flag = false;
                                        } else if (number == 2) {
                                            flag = false;
                                        } else {
                                            Menu.wrongCallMenu();

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            DomXmlEditor.saveChanges(doc);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    //изменяем значение ВЫБРАННОГО элемента
    public static void updateOneElementValue(String taskId) {
        Document doc = DomXmlEditor.DocumentBuilderFactory();
        try {

            NodeList taskList = doc.getElementsByTagName("Task");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите название поля, которое хотите изменить");
            String element = scanner.nextLine();
            System.out.println("Введите новое значение поля");
            String elementValue = scanner.nextLine();
            for (int i = 0; i < taskList.getLength(); i++) {
                Node task = taskList.item(i);
                if (task.getNodeType() == Node.ELEMENT_NODE) {
                    Element taskElement = (Element) task;
                    String id = taskElement.getAttribute("id");
                    Node name = taskElement.getElementsByTagName(element).item(0).getFirstChild();
                    if (id.equals(taskId)) {
                        name.setNodeValue(elementValue);
                    }
                }
            }
            DomXmlEditor.saveChanges(doc);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    //Удаление задачи по её ID
    public static void deleteElementValue(String taskId) {
        Document doc = DomXmlEditor.DocumentBuilderFactory();
        try {
            // проходим по каждому элементу Task
            NodeList taskList = doc.getElementsByTagName("Task");
            for (int i = 0; i < taskList.getLength(); i++) {
                Node task = taskList.item(i);
                if (task.getNodeType() == Node.ELEMENT_NODE) {
                    Element taskElement = (Element) task;
                    String id = taskElement.getAttribute("id");
                    if (id.equals(taskId)) {
                        taskElement.getParentNode().removeChild(taskElement);
                    }
                }
            }
            DomXmlEditor.saveChanges(doc);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    //Записываем изменения в файл
    private static void saveChanges(Document doc) throws TransformerException {
        doc.getDocumentElement().normalize();
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(DomXmlEditor.filePath));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
        System.out.println("XML успешно изменен!");
    }

}
