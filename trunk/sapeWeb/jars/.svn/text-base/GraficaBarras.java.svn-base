

import java.awt.*;
import java.io.PrintStream;
import java.util.StringTokenizer;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.labels.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.text.TextUtilities;
import org.jfree.ui.*;
import org.jfree.util.Log;
import org.jfree.util.PrintStreamLogTarget;



public class GraficaBarras extends JApplet
{

    public void init()
    {
        //SI EXISTE EL PARAMETRO, SE GENERARA UNA GRAFICA PARA TSTLI
        //KE FUE SOLICITADA DESDE EL JSP /sape/indicadores/inicioIndicadores.jsp
        String graficaTSTLI = getParameter("TSTLI");
        
        if(graficaTSTLI != null){
            if(graficaTSTLI.equals("true")){
                graficaTSTLI();
                return;
            }
        }
        
        String PENDI = getParameter("PENDI");
        String CUMPL = getParameter("CUMPL");
        StringTokenizer tokenP = new StringTokenizer(PENDI, "*");
        StringTokenizer tokenC = new StringTokenizer(CUMPL, "*");
        String titulo = null;//token.nextToken();
        String limite=null;
        String s  = "PENDIENTES";
        String s2 = "CUMPLIDAS";
        
        String ejeX = null;
        String ejeY = null;
        
        tokenP.nextToken();
        tokenC.nextToken();
        
        if(PENDI==null && CUMPL == null){
            showStatus("ERROR: FALTAN PARAMETROS DE INICIALIZACION");
            return;
        }
        
        if(PENDI.equals("VACIO")){
            titulo="COLAS CUMPLIDAS";
            ejeX = tokenC.nextToken();
            ejeY = tokenC.nextToken();
        }else if(CUMPL.equals("VACIO")){
            titulo="COLAS PENDIENTES";
            ejeX = tokenP.nextToken();
            ejeY = tokenP.nextToken();            
        }else if(PENDI.equals("1-2")){//es una sola grafica para una cola
            
            limite=getParameter("limite");
            showStatus("TIENE ALARMA???="+limite);
            /*try {
                Thread.sleep(3000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();}*/         
            ejeX = tokenC.nextToken();
            ejeY = tokenC.nextToken();            
        }else{
            titulo="COLAS CUMPLIDAS Y PENDIENTES";
            System.out.println("SON CUMPLIDAS Y PENDIENTES LAS DOS");
            showStatus("LAS DOS!!!!!");
            ejeX = tokenC.nextToken();
            ejeY = tokenC.nextToken();
            tokenP.nextToken();//PARA KE KEDEN UBICADOS EN EL MISMO PUNTO LOS TOKENS
            tokenP.nextToken();//            
        }
        
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

//      COLAS PENDIENTES.*ESTADO*CANTIDAD*ANALI*17*REPLI*893*GRANC*2*TSTLI*1*
        
        try
        {
            String COLA =null;
            String valor =null;
            
            if(PENDI.equals("1-2")){
                
                COLA=tokenC.nextToken();
                valor=tokenC.nextToken();
                dataset.addValue(new Double(valor),"PENDIENTE",COLA);
                COLA=tokenC.nextToken();
                valor=tokenC.nextToken();
                dataset.addValue(new Double(valor),"CUMPLIDO",COLA);
                COLA=null;
                valor=null;                
            }else{
            
	            while(tokenP.hasMoreElements()){
	                COLA=tokenP.nextToken();
	                valor=tokenP.nextToken();
	                dataset.addValue(new Double(valor),"PENDIENTE",COLA);
	            }
	            COLA=null;
	            valor=null;
	            while(tokenC.hasMoreElements()){
	                COLA=tokenC.nextToken();
	                valor=tokenC.nextToken();
	                dataset.addValue(new Double(valor),"CUMPLIDOS",COLA);
	            }
            }
//            String caption;
//            for(; token.hasMoreElements(); dataset.addValue(new Double(token.nextToken()), caption, ""))
//                caption = token.nextToken();

        }
        catch(NumberFormatException e)
        {
            System.out.println("Error en un par\341metro num\351rico");
            System.out.println("APPLET NO CARGADO");
            return;
        }
        System.out.println("YA VA PA LLA!!!!!");
        if(limite == null){
            PENDI ="CUALKIER COSA";
        }else if(limite.equals("")){//PARA CONTROLAR QUE ESTA COLA NO TENGA ALARMA
            PENDI ="CUALKIER COSA";
        }
        
        JFreeChart jfreechart = ChartFactory.createBarChart(titulo, ejeX, ejeY, dataset, PlotOrientation.VERTICAL, false, true, false);
        
        
        /*JFreeChart chart = itulo, ejeX, ejeY, dataset, true, true, false);
        */
        Color c1=null,c2=null;
        try{
            c1=Color.decode(getParameter("color1"));
            c2=Color.decode(getParameter("color2"));
        }catch(Exception err){
            c1=null;
            c2=null;
        }
        
       
        if(c1 != null && c2 != null){
            jfreechart.setBackgroundPaint(new GradientPaint(0.0F, 0.0F, c1, 70F, 0.0F, c2, true));
        }else {
            System.out.println("no kogio los de por default");
            jfreechart.setBackgroundPaint(new GradientPaint(0.0F, 0.0F, Color.WHITE, 70F, 0.0F, new Color(211, 211, 211), true));
        }
        CategoryPlot categoryplot = jfreechart.getCategoryPlot();
        categoryplot.setBackgroundPaint(new Color(220,220,255));
        categoryplot.setDomainGridlinePaint(Color.white);
        categoryplot.setRangeGridlinePaint(Color.white);
        
        if(PENDI.equals("1-2") && limite.equals("") != true){
	        IntervalMarker intervalmarker = new IntervalMarker(Integer.parseInt(limite), Integer.parseInt(limite)+1);
	        intervalmarker.setLabel("Limite de DESBORDE");
	        intervalmarker.setLabelFont(new Font("System", 2, 15));
	        intervalmarker.setLabelAnchor(RectangleAnchor.LEFT);
	        intervalmarker.setLabelTextAnchor(TextAnchor.CENTER_LEFT);
	        intervalmarker.setPaint(new Color(22, 122, 155, 128));
	        categoryplot.addRangeMarker(intervalmarker, Layer.BACKGROUND);
        }
        NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer barrenderer = (BarRenderer)categoryplot.getRenderer();
        barrenderer.setDrawBarOutline(true);
        barrenderer.setItemMargin(0.10000000000000001D);
        GradientPaint gradientpaint = new GradientPaint(0.0F, 0.0F, Color.red, 0.0F, 0.0F, Color.white);
        GradientPaint gradientpaint1 = new GradientPaint(0.0F, 0.0F, Color.blue, 0.0F, 0.0F, Color.white);
        //GradientPaint gradientpaint2 = new GradientPaint(0.0F, 0.0F, Color.red, 0.0F, 0.0F, Color.lightGray);
        
        if(CUMPL.equals("VACIO")){
            barrenderer.setSeriesPaint(0, gradientpaint);
            barrenderer.setSeriesPaint(1, gradientpaint1);            
        }else if(PENDI.equals("1-2")){
            barrenderer.setSeriesPaint(0, gradientpaint);
            barrenderer.setSeriesPaint(1, gradientpaint1);
        }else{
            barrenderer.setSeriesPaint(0, gradientpaint1);
            barrenderer.setSeriesPaint(1, gradientpaint);
        }
        //barrenderer.setSeriesPaint(2, gradientpaint2);
        //showStatus("ANTES DE SETEAR LA BARRA");
        
        barrenderer.setLabelGenerator( new LabelGenerator());       
        
        barrenderer.setItemLabelsVisible(true);
        ItemLabelPosition itemlabelposition = new ItemLabelPosition(ItemLabelAnchor.INSIDE12, TextAnchor.CENTER_RIGHT, TextAnchor.CENTER_RIGHT, -1.5707963267948966D);
        barrenderer.setPositiveItemLabelPosition(itemlabelposition);
        ItemLabelPosition itemlabelposition1 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.CENTER_LEFT, TextAnchor.CENTER_LEFT, -1.5707963267948966D);
        barrenderer.setPositiveItemLabelPositionFallback(itemlabelposition1);
        CategoryAxis categoryaxis = categoryplot.getDomainAxis();
        categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        ChartPanel chartPanel= new ChartPanel(jfreechart);
        Dimension dd=new Dimension(800,400);
        chartPanel.setPreferredSize(dd);
        chartPanel.setSize(800,400);
        setContentPane(chartPanel);
        setSize(800,600);
        setVisible(true);
        System.out.println("EL TAMANO ES: "+getPreferredSize());
        
