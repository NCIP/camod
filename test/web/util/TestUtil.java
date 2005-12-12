package web.util;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.service.impl.AnimalModelManagerSingleton;
import gov.nih.nci.camod.util.GUIDGenerator;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.security.junk.RandomIntGenerator;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import com.meterware.httpunit.WebForm;

public class TestUtil {

	static private RandomIntGenerator ourRandomIntGenerator = new RandomIntGenerator(10, 20);

	public static void moveModelToEditedApproved(String inModelName) throws Exception {
		AnimalModel theQBEAnimalModel = new AnimalModel();

		theQBEAnimalModel.setModelDescriptor(inModelName);

		List theModelList = Search.query(theQBEAnimalModel);

		if (theModelList.size() > 1) {
			throw new IllegalArgumentException("More than one model matched the name: " + inModelName);
		} else if (theModelList.size() == 0) {
			throw new IllegalArgumentException("No models matched the name: " + inModelName);
		} else {
			AnimalModel theAnimalModel = (AnimalModel) theModelList.get(0);

			theAnimalModel.setState("Edited-approved");

			AnimalModelManagerSingleton.instance().save(theAnimalModel);

		}
	}

	public static void setRandomValues(Object inDataObject, WebForm inForm, boolean setOtherValues, List inParamsToIgnore) throws Exception {

		Map theBeanProps = PropertyUtils.describe(inDataObject);
		Iterator theProperties = theBeanProps.entrySet().iterator();

		// loop thru bean properties
		while (theProperties.hasNext()) {

			Map.Entry theEntry = (Map.Entry) theProperties.next();

			if (theEntry.getKey() != null) {

				String thePropertyName = theEntry.getKey().toString();
				PropertyDescriptor thePropertyDescriptor = PropertyUtils.getPropertyDescriptor(inDataObject,
						thePropertyName);

				System.out.println("Property name: " + thePropertyName);

				Object thePropertyValue = theEntry.getValue();

				// Only override non-set values
				if (thePropertyValue == null && !inParamsToIgnore.contains(thePropertyName)) {
					// check if property is a collection
					if (thePropertyDescriptor.getPropertyType().getName().equals("java.lang.String")) {

						String[] theOptions = inForm.getOptionValues(thePropertyName);

						if (theOptions.length > 0) {
							if (Arrays.asList(theOptions).contains(Constants.Dropdowns.OTHER_OPTION)
									&& setOtherValues == true) {
								BeanUtils.setProperty(inDataObject, thePropertyName, Constants.Dropdowns.OTHER_OPTION);
							} else {
								BeanUtils.setProperty(inDataObject, thePropertyName, theOptions[theOptions.length - 1]);
							}
						} else {

							// If we're not setting the other option, skip these
							if (thePropertyName.indexOf("other") == -1 || setOtherValues == true) {
								BeanUtils.setProperty(inDataObject, thePropertyName, GUIDGenerator.getInstance()
										.genNewGuid());
							}
						}
					} else if (thePropertyDescriptor.getPropertyType().getName().equals("java.lang.Long")) {
						BeanUtils.setProperty(inDataObject, thePropertyName, new Long(ourRandomIntGenerator.draw()));
					} else if (thePropertyDescriptor.getPropertyType().getName().equals("java.lang.Double")) {
						BeanUtils.setProperty(inDataObject, thePropertyName, new Double(ourRandomIntGenerator.draw()));
					} else {
						// Ignore for now
					}
				}
			}
		}
	}

	public static void setValuesOnForm(Object inDataObject, WebForm inForm) throws Exception {

		Map theBeanProps = PropertyUtils.describe(inDataObject);
		Iterator theProperties = theBeanProps.entrySet().iterator();

		// loop thru bean properties
		while (theProperties.hasNext()) {

			Map.Entry theEntry = (Map.Entry) theProperties.next();

			if (theEntry.getKey() != null) {
				String thePropertyName = theEntry.getKey().toString();
				Object thePropertyValue = theEntry.getValue();

				if (thePropertyValue != null && inForm.hasParameterNamed(thePropertyName)) {

					System.out.println("Setting value: " + thePropertyName + " to " + thePropertyValue.toString());
					inForm.setParameter(thePropertyName, thePropertyValue.toString());
				}
			}
		}
	}
}
