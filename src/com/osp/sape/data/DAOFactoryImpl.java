/*
 * Created on Mar 17, 2005
 */
package com.osp.sape.data;

import com.gc.data.AplicacionDAO;
import com.gc.data.AplicacionDAOImpl;
import com.osp.sape.utils.ServicioGUDE;

/** * @author Andres Aristizabal */
public class DAOFactoryImpl implements DAOFactory {


    private static DAOFactory _instancia = null;
    private UsuarioDAO _usuarioDAO;
    private CodigosVerDAO _codigosVerDAO;
    private TipoNodoDAO _tipoNodoDAO;
    private CabezaPruebaDAO _cabezaPruebaDAO;
    private SerieDAO _serieDAO;
    private PermitidosDAO _permitidosDAO;
    private PruebaBasicaDAO _pruebaBasicaDAO;
    private EventoSapeDAO _eventoSapeDAO;
    private ServicioIndicadoresOSSDAO _servicioIndicadoresOSSDAO;
    private RequerimientosColaDAO _requerimientosColaDAO;
    private RutinaClienteDAO _rutinaClienteDAO;
    private HoraPruebaClienteDAO _horaPruebaClienteDAO;
    private HoraPruebaArmarioDAO _horaPruebaArmarioDAO;
    private CableDAO _cableDAO;
    private RutinaArmarioDAO _rutinaArmarioDAO;
    private RutinaCableDAO _rutinaCableDAO;
    private HoraPruebaCableDAO _horaPruebaCableDAO;
    private HoraPruebaColaDAO _horaPruebaColaDAO;
    private ReportesDAO _reportesDAO;
    private PruebaProgramadaDAO _pruebaProgramadaDAO;
    private PruebaSPPDAO _pruebasPPDAO;
    private DistanciaArmariosDAO _distanciaArmariosDAO;
    private CategoriasDAO _categoriasDAO;
    private ProcesosSapeDAO _procesosSapeDAO;
    private PruebaBasicaDAO _pruebaSiplexPRODAO;
    private SiplexPROAutotestDAO _siplexPROAutotestDAO;
    private ServicioGUDE _servicioGUDE;
    private ConfiguracionADSLDAO _configuracionADSLDAO;
    private PruebaAtuDAO _pruebaAtuDAO;
    private PruebaEstadisticaConexionDAO _pruebaEstadisticasConexionDAO;
    private TipoPruebaDAO _tipoPruebaDAO;
    private PruebaPingDAO _pruebaPingDAO;
    private ConfiguracionEWSDDAO _configuracionEWSDDAO;
    private CodigoVerETBDAO _codigoETBDAO;
    private AplicacionDAO _aplicacionDAO; 
    private SiplexproLIDAO _siplexproLIDAO;
    private RutinasDAO _rutinasDAO;
    private ReporteadorDAO _reporteadorDAO;
    private FastEpmDAO _fastEpmDAO;
    private IndigoEpmDAO _indigoEpmDAO;
    

    public IndigoEpmDAO getIndigoEpmDAO(){
    	if(_indigoEpmDAO == null){
    		_indigoEpmDAO=new IndigoEpmDAOImpl();
    	}
    	return _indigoEpmDAO;
    }
    
    public FastEpmDAO getFastEpmDAO(){
    	if(_fastEpmDAO == null){
    		_fastEpmDAO=new FastEpmDAOImpl();
    	}
    	return _fastEpmDAO;
    }
    
    public ReporteadorDAO getReporteadorDAO(){
    	if(_reporteadorDAO == null){
    		_reporteadorDAO=new ReporteadorDAOImpl();
    	}
    	return _reporteadorDAO;
    }
    public RutinasDAO getRutinasDAO(){
    	if(_rutinasDAO==null){
    		_rutinasDAO= new RutinasDAOImpl();
    	}
    	return _rutinasDAO;
    }
    
    public SiplexproLIDAO getSiplexproLIDAO(){
    	if(_siplexproLIDAO == null){
    		_siplexproLIDAO = new SiplexproLIDAOImpl();
    	}
    	return _siplexproLIDAO;
    }
    
    public CodigoVerETBDAO getCodigoVerETBDAO() {
    	if(_codigoETBDAO == null){
    		_codigoETBDAO = new CodigoVerETBDAOImpl();
    	}
    	return _codigoETBDAO;
    }
    