        System.out.println("ESTE NUEVO!!!123");
    }
    
    public void graficaTSTLI(){
        
        String datos = getParameter("datos");
        StringTokenizer tokens = new StringTokenizer(datos, "*");
        String titulo = null;
        String ejeX = null;
        String ejeY = null;
        
        titulo = tokens.nextToken();//el primer token es el titulo
        ejeX = tokens.nextToken();// segundo el eje x
        ejeY = tokens.nextToken();// tercero el eje y
        // por ultimo los datos
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        String atributo =null;// este dependera del tipo de grafica(cable, armario_id, subzona estos atributos son de la tabla requerimientoscola)
        String valor =null;
        
        while(tokens.hasMoreElements()){
            atributo=tokenP.nextToken();
            valor=tokenP.nextToken();
            dataset.addValue(new Double(valor),"PENDIENTE",COLA);
        }
        
        
        JFreeChart jfreechart = ChartFactory.createBarChart(titulo, ejeX, ejeY, dataset, PlotOrientation.VERTICAL, false, true, false);
        
        jfreechart.setBackgroundPaint(new GradientPaint(0.0F, 0.0F, Color.WHITE, 70F, 0.0F, new Color(211, 211, 211), true));
        
        CategoryPlot categoryplot = jfreechart.getCategoryPlot();
        categoryplot.setBackgroundPaint(new Color(220,220,255));
        categoryplot.setDomainGridlinePaint(Color.white);
        categoryplot.setRangeGridlinePaint(Color.white);
        
        NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        
        BarRenderer barrenderer = (BarRenderer)categoryplot.getRenderer();
        
        barrenderer.setLabelGenerator( new LabelGenerator());       
        
        barrenderer.setItemLabelsVisible(true);
        ItemLabelPosition itemlabelposition = new ItemLabelPosition(ItemLabelAnchor.INSIDE12, TextAnchor.CENTER_RIGHT, TextAnchor.CENTER_RIGHT, -1.5707963267948966D);
        barrenderer.setPositiveItemLabelPosition(itemlabelposition);
        ItemLabelPosition itemlabelposition1 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.CENTER_LEFT, TextAnchor.CENTER_LEFT, -1.5707963267948966D);
        barrenderer.setPositiveItemLabelPositionFallback(itemlabelposition1);
        CategoryAxis categoryaxis = categoryplot.getDomainAxis();
        categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        ChartPanel chartPanel= new ChartPanel(jfreechart);
        Dimension dd=new Dimension(800,400);
        chartPanel.setPreferredSize(dd);
        chartPanel.setSize(800,400);
        setContentPane(chartPanel);
        setSize(800,600);
        setVisible(true);
    }
}
