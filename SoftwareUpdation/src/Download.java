import java.io.*;
import java.net.*;
import java.util.*;


// This class downloads a file from a URL.
class DownloadFile extends Observable implements Runnable {
  // Max size of download buffer.
  private static final int MAX_BUFFER_SIZE = 1024;

  // These are the status names.
  public static final String STATUSES[] = {"Downloading","","Updated","", "Error"};

  // These are the status codes.
  public static final int DOWNLOADING = 0;
  public static final int COMPLETE = 2;
  public static final int ERROR = 4;

  private URL url; // download URL
  private int size; // size of download in bytes
  private int downloaded; // number of bytes downloaded
  private int status; // current status of download
  String appname="";

  // Constructor for Download.
    public DownloadFile(URL url,String name)
    {
       this.appname=name;
        this.url = url;
        size = -1;
        downloaded = 0;
        status = DOWNLOADING;
        // Begin the download.
        download();
    }


    // Get this download's URL.
    public String getUrl()
    {
        return url.toString();
    }


    // Get this download's size.
    public int getSize()
    {
        return size;
    }

    //Get the downloaded bytes
    public int getDownloaded()
    {
        return downloaded;
    }


    // Get this download's progress.
    public float getProgress()
    {
        return ((float) downloaded / size) * 100;
    }

    // Get the file name
    public String getName()
    {
        String p=url.getFile();
        return p.substring(p.lastIndexOf('/') + 1);
    }


    // Get this download's status.
    public int getStatus()
    {
        return status;
    }


    // Resume this download.
    public void resume()
    {
        status = DOWNLOADING;
        stateChanged();
        download();
    }


    // Mark this download as having an error.
    private void error()
    {
        status = ERROR;
        stateChanged();
    }


    // Start or resume downloading.
    private void download()
    {
        Thread thread = new Thread(this);
        thread.start();
    }


    // Get file name portion of URL.
    private String getFileName(URL url)
    {
        String fileName = url.getFile();
        return fileName.substring(fileName.lastIndexOf('/') + 1);
    }


    // Download file.
    public void run()
    {
        RandomAccessFile file = null;
        InputStream stream = null;

        try
        {
            // Open connection to URL.
            HttpURLConnection connection =(HttpURLConnection) url.openConnection();

            // Specify what portion of file to download.
            connection.setRequestProperty("Range","bytes=" + downloaded + "-");

            // Connect to server.
            connection.connect();

            // Make sure response code is in the 200 range.
            if (connection.getResponseCode() / 100 != 2)
            {
                error();
            }

            // Check for valid content length.
            int contentLength = connection.getContentLength();
            if (contentLength < 1)
            {
                error();
            }

            /* Set the size for this download if it hasn't been already set. */
            if (size == -1)
            {
                size = contentLength;
                stateChanged();
            }
            if(new GetOSName().getName().equals("win"))
            {

            java.io.File f =new java.io.File("splash.gif");
            String path = f.getAbsolutePath().replaceAll("splash.gif", "")+"Bin\\Data\\Temp\\";
            file = new RandomAccessFile(path+getFileName(url), "rw");
            }
 else
            {
             java.io.File f =new java.io.File("splash.gif");
            String path = f.getAbsolutePath().replaceAll("splash.gif", "")+"Bin/Data/Temp/";
            file = new RandomAccessFile(path+getFileName(url), "rw");

 }
            file.seek(downloaded);
            stream = connection.getInputStream();
            while (status == DOWNLOADING)
            {
                /* Size buffer according to how much of the file is left to download. */
                byte buffer[];
                if (size - downloaded > MAX_BUFFER_SIZE)
                {
                    buffer = new byte[MAX_BUFFER_SIZE];
                }
                else
                {
                    buffer = new byte[size - downloaded];
                }

                // Read from server into buffer.
                int read = stream.read(buffer);
                if (read == -1)
                    break;

                // Write buffer to file.
                file.write(buffer, 0, read);
                downloaded += read;
                stateChanged();
            }

        /* Change status to complete if this point was reached because downloading has finished. */
        if (status == DOWNLOADING)
        {
             if(new GetOSName().getName().equals("win"))
            {
                java.io.File f1 =new java.io.File("splash.gif");
                String dt = f1.getAbsolutePath().replaceAll("splash.gif", "")+"Bin\\Data\\Apps\\"+appname+getFileName(url);
                String src = f1.getAbsolutePath().replaceAll("splash.gif", "")+"Bin\\Data\\Temp\\"+getFileName(url);
                System.out.println("Source"+src);
                System.out.println("Destination"+dt);
            copyfile(src,dt);
            new File(src).deleteOnExit();
            status = COMPLETE;
            stateChanged();
            }
            else
            {
            java.io.File f1 =new java.io.File("splash.gif");
            String dt = f1.getAbsolutePath().replaceAll("splash.gif", "")+"Bin/Data/Apps/"+appname+getFileName(url);
            String src = f1.getAbsolutePath().replaceAll("splash.gif", "")+"Bin/Data/Temp/"+getFileName(url);
            System.out.println("Source"+src);
            System.out.println("Destination"+dt);
            copyfile(src,dt);
            status = COMPLETE;
            stateChanged();
            }
        }
    } catch (Exception e)
        {
            error();
        } finally
            {
                // Close file.
                if (file != null)
                {
                    try
                    {
                        file.close();
                    } catch (Exception e)
                        {
                        }
                }

                // Close connection to server.
                if (stream != null)
                {
                    try
                    {
                        stream.close();
                    } catch (Exception e)
                        {
                        }
                }
            }
  }

    class GetOSName
    {
        public String getName()
        {
            try
            {
                String osName= System.getProperty("os.name");
                if(osName.contains("Mac"))
                {
                    return "mac";
                }
                else
                    if(osName.contains("Window"))
                    {
                        return "win";
                    }
                else
                    {
                        return "linux";
                    }
            }catch (Exception e)
                {
                    return "No";
                }
    }
}

    // Notify observers that this download's status has changed.
    private void stateChanged()
    {
        setChanged();
        notifyObservers();
    }

     private static void copyfile(String srFile, String dtFile)
     {
         System.out.println("Source"+srFile);
            System.out.println("Destination"+dtFile);
    try{
      File f1 = new File(srFile);
      File f2 = new File(dtFile);
      InputStream in = new FileInputStream(f1);
      
      OutputStream out = new FileOutputStream(f2);

      byte[] buf = new byte[1024];
      int len;
      while ((len = in.read(buf)) > 0){
        out.write(buf, 0, len);
      }
      in.close();
      out.close();
    }
    catch(Exception ex){System.out.println(ex);}
  }
}