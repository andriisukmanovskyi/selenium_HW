package com.epam.lab.parser.stax;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class UsersLoginDataSTAXParser {

    private static String[][] usersLoginData;
    private static int userIndex = 0;

    public static String[][] parseXML(File file) {
        usersLoginData = new String[5][2];
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = null;
        try {
            eventReader = factory.createXMLEventReader(new FileReader(file));
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    handleStartElement(eventReader, event);
                } else if (event.isEndElement()) {
                    handleEndElement(event);
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return usersLoginData;
    }

    private static void handleEndElement(XMLEvent event) {
        if (event.asEndElement().getName().getLocalPart().equals("user"))
            userIndex++;
    }

    private static void handleStartElement(XMLEventReader eventReader, XMLEvent event) throws XMLStreamException {
        StartElement startElement = event.asStartElement();
        String startElementName = startElement.getName().getLocalPart();
        if (startElementName.equals("userName")) {
            event = eventReader.nextEvent();
            usersLoginData[userIndex][0] = event.asCharacters().getData();
        } else if (startElementName.equals("password")) {
            event = eventReader.nextEvent();
            usersLoginData[userIndex][1] = event.asCharacters().getData();
        }
    }
}