package ec.edu.uce.indicadores.web.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import ec.edu.uce.indicadores.commons.util.MessagesApplicacion;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatch;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

public class JsfUtil {

	public static SelectItem[] getSelectItems(List<?> entities,
			boolean selectOne) {
		int size = selectOne ? entities.size() + 1 : entities.size();
		SelectItem[] items = new SelectItem[size];
		int i = 0;
		if (selectOne) {
			items[0] = new SelectItem("", "---");
			i++;
		}
		for (Object x : entities) {
			items[i++] = new SelectItem(x, x.toString());
		}
		return items;
	}

	public static void addErrorMessage(Exception ex, String defaultMsg) {
		String msg = ex.getLocalizedMessage();
		if (msg != null && msg.length() > 0) {
			addErrorMessage(msg);
		} else {
			addErrorMessage(defaultMsg);
		}
	}

	public static void addErrorMessages(List<String> messages) {
		for (String message : messages) {
			addErrorMessage(message);
		}
	}

	public static void addErrorMessage(String msg) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}

	public static void addInfoMessage(String msg) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
		FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
	}

	public static void addWarningMessage(String msg) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg);
		FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
	}

	public static void addFatalMessage(String msg) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg);
		FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
	}

	
	public static String getRequestParameter(String key) {
		return FacesContext.getCurrentInstance().getExternalContext() .getRequestParameterMap().get(key);
	}

	public static ExternalContext getExternalContext()
	{
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	public static void redirect(String url) throws IOException
	{
		FacesContext.getCurrentInstance().getExternalContext().redirect(url);
	}
	
	public static void putObject(String key,Object value)
	{
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key,value);
	}
	
	public static Object getObject(String key)
	{
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
	}
	
	public static Object getObjectFromRequestParameter(
			String requestParameterName, Converter converter, UIComponent component) {
		String theId = JsfUtil.getRequestParameter(requestParameterName);
		return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
	}
	
	 public static HttpSession getSession() {
	        return (HttpSession)
	          FacesContext.
	          getCurrentInstance().
	          getExternalContext().
	          getSession(false);
	 }

	public static String getTypeFile(byte[] content)
	{
		String mimeType = null;
		try {		
			MagicMatch match = Magic.getMagicMatch(content);
			mimeType = match.getMimeType();
		} catch (MagicParseException e) {
			e.printStackTrace();
		} catch (MagicMatchNotFoundException e) {
			e.printStackTrace();
		} catch (MagicException e) {
			e.printStackTrace();
		}
		return mimeType;
	}
	
	public static String saveToDiskUpdload(byte[] bytefile,String fileName)
	{
		String pathDir,pathFile = null;
		File f;
		try {
			//date=String.valueOf(CalendarUtil.getYear())+"\\"+String.valueOf(CalendarUtil.getMonth())+"\\"+String.valueOf(CalendarUtil.getDay());
			
			pathDir=MessagesApplicacion.getString("com.corvustec.indicadores.path.web")+"\\images\\tmp\\upload\\";
			pathFile=pathDir+"\\"+fileName;
			
			f= new File(pathFile);
			if(f.exists())
				f.delete();
			
			f= new File(pathDir);
			if(!f.exists())
				f.mkdirs();
			FileOutputStream fos=new FileOutputStream(pathFile);
			fos.write(bytefile);
			fos.close();
			pathFile="\\images\\tmp\\upload\\"+fileName;
			pathFile=pathFile.replace('\\', '/');
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pathFile;
	}
}
