package com.banorte.ws.esb.reconciliaciones.ObtenerInventario.full.dao;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.banorte.ws.esb.reconciliaciones.ObtenerInventario.full.entity.ObtenerInventarioFullOut;
import com.banorte.ws.esb.reconciliaciones.entity.User;


public interface ObtenerInventarioFullOutDao extends JpaRepository<ObtenerInventarioFullOut, Integer>{
	
	@Query(value = "SELECT REPOIGID,REPOIGNIVEL,REPOIGCADENA,REPOIGFECHAGEN FROM REP_OIG", nativeQuery=true)
    List<ObtenerInventarioFullOut> getInventarioFullOut();
	
	@Procedure(name = "Get.get_extract_reconciliaciones_test",procedureName = "get_extract_reconciliaciones_test")
	Map<String, Object> getInventarioFull(@Param("pUsuario") String pUsuario,@Param("pTerminal") String pTerminal,@Param("p_var") String p_var);
	
}

