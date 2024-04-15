package br.univesp.PJI1102024G02.helper;

import java.util.*;
import javax.faces.context.*;

@SuppressWarnings("unchecked")
public class JSFHelper {
    private static ExternalContext getExternalContext() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getExternalContext();
    }
    
    public static String getRequestParameter(String parameterName) {
        Map paramMap = getExternalContext().getRequestParameterMap();
        return (String) paramMap.get(parameterName);
    }
    
    public static Object getSessionAttribute(String attributeName) {
        Map attrMap = getExternalContext().getSessionMap();
        return attrMap.get(attributeName);
    }
    
    public static void setSessionAttribute(String attributeName, Object value) {
        Map attrMap = getExternalContext().getSessionMap();
        attrMap.put(attributeName, value);
    }
}