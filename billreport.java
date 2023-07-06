import java.io.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.sql.*;
import java.awt.Desktop;
class billreport
{
 String path;
 Document document;
 OutputStream file;
 Paragraph p;
 PdfPTable table;
 PdfPCell c1;
 Font big,small;
 Connection cn;
 Statement stm;
 ResultSet rs;
 //br=BufferedReader(new BufferedReader(InputStreamReader(System.in)));
 int cnt,tot,tot1;
 String sname;
String fdt,tdt;
 
  //fdt=br.readLine();
  //tdt=br.readLine();
 billreport(String fdt,String tdt)
 {
  big=new Font(Font.FontFamily.TIMES_ROMAN,20,Font.BOLD);
  small=new Font(Font.FontFamily.TIMES_ROMAN,12,Font.BOLD);
  try
  {
   cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jwellery","root","");
   stm=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

        

    /*if(dt>=fdt && dt<=tdt)
    {
    rs=stm.executeQuery("select * from customer where date BETWEEN'"+fdt+"' AND '"+tdt+"'");
      while(rs.next())
       {
          dt=rs.getString(5);
         
         
          
       }
   }*/
  }
  catch(Exception e)
  {
   System.out.println(e);
  }
 //}
 //void create_pdf()
 //{
  try
  {

      path="Test.pdf";
   file=new FileOutputStream(new File(path));
   document=new Document();
   PdfWriter.getInstance(document,file);
   document.open();
   //document.newPage(); //for new page
   }
   catch(Exception e)
   {
   System.out.println(e);
   }
  //}
  //void writer_heading()
  //{ac
   try
   {
    //wrie in a pdf
    p=new Paragraph("Bill Report",big);
    p.setAlignment(Element.ALIGN_CENTER);
    document.add(p);
    document.add(new Paragraph(" "));
    
    p=new Paragraph("Date=" +new java.util.Date());
    p.setAlignment(Element.ALIGN_RIGHT);
    document.add(p);
    document.add(new Paragraph(" "));
    
    p=new Paragraph("From Date:" +fdt+  "     To Date:"+tdt);
    p.setAlignment(Element.ALIGN_RIGHT);
    document.add(p);
    document.add(new Paragraph(" "));
   
   }
   catch(Exception e)
   {
   System.out.println(e);
   }
  //}
 // void create_tab_heading()
  //{
   float[] colsWidth={100,250,150,250,250,250,300};
   table=new PdfPTable(colsWidth);
   table.setWidthPercentage(80);
   c1=new PdfPCell(new Phrase("Sr No",small));
   c1.setHorizontalAlignment(Element.ALIGN_CENTER);
   table.addCell(c1);
   c1.setFixedHeight(55);
   c1=new PdfPCell(new Phrase("Customer Name",small));
   c1.setHorizontalAlignment(Element.ALIGN_CENTER);
   table.addCell(c1);
   c1.setFixedHeight(55);

   c1=new PdfPCell(new Phrase("Bill No.",small));
   c1.setHorizontalAlignment(Element.ALIGN_CENTER);
   table.addCell(c1);
   c1.setFixedHeight(55);


   c1=new PdfPCell(new Phrase("Product Name",small));
   c1.setHorizontalAlignment(Element.ALIGN_CENTER);
   table.addCell(c1);
   
  
   c1=new PdfPCell(new Phrase("Price ",small));
   c1.setHorizontalAlignment(Element.ALIGN_CENTER);
   table.addCell(c1);

   c1=new PdfPCell(new Phrase("Quantity",small));
   c1.setHorizontalAlignment(Element.ALIGN_CENTER);
   table.addCell(c1);
   
  
   c1=new PdfPCell(new Phrase("Total",small));
   c1.setHorizontalAlignment(Element.ALIGN_CENTER);
   table.addCell(c1);

   

  /* c1=new PdfPCell(new Phrase("Supplier Name",small));
   c1.setHorizontalAlignment(Element.ALIGN_CENTER);
   table.addCell(c1);*/
   
   table.setHeaderRows(1);
 
  //}
   //void add_rows()
  //{
   cnt=0;
   try
   {
    rs=stm.executeQuery("select * from cust_bill where cust_bill.b_date BETWEEN '"+fdt+"' AND '"+tdt+"'");
    //for(int i=0;i<10;i++)
   
    rs.beforeFirst();
    while(rs.next())
    {
       
      int tot=Integer.parseInt(rs.getString(12));
           tot1=tot1+tot;
     
     cnt++;
     c1=new PdfPCell(new Phrase(Integer.toString(cnt)));
     c1.setHorizontalAlignment(Element.ALIGN_CENTER);
     table.addCell(c1);

     c1=new PdfPCell(new Phrase(rs.getString(5)));
     c1.setHorizontalAlignment(Element.ALIGN_CENTER);
     table.addCell(c1);

     table.addCell(rs.getString(2));
     table.addCell(rs.getString(6));
     table.addCell(rs.getString(10));
     table.addCell(rs.getString(11));
     table.addCell(rs.getString(12));
     
    }
   
    rs.close();cn.close();
   //column heading
   PdfPCell cell=new PdfPCell(new Paragraph("Total Bill="+tot1));
   cell.setColspan(7);
   c1.setHorizontalAlignment(Element.ALIGN_CENTER);
   table.addCell(cell);
   document.add(table);
   document.add(new Paragraph(" "));
   
   }
   catch(Exception e)
   {
   System.out.println(e);
   }
  //}
 // void close_pdf()
  //{
   try
   {
    document.close();
    file.close();
    //to open the pdf file in linux & windows
    Desktop desktop=Desktop.getDesktop();
    desktop.open(new java.io.File(path));
  
   }
   catch(Exception e)
    {
    e.printStackTrace();
    }
}    
  
  
 //}
 /*class custreport1
 {
  public static void main(String args[])
  {
   custreport1 obj=new custreport1();
   obj.create_pdf();
   obj.create_tab_heading();
   obj.writer_heading();
   obj.add_rows();
   obj.close_pdf();
  }*/
 }

