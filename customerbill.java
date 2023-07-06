import java.io.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.awt.Desktop;
import java.sql.*;

class customerbill
  {
   String path;   OutputStream file;  Document document;
   Paragraph p;   PdfPTable table;   PdfPCell c1;
   Font big,small,small1,small2;
   Connection cn; Statement stm; ResultSet rs;
   int cnt,tid,tot,tot1=0;
  
customerbill(int billno)
{
	
	
  big=new Font(Font.FontFamily.TIMES_ROMAN,30,Font.BOLD);
  
  small=new Font(Font.FontFamily.TIMES_ROMAN,14,Font.BOLD);
  small1=new Font(Font.FontFamily.TIMES_ROMAN,12,Font.ITALIC); 
  small2=new Font(Font.FontFamily.TIMES_ROMAN,8,Font.NORMAL);

  try
   {
      cn=DriverManager.getConnection("jdbc:mysql://localhost:3307/addon2","root","");
       stm=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
      rs=stm.executeQuery("select * from prod_bill where t_id ="+tid);
      while(rs.next())
       {
          billno=Integer.parseInt(rs.getString(2));
          tot=Integer.parseInt(rs.getString(12));
          tid=Integer.parseInt(rs.getString(1));
          
       }
   
       }
   
  catch(Exception e)
    {
      System.out.println(e);
    }

   try
     {
       path="test.pdf";
       file=new FileOutputStream(new File(path));
       document=new Document();
       PdfWriter.getInstance(document,file);
       document.open();
         }
  catch(Exception e)
      {
        System.out.println(e);
       }

   try
    {
      //write in pdf
		p=new Paragraph("Jewellery",big);
		p.setAlignment(Element.ALIGN_CENTER);
     
      document.add(p);

    

     p=new Paragraph("Rajastan Corner,Mahaveer Peth,Laxmi Road,Baramati 413 102.",small1);
      p.setAlignment(Element.ALIGN_CENTER);
      
      document.add(p);

     p=new Paragraph("Mob.:7709355307,02112 255320",small1);
      p.setAlignment(Element.ALIGN_CENTER);
      
      document.add(p);

        
      p=new Paragraph("Date = " +new java.util.Date());
      p.setAlignment(Element.ALIGN_RIGHT);
            document.add(p);
      //document.add(new Paragraph(" "));

      p=new Paragraph("Bill No = " +billno);
      p.setAlignment(Element.ALIGN_RIGHT);
        
      document.add(p);
      document.add(new Paragraph(" "));
      
      //p=new Paragraph("Transaction ID = " +tid);
      //p.setAlignment(Element.ALIGN_CENTER);
       
      //document.add(p);
      
      document.add(new Paragraph(" "));
      
     
    }

  catch(Exception e)
    {
     System.out.println(e);
    }
 //void add_img()
  //  {
        try
        {
            // Add Image
            Image img = Image.getInstance("meri.jpg");
            img.scalePercent(50);
            img.setAbsolutePosition(130, 740);
            document.add(img);
        }
        catch(Exception e){     e.printStackTrace();    }
    //}


 float[] colsWidth={20,50,30,30,30};
 table=new PdfPTable(colsWidth);
 table.setWidthPercentage(80);

 c1=new PdfPCell(new Phrase("sr.no",small));
 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
  
  table.addCell(c1);

c1=new PdfPCell(new Phrase("Product Name",small));
 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
  
  table.addCell(c1);

c1=new PdfPCell(new Phrase("Price",small));
c1.setHorizontalAlignment(Element.ALIGN_CENTER);
  
  table.addCell(c1);

c1=new PdfPCell(new Phrase("Quantity",small));
 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
  
  table.addCell(c1);
 
c1=new PdfPCell(new Phrase("Total",small));
 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
  
  table.addCell(c1);


  cnt=0;
  
  try
   {
     rs=stm.executeQuery("select * from prod_bill where b_no="+billno);
     {  
      
       rs.beforeFirst();
       while(rs.next())
      {
       
           int tot=Integer.parseInt(rs.getString(12));
           tot1=tot1+tot;
     
         cnt++;
         c1=new PdfPCell(new Phrase(""+cnt));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
          table.addCell(c1);

          c1=new PdfPCell(new Phrase(rs.getString(6)));
          c1.setHorizontalAlignment(Element.ALIGN_CENTER);
          table.addCell(c1);


           c1=new PdfPCell(new Phrase(rs.getString(10)));
          c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
           table.addCell(c1);


          c1=new PdfPCell(new Phrase(rs.getString(11)));
          c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
          table.addCell(c1);
     
           c1=new PdfPCell(new Phrase(rs.getString(12)));
          c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
           table.addCell(c1);
           
    

            // column spanning */
    
    
    }
PdfPCell cell =new PdfPCell(new Phrase("Total Amount="+tot1));
    cell.setColspan(5);
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(cell);
    document.add(table);
    document.add(new Paragraph(" "));



   
    p=new Paragraph("Note:Goods Once sold will not be taken back or exchanged",small1);
      p.setAlignment(Element.ALIGN_BOTTOM);
      p.setAlignment(Element.ALIGN_BOTTOM);
     
      document.add(p);

     p=new Paragraph("Thank you for shopping",small1);
      p.setAlignment(Element.ALIGN_BOTTOM);
      p.setAlignment(Element.ALIGN_CENTER);
      
      document.add(p);


    p=new Paragraph("Please Come Again",small1);
      p.setAlignment(Element.ALIGN_BOTTOM);
      p.setAlignment(Element.ALIGN_CENTER);
      
      document.add(p);


     /* p=new Paragraph("Stamp",small);
      p.setAlignment(Element.ALIGN_BOTTOM);
      p.setAlignment(Element.ALIGN_BOTTOM);
      p.setAlignment(Element.ALIGN_BOTTOM);
      p.setAlignment(Element.ALIGN_BOTTOM);
      p.setAlignment(Element.ALIGN_BOTTOM);
      p.setAlignment(Element.ALIGN_BOTTOM);
      p.setAlignment(Element.ALIGN_BOTTOM);
      p.setAlignment(Element.ALIGN_LEFT);
     
      document.add(p);*/

     p=new Paragraph("Prepared By",small1);
      p.setAlignment(Element.ALIGN_BOTTOM);
      p.setAlignment(Element.ALIGN_BOTTOM);
      p.setAlignment(Element.ALIGN_BOTTOM);
      p.setAlignment(Element.ALIGN_BOTTOM);
      p.setAlignment(Element.ALIGN_BOTTOM);
      p.setAlignment(Element.ALIGN_BOTTOM);
      p.setAlignment(Element.ALIGN_RIGHT);
     
      document.add(p);


   }
  }
 catch(Exception e)
    {
     System.out.println(e);
    }
    

try
     {
        
      document.close();
      file.close();
          Desktop desktop =Desktop.getDesktop();
      desktop.open(new java.io.File(path));
     }
    catch(Exception e)
    {
     System.out.println(e);
    }
 
}
}
