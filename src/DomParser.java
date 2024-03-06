import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DomParser {
    private static final String filePath = "D:/IlyaJava/javaidea/Task List/src/ToDoList.xml";
    static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    //Делаем Document Builder
    private static Document DocumentBuilderFactory() {
            Document doc = null;
            try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                //Получаем документ
                File file = new File(filePath);
                doc = builder.parse(file);
                //Нормализация структуры xml файла
                doc.getDocumentElement().normalize();
            } catch (ParserConfigurationException | IOException | SAXException e) {
                throw new RuntimeException(e);
            }
            return doc;
        }

    public static void listAll() {
        Document doc = DocumentBuilderFactory();
        try {
            //Вытаскиваем все элементы xml по тегам
            NodeList taskList = doc.getElementsByTagName("Task");
            for (int i = 0; i < taskList.getLength(); i++) {
                Node task = taskList.item(i);
                if (task.getNodeType() == Node.ELEMENT_NODE) {
                    Element taskElement = (Element) task;
                    System.out.println("Task ID: " + taskElement.getAttribute("id"));
                    NodeList taskChildList = task.getChildNodes();
                    for (int j = 0; j < taskChildList.getLength(); j++) {
                        Node taskChild = taskChildList.item(j);
                        if (taskChild.getNodeType() == Node.ELEMENT_NODE) {
                            Element childElement = (Element) taskChild;
                            System.out.println("    " + ((Element) taskChild).getTagName() + ": " + ((Element) taskChild).getTextContent());
                        }
                    }
                }
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public static void listInProgress() {
        Document doc = DocumentBuilderFactory();
        try {
            //Вытаскиваем все элементы xml по тегам
            NodeList taskList = doc.getElementsByTagName("Task");
            for (int i = 0; i < taskList.getLength(); i++) {
                Node task = taskList.item(i);
                if (task.getNodeType() == Node.ELEMENT_NODE) {
                    Element taskElement = (Element) task;
                    if (taskElement.getAttribute("hiddenStatus").equals("in progress")) {
                        System.out.println("Task ID: " + taskElement.getAttribute("id"));
                        NodeList taskChildList = task.getChildNodes();
                        for (int j = 0; j < taskChildList.getLength(); j++) {
                            Node taskChild = taskChildList.item(j);
                            if (taskChild.getNodeType() == Node.ELEMENT_NODE) {
                                Element childElement = (Element) taskChild;
                                System.out.println("    " + ((Element) taskChild).getTagName() + ": " + ((Element) taskChild).getTextContent());
                            }
                        }
                    }
                }
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public static void listDone() {
        Document doc = DocumentBuilderFactory();
        try {
            //Вытаскиваем все элементы xml по тегам
            NodeList taskList = doc.getElementsByTagName("Task");
            for (int i = 0; i < taskList.getLength(); i++) {
                Node task = taskList.item(i);
                if (task.getNodeType() == Node.ELEMENT_NODE) {
                    Element taskElement = (Element) task;
                    if (taskElement.getAttribute("hiddenStatus").equals("done")) {
                        System.out.println("Task ID: " + taskElement.getAttribute("id"));
                        NodeList taskChildList = task.getChildNodes();
                        for (int j = 0; j < taskChildList.getLength(); j++) {
                            Node taskChild = taskChildList.item(j);
                            if (taskChild.getNodeType() == Node.ELEMENT_NODE) {
                                Element childElement = (Element) taskChild;
                                System.out.println("    " + ((Element) taskChild).getTagName() + ": " + ((Element) taskChild).getTextContent());
                            }
                        }
                    }
                }
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public static void listNew() {
        Document doc = DocumentBuilderFactory();
        try {
            //Вытаскиваем все элементы xml по тегам
            NodeList taskList = doc.getElementsByTagName("Task");
            for (int i = 0; i < taskList.getLength(); i++) {
                Node task = taskList.item(i);
                if (task.getNodeType() == Node.ELEMENT_NODE) {
                    Element taskElement = (Element) task;
                    if (taskElement.getAttribute("hiddenStatus").equals("new")) {
                        System.out.println("Task ID: " + taskElement.getAttribute("id"));
                        NodeList taskChildList = task.getChildNodes();
                        for (int j = 0; j < taskChildList.getLength(); j++) {
                            Node taskChild = taskChildList.item(j);
                            if (taskChild.getNodeType() == Node.ELEMENT_NODE) {
                                Element childElement = (Element) taskChild;
                                System.out.println("    " + ((Element) taskChild).getTagName() + ": " + ((Element) taskChild).getTextContent());
                            }
                        }
                    }
                }
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}