    public ConfiguracionEWSDDAO getConfiguracionEWSDDAO(){
    	if(_configuracionEWSDDAO == null){
    		_configuracionEWSDDAO = new ConfiguracionEWSDDAOImpl();
    	}
    	return _configuracionEWSDDAO;
    	
    }
    
    public TipoPruebaDAO getTipoPruebaDAO(){
    	
    	if(_tipoPruebaDAO == null){
    		_tipoPruebaDAO = new TipoPruebaDAOImpl();
    	}
    	return _tipoPruebaDAO; 
    }

    public PruebaAtuDAO getPruebaAtuDAO(){
    	if(_pruebaAtuDAO == null){
    		_pruebaAtuDAO = new PruebaAtuDAOImpl();
    	}
    	return _pruebaAtuDAO;
    }
    
	public ConfiguracionADSLDAO getConfiguracionADSLDAO() {
		
		if(_configuracionADSLDAO == null){
			_configuracionADSLDAO = new ConfiguracionADSLDAOImpl();
		}
		return _configuracionADSLDAO;
	}
    
    public ServicioGUDE getServicioGUDE(){
    	
    	if(_servicioGUDE == null){
    		_servicioGUDE = new ServicioGUDE();
    	}
    	return _servicioGUDE;
    }
    
    public SiplexPROAutotestDAO getSiplexPROAutotestDAO(){
    	if(_siplexPROAutotestDAO == null){
    		_siplexPROAutotestDAO = new SiplexPROAutotestDAOImpl();
    	}
    	return _siplexPROAutotestDAO;
    }
    
    public PruebaBasicaDAO getPruebaSiplexPRODAO() {

        if(_pruebaSiplexPRODAO == null) {
        	_pruebaSiplexPRODAO = new PruebaSiplexPRODAOImpl();
          }
         return _pruebaSiplexPRODAO;
    }          

    
    public static DAOFactory getInstance() {
        if (_instancia == null) _instancia = new DAOFactoryImpl();
        return _instancia;
    }
    
    public ProcesosSapeDAO getProcesosSapeDAO(){
    	if(_procesosSapeDAO == null) {
           	_procesosSapeDAO = new ProcesosSapeDAOImpl();
   	    }
   	    return _procesosSapeDAO;
    }
    
    public CategoriasDAO getCategoriasDAO() {
        if(_categoriasDAO == null) {
        	_categoriasDAO = new CategoriasDAOImpl();
	    }
	    return _categoriasDAO;
    }
    
    public DistanciaArmariosDAO getDistanciaArmariosDAO() {
        if(_distanciaArmariosDAO == null) {
        	_distanciaArmariosDAO = new DistanciaArmariosDAOImpl();
	    }
	    return _distanciaArmariosDAO;
    }
    
    public PruebaSPPDAO getPruebaSPPDAO() {
        if(_pruebasPPDAO == null) {
        	_pruebasPPDAO = new PruebaSPPDAOImpl();
	    }
	    return _pruebasPPDAO;
    }
    
    public PruebaProgramadaDAO getPruebaProgramadaDAO(){
        if(_pruebaProgramadaDAO == null) {
            _pruebaProgramadaDAO = new PruebaProgramadaDAOImpl();
	    }
	    return _pruebaProgramadaDAO;
    }
    
    public ReportesDAO getReportesDAO(){
        if(_reportesDAO == null) {
            _reportesDAO = new ReportesDAOImpl();
	    }
	    return _reportesDAO;
    }
    
    public HoraPruebaColaDAO getHoraPruebaColaDAO(){
        if(_horaPruebaColaDAO == null) {
	        _horaPruebaColaDAO = new HoraPruebaColaDAOImpl();
	    }
	    return _horaPruebaColaDAO;
    }
    
    public HoraPruebaCableDAO getHoraPruebaCableDAO(){
        if(_horaPruebaCableDAO == null) {
	        _horaPruebaCableDAO = new HoraPruebaCableDAOImpl();
	    }
	    return _horaPruebaCableDAO;
    }
    
    public RutinaCableDAO getRutinaCableDAO(){
        if(_rutinaCableDAO == null) {
            _rutinaCableDAO = new RutinaCableDAOImpl();
	    }
	    return _rutinaCableDAO;
    }
    
