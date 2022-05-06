package com.banorte.ws.esb.reconciliaciones.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.banorte.ws.esb.reconciliaciones.entity.T_RECORD_REP_OIG;

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
	