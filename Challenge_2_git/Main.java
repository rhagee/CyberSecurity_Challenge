package Second_Challenge;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        File folder = new File("src/Second_Challenge/files");
        File[] list = folder.listFiles();
        List<FilePair> FileList = new ArrayList();
        List<myThread> Compare = new ArrayList();
        for (int i = 0; i < list.length-1;i++)
        {
            char[] a = list[i].getName().toCharArray();
            char[] b = list[i+1].getName().toCharArray();
            String new1="";
            String new2="";
            int lun = (a.length<b.length) ? a.length : b.length;
            boolean exit=false;
            for(int j=0;j<lun && !exit;j++)
            {
                if(b[j]!='.')
                {
                    new1+=a[j];
                    new2+=b[j];
                }
                else
                {
                    exit=true;
                }
            }
            if(new1.equals(new2))
            {
                FileList.add(new FilePair(list[i],list[i+1]));
            }
        }
        for(int i=0;i<FileList.size();i++)
        {
            myThread tr = new myThread(FileList.get(i).first,FileList.get(i).second);
            tr.start();
            Compare.add(tr);
        }
    }
}
