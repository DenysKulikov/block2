package com.solvd.laba.parsers;

import com.solvd.laba.domain.Company;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class JAXBParser {
    private static final Logger LOGGER = LogManager.getLogger(JAXBParser.class);
    public static void main(String[] args) {
        File file = new File("src/main/resources/company.xml");

        try {
            JAXBContext context = JAXBContext.newInstance(Company.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Company company = (Company) unmarshaller.unmarshal(file);
            LOGGER.trace(company);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
