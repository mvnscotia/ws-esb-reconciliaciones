package com.banorte.ws.esb.reconciliaciones.util;

import org.springframework.stereotype.Component;

@Component
public class Props {
	
	
	public String find_coincidence(String to_find)
	{
		String found="";
		switch(to_find)
		{
			case "perfil": 
							found="N1";break;
			case "transaccion": 
							found="N2";break;
			case "relacion-perfil-transaccion": 
							found="R1";break;
			case "relacion-usuario-perfil": 
							found="R3";break;
			default:found="not_found";break;
		}
		
		return found;
	}
	
	
}
	