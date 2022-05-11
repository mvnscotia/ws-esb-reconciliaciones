package com.banorte.ws.esb.reconciliaciones.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.banorte.ws.esb.reconciliaciones.entity.ObtenerInventarioFiltradoOut;
import com.banorte.ws.esb.reconciliaciones.entity.RepOig;


public interface RepOigRepository extends JpaRepository<RepOig, Long>{

	@Query(nativeQuery=true)
    List<ObtenerInventarioFiltradoOut> findFullInventory(@Param( "pUsuario" ) String pUsuario,@Param( "pTerminal" ) String pTerminal,@Param( "pVar" ) String pVar, @Param( "pClaveAplicativo" ) String pClaveAplicativo);
	
	@Query(nativeQuery=true)
    List<ObtenerInventarioFiltradoOut> findFilteredInventoryByObjectType(@Param( "pUsuario" ) String pUsuario,@Param( "pTerminal" ) String pTerminal,@Param( "pVar" ) String pVar, @Param( "pClaveAplicativo" ) String pClaveAplicativo);
	
}

