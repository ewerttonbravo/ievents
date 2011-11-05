package br.com.ievents.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="custom.Converter")
public class CustomValueConverter implements javax.faces.convert.Converter {

	@Override
	public Object getAsObject(FacesContext ctxt, UIComponent comp, String value) {
		if (null == value || 0 == value.length())
			throw new ConverterException("Erro na conversao");
		
		value = value.replace("R$ ", "0");
		try {
			Double customValue = new Double(value);
			return customValue;
		} catch (NumberFormatException e) {
			throw new ConverterException(e.getMessage());
		} catch (Exception ex) {
			throw new ConverterException(ex.getMessage());
		}
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent comp, Object value) {
		StringBuilder ret = new StringBuilder("R$ ");
		ret.append(value);
		return ret.toString();
	}

}
