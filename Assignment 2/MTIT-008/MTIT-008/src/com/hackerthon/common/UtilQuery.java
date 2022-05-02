package com.hackerthon.common;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class UtilQuery extends UtilCommon{

	// retrieve the id from the employeeQuery

	public static String query(String id) {

	// get logger to return the name of the entity

		final Logger logger = Logger.getLogger(UtilTransform.class.getName());
		try {

			NodeList nodeList;
			Element element = null;
			nodeList = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new File(properties.getProperty(CommonConstants.SRC_EMOPLOYEE_QUERY_XML)))
					.getElementsByTagName(CommonConstants.TAG_NAME);

			for (int x = 0; x < nodeList.getLength(); x++) {
				element = (Element) nodeList.item(x);
				if (element.getAttribute(CommonConstants.ATTRIBUTE_ID).equals(id))
					break;
			}

			return element.getTextContent().trim();
		} catch (SAXException e) {
			logger.log(Level.SEVERE, e.getMessage());
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage());
		} catch (ParserConfigurationException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		return id;
	}
}
