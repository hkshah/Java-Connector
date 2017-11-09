package com.rapidvalue.master.dao.util;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.rapidvalue.master.dao.constants.ConnectorConstants;
import com.rapidvalue.master.dao.exception.DaoException;
import com.rapidvalue.master.dao.model.ProcedureParam;

/**
 * @author rohit.nagesh
 * @since Apr 28, 2016
 */
public class ProcedureReader {

	private static final Logger LOG = Logger.getLogger(ProcedureReader.class);
	private static ProcedureReader instance = null;
	private static Document procXmlDoc = null;

	/**
	 * Private constructor.
	 */
	private ProcedureReader() {
		if (procXmlDoc == null) {
			initProcedureDoc();
		}
	}

	public static synchronized ProcedureReader getInstance() {
		if (instance == null) {
			instance = new ProcedureReader();
		}
		return instance;
	}

	/**
	 * Read procedure xml and get the input/output parameters
	 * 
	 * @param procedureName
	 * @return
	 * @throws DaoException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws XPathExpressionException
	 */
	public List<ProcedureParam> getProcedureParams(final String procedureName)
			throws DaoException {
		NodeList nodeList = readProcedureXML(procedureName);
		List<ProcedureParam> paramList = new ArrayList<>();
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				// Get procedure parameters
				Element el = (Element) nodeList.item(i);
				ProcedureParam param = new ProcedureParam();
				param.setValue(el.getFirstChild().getNodeValue());
				String mode = el.getAttribute(ConnectorConstants.PARAM_MODE);
				if (StringUtils.isNotEmpty(mode)) {
					param.setMode(mode);
				}
				String jdbcType = el
						.getAttribute(ConnectorConstants.PARAM_JDBC_TYPE);
				if (StringUtils.isNotEmpty(jdbcType)) {
					param.setJdbcType(jdbcType);
				}
				String jdbcTypeName = el
						.getAttribute(ConnectorConstants.PARAM_JDBC_TYPE_NAME);
				if (StringUtils.isNotEmpty(jdbcTypeName)) {
					param.setJdbcTypeName(jdbcTypeName);
				}
				String jdbcTypeArray = el
						.getAttribute(ConnectorConstants.PARAM_JDBC_STRUCT);
				if (StringUtils.isNotEmpty(jdbcTypeArray)) {
					param.setjdbcStruct(jdbcTypeArray);
				}
				String typeHandler = el
						.getAttribute(ConnectorConstants.PARAM_TYPE_HANDLER);
				if (StringUtils.isNotEmpty(typeHandler)) {
					param.setTypeHandler(typeHandler);
				}
				// Add to the map
				paramList.add(param);
			}
		}
		return paramList;
	}

	/**
	 * Read the procedure xml and get the node list
	 * 
	 * @param procedureName
	 * @return
	 * @throws DaoException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws XPathExpressionException
	 */
	private NodeList readProcedureXML(final String procedureName)
			throws DaoException {

		Object exprResult = null;
		try {
			XPath xpath = XPathFactory.newInstance().newXPath();
			XPathExpression expr = xpath.compile("//procedure[@name=\""
					+ procedureName + "\"]/params");
			exprResult = expr.evaluate(procXmlDoc, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			LOG.error("Failed to execute procedure::\n" + e);
			throw new DaoException(500, "Failed to execute procedure.", e);
		}
		return (NodeList) exprResult;
	}

	private void initProcedureDoc() {
		try {
			URL url = this.getClass().getClassLoader()
					.getResource("procedure.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			procXmlDoc = builder.parse(url.toString());
		} catch (Exception e) {
			LOG.error("Failed to read procedure.xml::\n" + e);
		}
	}

}
