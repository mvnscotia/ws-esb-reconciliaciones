package com.banorte.ws.esb.reconciliaciones.dao;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.banorte.ws.esb.reconciliaciones.entity.ObtenerInventarioFullOut;
import com.banorte.ws.esb.reconciliaciones.entity.User;


public interface ObtenerInventarioFullOutDao extends JpaRepository<ObtenerInventarioFullOut, Integer>{
	
	@Query(value = "select * from table(return_table_final(:pUsuario,:pTerminal,:p_var))", nativeQuery=true)
    List<ObtenerInventarioFullOut> getInventarioFullOut(@Param( "pUsuario" ) String pUsuario,@Param( "pTerminal" ) String pTerminal,@Param( "p_var" ) String p_var);
	
}

