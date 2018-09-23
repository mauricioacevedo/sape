
import java.awt.*;
import java.io.PrintStream;
import java.util.StringTokenizer;
import javax.swing.JApplet;
import org.jfree.chart.*;
import org.jfree.chart.axis.HorizontalCategoryAxis3D;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.DefaultCategoryDataset;

public class GraphBarras extends JApplet
{

    public GraphBarras()
    {
    }
    //el string ke recibe con datos debe kedar asi:
    //TITULO*labelEjeX*labelEjeY*
    public void init()
    {
        String parametro = getParameter("datos");
        StringTokenizer token = new StringTokenizer(parametro, "*");
        String titulo = token.nextToken();
        String ejeX = token.nextToken();
        String ejeY = token.nextToken();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try
        {
            String caption;
            for(; token.hasMoreElements(); dataset.addValue(new Double(token.nextToken()), caption, "??"))
                caption = token.nextToken();

        }
        catch(NumberFormatException e)
        {
            System.out.println("Error en un par\341metro num\351rico");
            System.out.println("APPLET NO CARGADO");
            return;
        }
        JFreeChart chart = ChartFactory.createVerticalBarChart3D(titulo, ejeX, ejeY, dataset, true, true, false);
        chart.setBackgroundPaint(new GradientPaint(0.0F, 0.0F, Color.WHITE, 70F, 0.0F, new Color(211, 211, 211), true));
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setValueLabelsVisible(true);
        HorizontalCategoryAxis3D axis = (HorizontalCategoryAxis3D)plot.getDomainAxis();
        axis.setTickLabelsVisible(false);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(550, 300));
        setContentPane(chartPanel);

    }
}
