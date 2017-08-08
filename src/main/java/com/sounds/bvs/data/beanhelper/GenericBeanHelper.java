package com.sounds.bvs.data.beanhelper;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.event.FacesEvent;


public class GenericBeanHelper {

	/**
	 * @param severity
	 * @param message
	 */
	public static void showGrowlMessages(final String severity, final String message) {
		final FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = null;
		if("INFO".equals(severity)) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, severity, message);
		}else if("ERROR".equals(severity)) {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, severity, message);
		}else if("WARN".equals(severity)) {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, severity, message);
		}else if("FATAL".equals(severity)) {
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, severity, message);
		}
		context.addMessage(null, msg);
	}

	/**
	 * @param object
	 * @param ae
	 * @return
	 */
	public static Object getUIComponent(Object object, final FacesEvent facesEvent) {
		UIComponent tempComponent = facesEvent.getComponent();
		while(null != tempComponent && !(tempComponent instanceof UIData)) {
			tempComponent = tempComponent.getParent();
		}
		if(null != tempComponent && tempComponent instanceof UIData) {
			final Object tempRowData = ((UIData)tempComponent).getRowData();
			if(tempRowData instanceof Object) {
				object = (Object)tempRowData;
			}
		}
		return object;
	}
}
