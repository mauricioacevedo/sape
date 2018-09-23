/*
 * Created on Mar 15, 2005
 */
package com.osp.sape.data;

import com.gc.data.AplicacionDAO;
import com.osp.sape.utils.ServicioGUDE;


/** * @author Andres Aristizabal */
public interface DAOFactory {

    public UsuarioDAO getUsuarioDAO();

    public CodigosVerDAO getCodigosVerDAO();

    public TipoNodoDAO getTipoNodoDAO();

    public CabezaPruebaDAO getCabezaPruebaDAO();
    
    public SerieDAO getSerieDAO();
    
    public PermitidosDAO getPermitidosDAO();
    
    public PruebaBasicaDAO getPruebaBasicaDAO();
    
    public EventoSapeDAO getEventoSapeDAO();
    
    public ServicioIndicadoresOSSDAO getServicioIndicadoresOSSDAO();
    
    public RequerimientosColaDAO getRequerimientosColaDAO();
    
    public RutinaClienteDAO getRutinaClienteDAO();
    
    public HoraPruebaClienteDAO getHoraPruebaClienteDAO();
    
    public CableDAO getCableDAO();
    
    public RutinaArmarioDAO getRutinaArmarioDAO();
    
    public HoraPruebaArmarioDAO getHoraPruebaArmarioDAO();
    
    public RutinaCableDAO getRutinaCableDAO();
    
    public HoraPruebaCableDAO getHoraPruebaCableDAO();
    
    public HoraPruebaColaDAO getHoraPruebaColaDAO();
    
    public ReportesDAO getReportesDAO();
    
    public PruebaProgramadaDAO getPruebaProgramadaDAO();
    
    public PruebaSPPDAO getPruebaSPPDAO();
    
    public DistanciaArmariosDAO getDistanciaArmariosDAO();
    
    public CategoriasDAO getCategoriasDAO();
    
    public ProcesosSapeDAO getProcesosSapeDAO();
    
    public PruebaBasicaDAO getPruebaSiplexPRODAO();
    
    public SiplexPROAutotestDAO getSiplexPROAutotestDAO();
    
    public ServicioGUDE getServicioGUDE();
    
    public ConfiguracionADSLDAO getConfiguracionADSLDAO();
    
    public PruebaAtuDAO getPruebaAtuDAO();
    
    public PruebaEstadisticaConexionDAO getPruebaEstadisticasConexionDAO();
       
    public TipoPruebaDAO getTipoPruebaDAO();

    public PruebaPingDAO getPruebaPingDAO();
    
    public ConfiguracionEWSDDAO getConfiguracionEWSDDAO();
    
    public CodigoVerETBDAO getCodigoVerETBDAO();
    
    public AplicacionDAO getAplicacionDAO();

    public SiplexproLIDAO getSiplexproLIDAO();
    
    public RutinasDAO getRutinasDAO();
    
    public ReporteadorDAO getReporteadorDAO();
    
    public FastEpmDAO getFastEpmDAO();
    
    public IndigoEpmDAO getIndigoEpmDAO();
}