    public HoraPruebaArmarioDAO getHoraPruebaArmarioDAO(){
        if(_horaPruebaArmarioDAO == null) {
	        _horaPruebaArmarioDAO = new HoraPruebaArmarioDAOImpl();
	    }
	    return _horaPruebaArmarioDAO;
    }
    
    public RutinaArmarioDAO getRutinaArmarioDAO(){
        if(_rutinaArmarioDAO == null) {
            _rutinaArmarioDAO = new RutinaArmarioDAOImpl();
	    }
	    return _rutinaArmarioDAO;
    }
    
    public CableDAO getCableDAO(){
        if(_cableDAO == null) {
	        _cableDAO = new CableDAOImpl();
	    }
	    return _cableDAO;
    }
    
    public HoraPruebaClienteDAO getHoraPruebaClienteDAO(){
        if(_horaPruebaClienteDAO == null) {
	        _horaPruebaClienteDAO = new HoraPruebaClienteDAOImpl();
	    }
	    return _horaPruebaClienteDAO;
    }
    
    public RutinaClienteDAO getRutinaClienteDAO() {
        if(_rutinaClienteDAO == null) {
            	_rutinaClienteDAO = new RutinaClienteDAOImpl();
        }
        return _rutinaClienteDAO;
    }
    
    
    public UsuarioDAO getUsuarioDAO() {
        if(_usuarioDAO == null) {
            	_usuarioDAO = new UsuarioDAOImpl();
        }
        return _usuarioDAO;
    }


    public CodigosVerDAO getCodigosVerDAO() {
        if(_codigosVerDAO == null){
            _codigosVerDAO = new CodigosVerDAOImpl();
        }
        return _codigosVerDAO;
    }


    public TipoNodoDAO getTipoNodoDAO() {
        if(_tipoNodoDAO == null) {
            _tipoNodoDAO = new TipoNodoDAOImpl();
          }
         return _tipoNodoDAO;
        }

    public CabezaPruebaDAO getCabezaPruebaDAO() {
        if(_cabezaPruebaDAO == null) {
            _cabezaPruebaDAO = new CabezaPruebaDAOImpl();
          }
         return _cabezaPruebaDAO;
    }
        
    
    public SerieDAO getSerieDAO(){
        if(_serieDAO == null) {
            _serieDAO = new SerieDAOImpl();
          }
         return _serieDAO;
    }
    
    public PermitidosDAO getPermitidosDAO(){
        if(_permitidosDAO == null) {
            _permitidosDAO = new PermitidosDAOImpl();
          }
         return _permitidosDAO;
    }


    public PruebaBasicaDAO getPruebaBasicaDAO() {

        if(_pruebaBasicaDAO == null) {
            _pruebaBasicaDAO = new PruebaBasicaDAOImpl();
          }
         return _pruebaBasicaDAO;
    }          
    
    public EventoSapeDAO getEventoSapeDAO() {

        if(_eventoSapeDAO == null) {
            _eventoSapeDAO = new EventoSapeDAOImpl();
          }
         return _eventoSapeDAO;
    }

    public ServicioIndicadoresOSSDAO getServicioIndicadoresOSSDAO() {

        if(_servicioIndicadoresOSSDAO == null) {
            _servicioIndicadoresOSSDAO = new ServicioIndicadoresOSSDAOImpl();
          }
         return _servicioIndicadoresOSSDAO;        
    }          

    public RequerimientosColaDAO getRequerimientosColaDAO() {
        if(_requerimientosColaDAO == null) {
            _requerimientosColaDAO = new RequerimientosColaDAOImpl();
        }
        return _requerimientosColaDAO;
    }
    
    public PruebaEstadisticaConexionDAO getPruebaEstadisticasConexionDAO() {
    	if (_pruebaEstadisticasConexionDAO == null) {
    		_pruebaEstadisticasConexionDAO = new PruebaEstadisticaConexionDAOImpl();
    	}
    	return _pruebaEstadisticasConexionDAO;
    }
    
    public PruebaPingDAO getPruebaPingDAO() {
    	if (_pruebaPingDAO == null) {
    		_pruebaPingDAO = new PruebaPingDAOImpl();
    	}
    	return _pruebaPingDAO;
    }
    
    public AplicacionDAO getAplicacionDAO() {
    	if (_aplicacionDAO == null) _aplicacionDAO = new AplicacionDAOImpl();
    	return _aplicacionDAO;
    }
 }    
    