package com.corvustec.commons.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ApplicationUtil {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationUtil.class);
	
	public static String saveToDiskBin(byte[] bytefile)
	{
		String file,path,date,pathAbs,pathSave = null;
		try {
			file= UUID.randomUUID().toString();
			//path=FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
			date=String.valueOf(CalendarUtil.getYear())+"\\"+String.valueOf(CalendarUtil.getMonth())+"\\"+String.valueOf(CalendarUtil.getDay());
			pathSave="\\SILSAG\\"+date;
			path=System.getProperty("jboss.server.data.dir")+"\\SILSAG\\"+date;
			File f= new File(path);
			if(!f.exists())
				f.mkdirs();
			pathAbs=path+"\\"+file+".bin";//+mime;
			pathSave=pathSave+"\\"+file+".bin";//+mime;
			FileOutputStream fos=new FileOutputStream(pathAbs);
			fos.write(bytefile);
			fos.close();
		} catch (FileNotFoundException e) {
			logger.info("FileNotFoundException {}",e.toString());
		} catch (IOException e) {
			logger.info("FileNotFoundException {}",e.toString());
		}
		return pathSave;
	}
	
	public static String saveToDisk(byte[] bytefile,String fileName)
	{
		String pathDir,pathFile = null;
		File f;
		try {
			//date=String.valueOf(CalendarUtil.getYear())+"\\"+String.valueOf(CalendarUtil.getMonth())+"\\"+String.valueOf(CalendarUtil.getDay());
			
			pathDir=MessagesApplicacion.getString("com.corvustec.redxxi.path.web")+"\\images\\tmp\\upload\\";
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
			if(fileName.split("\\.")[1].equals("pdf"))
				pathFile="\\images\\tmp\\upload\\"+fileName;
			else
				pathFile="\\images\\tmp\\upload\\"+fileName;
			pathFile=pathFile.replace('\\', '/');
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pathFile;
	}
	
	
	public static void deletefile(String fileName)
	{
		String pathDir,pathFile = null;
		File f;

		//date=String.valueOf(CalendarUtil.getYear())+"\\"+String.valueOf(CalendarUtil.getMonth())+"\\"+String.valueOf(CalendarUtil.getDay());
		
		pathDir=MessagesApplicacion.getString("com.corvustec.redxxi.path.web")+"\\images\\tmp\\";
		pathFile=pathDir+"\\"+fileName;
		
		f= new File(pathFile);
		if(f.exists())
		{
			f.delete();
		}	
	}
	
	
	public static boolean validarFecha(String fecha) 
	{  
		if (fecha == null)  
		return false;  
		  
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //a�o-mes-dia  
		  
		if (fecha.trim().length() != dateFormat.toPattern().length())  
			return false;  
		dateFormat.setLenient(false);  		  
		try {
			dateFormat.parse(fecha.trim());
			
		} catch (ParseException e) {
			return false;
		}  	
		return true;
	}
	
	
	public static Boolean exitsFile(String fileName)
	{
		String pathDir=MessagesApplicacion.getString("com.corvustec.redxxi.path.web")+"\\images\\tmp\\upload\\"+fileName;
		File file =new File(pathDir);
		if(file.exists())
			return Boolean.TRUE;
		else
			return Boolean.FALSE;
	}
	
	public static String getPathFile(String fileName,byte[] fileArray)
	{
		if(exitsFile(fileName))
			return "\\images\\tmp\\upload\\"+fileName;
		else
		{
			saveToDisk(fileArray, fileName);
			return "\\images\\tmp\\upload\\"+fileName;
		}
	}
}
