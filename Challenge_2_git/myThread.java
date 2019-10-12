package Second_Challenge;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.Object;
import java.lang.*;
import org.apache.commons.lang3.StringUtils;

public class myThread extends Thread
{
    public File first;
    public File second;
    String diff1;
    String diff2;
    public myThread(File first, File second)
    {
        this.first=first;
        this.second=second;
        diff1="";
        diff2="";
    }
    @Override
    public void run()
    {
       List<String> a = readFile(first);
       List<String> b =  readFile(second);
    }



    /*

    OLD VERSION

    @Override
    public void run() {
        List<String> flines = null;
        try {
            flines = getLines(first);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> slines = null;
        try {
            slines = getLines(second);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int ln = flines.size()<slines.size() ? flines.size() : slines.size();
        for(int i=0;i<ln;i++)
        {
            String a = flines.get(i);
            String b = slines.get(i);
            diff1+=compareLine(a,b);
            diff2+=compareLine(b,a);
        }
        System.out.println("Diff 1 : "+diff1);
        System.out.println("Diff 2 : "+diff2);
        try {
            BufferedWriter wr = new BufferedWriter(new FileWriter("src/Second_Challenge/"+first.getName()+"(0).txt"));
            wr.write(diff1);
            wr = new BufferedWriter(new FileWriter("src/Second_Challenge/"+first.getName()+"(1).txt"));
            wr.write(diff2);
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private String compareLine(String a, String b)
    {
        String res = "";
        char[] arr_a = a.toCharArray();
        char[] arr_b = b.toCharArray();
        int len = (arr_a.length<arr_b.length) ? arr_a.length : arr_b.length;
        for(int i=0;i<len;i++)
        {
            if(arr_a[i]!=arr_b[i])
            {
                res+=arr_a[i];
            }
        }
        return res;
    }

    public List<String> getLines(File file) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> lines= new ArrayList();
        String line="";
        while((line=reader.readLine())!=null)
        {
            lines.add(line);
        }
        reader.close();
        return lines;
    }
*/
}
