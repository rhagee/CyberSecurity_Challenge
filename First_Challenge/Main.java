package First_Challenge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new FileReader("src/First_Challenge/file.txt"));
        Thread thread = new Thread();
        thread.start();
        List<myThread> thrlist = new ArrayList();
        List<String> lines = createList(reader);
        int startc=0;
        int startr=0;
        for(int i=0;i<lines.size();i++)
        {
            char[] line = lines.get(i).toCharArray();
            for(int j=0;j<line.length;j++)
            {
                if(line[j]=='$')
                {
                    List<String> newlines = new ArrayList();
                    newlines = List.copyOf(lines);
                    myThread tr = new myThread(i,j,newlines);
                    tr.start(); //start
                    thrlist.add(tr);
                }
            }
        }
    }

    private static List<String> createList(BufferedReader reader) throws IOException {
        List<String> lines = new ArrayList();
        String line="";
        while((line=reader.readLine())!=null)
        {
            lines.add(line);
        }
        return lines;
    }
}
