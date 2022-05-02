package com.banorte.ws.esb.reconciliaciones.dao;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.banorte.ws.esb.reconciliaciones.entity.ObtenerInventarioFullOut;


public interface ObtenerInventarioFullOutDao extends JpaRepository<ObtenerInventarioFullOut, Long>{
	
	@Query(value = "select * from table(return_table_final2(:pUsuario,:pTerminal,:p_var,:clave_aplicativo)) ", nativeQuery=true)
	List<ObtenerInventarioFullOut> getInventarioFullOut(@Param( "pUsuario" ) String pUsuario,@Param( "pTerminal" ) String pTerminal,@Param( "p_var" ) String p_var,@Param( "clave_aplicativo" ) String clave_aplicativor);

	
}

