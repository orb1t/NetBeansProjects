import java.sql.*;
public class CheckQuery
{
    String empid;
  public CheckQuery(String emp)
  {
        empid=emp;
  }

  public String search(String rav)
  {
      Connection con = null;
        try
        {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String driver="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ="+rav;
            con = DriverManager.getConnection(driver,"","");
            Statement sta = con.createStatement();
            // getting the data back
            ResultSet res = sta.executeQuery("SELECT * FROM EnggDept where EMPID="+empid);
            if(res.next())
            {
                return res.getString("PSWD");
            }
      res.close();
      sta.close();
      con.close();
    } catch (Exception e) {
      System.err.println("Exception: "+e.getMessage());
    }
      return "";
  }
}
