package com.corvustec.signture.main;

import java.io.File;

import com.corvustec.signature.xml.Signature;
import com.corvustec.signature.xml.exception.SignatureException;

public class Process {

	public static void main(String[] args) {

		File xmlFile=new File("/home/fernando/FacturacionElectronica/clientes/Prueba/firmado/0710201301179125123700110010650000000107791051216.xml");
		try {
			Signature.Xml(xmlFile, "/home/fernando/FacturacionElectronica/clientes/Prueba/Firma/francisco_arturo_velez_rojas.p12","ulygGd+Hh/4di7WinfA1NA==");
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
