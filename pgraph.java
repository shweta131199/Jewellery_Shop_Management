import org.jfree.chart.*;
import org.jfree.data.category.*;
import org.jfree.chart.plot.*;
import java.awt.*;

public class pgraph
{
    pgraph()
   {
        try
     {
     cn=DriverManager.getConnection("jdbc:mysql://localhost:3307/komal","root","");
     stm=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
     rs=stm.executeQuery("select * from product");
      rs.next();
      {//display();
       DefaultCategoryDataset dataset = new DefaultCategoryDataset();
  dataset.setValue(rs.getString(4), "Sales", "Hair Product");
  dataset.setValue(rs.getString(4), "Sales", "Body Product");
  dataset.setValue(rs.getString(4), "Sales", "Nail Product");
  dataset.setValue(rs.getString(4), "Sales", "Skin Product");
  dataset.setValue(rs.getString(4), "Sales", "Eye Product");
  dataset.setValue(rs.getString(4), "Sales", "Perfume Product");
  dataset.setValue(rs.getString(4), "Sales", "Lip Product");
     }
   JFreeChart chart = ChartFactory.createBarChart
  ("BarChart","Products", "Sales", dataset,
   PlotOrientation.VERTICAL, false,true, false);
  chart.setBackgroundPaint(Color.yellow);
  chart.getTitle().setPaint(Color.blue);
  CategoryPlot p = chart.getCategoryPlot();
  p.setRangeGridlinePaint(Color.red);
  ChartFrame frame1=new ChartFrame("Bar Chart",chart);
  frame1.setVisible(true);
  frame1.setSize(900,550);
  
	//maxtid=rs.getInt(1);
//rs.close();
	}
   catch(Exception e)
    {
    e.printStackTrace();
    }

   }
 public static void main(String arg[])
 {
    new pgraph();
  }
}
