package RavBase64;

public class B2D
{
    String str="";
  B2D(String st)
  {
    str=st;
  }
  public String re()
  {
      long num = Long.parseLong(str);
    long rem;
    while(num > 0){
      rem = num % 10;
      num = num / 10;
      if(rem != 0 && rem != 1)
      {
        System.exit(0);
      }
    }
    int i= Integer.parseInt(str,2);
      return(""+i);
  }
}