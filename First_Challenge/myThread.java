package First_Challenge;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class myThread  extends Thread
{
    public int r=0;
    public int c=0;
    private List<String> lines;
    private List<Character> Stack;
    public myThread(int r, int c, List<String> lines)
    {
        this.r=r;
        this.c=c;
        this.lines=lines;
        Stack = new ArrayList();
    }

    @Override
    public void run()
    {
        boolean finish=false;
        boolean c_control=false;
        boolean r_control=false;
        int startc=c;
        int startr=r;
        String flag="";
        r++; // Next Row
            while(!finish)
            {
                for(int i=c;i<lines.get(r).length() && !finish;i++)
                {

                   switch(lines.get(r).charAt(i))
                   {
                       case '@':
                           finish=true;
                           break;
                       case '(' :
                                {
                                        flag = Stack.get(Stack.size() - 1) + flag;
                                        Stack.remove(Stack.size() - 1);
                                        int n=ReadNumber(i+1,lines.get(r).toCharArray(),"right");
                                        i=i-n-1;
                                }
                           break;
                       case ')' : {
                                        flag = flag + Stack.get(Stack.size() - 1);
                                        Stack.remove(Stack.size() - 1);
                                        int n=ReadNumber(i-1,lines.get(r).toCharArray(),"left");
                                        i=i+n-1;
                                  }
                                break;
                       case '-' :
                                {
                                    char[] tmp = flag.toCharArray();
                                    flag=DeleteFromCharArr(tmp,0);;
                                    int n=ReadUpDown(r+1,i,"down");
                                    r=r-n;
                                    c=i;
                                    c_control=true;
                                    r_control=true;
                                    i=lines.get(r).length();
                                }
                                break;
                       case '+':
                                {
                                    char[] tmp = flag.toCharArray();
                                    flag=DeleteFromCharArr(tmp,tmp.length-1);;
                                    int n=ReadUpDown(r-1,i,"up");
                                    r=r+n;
                                    c=i;
                                    c_control=true;
                                    r_control=true;
                                    i=lines.get(r).length();
                                }
                                break;
                       case '%':
                                {
                                    flag=reverse(flag);
                                    r=r+1;
                                    c=i;
                                    r_control=true;
                                    c_control=true;
                                    i=lines.get(r).length();
                                }
                                break;
                       case '[' :
                                {
                                    Stack.add(lines.get(r).charAt(i+1));
                                    i++;
                                }
                                break;
                       case ']' :
                                {
                                    Stack.add(lines.get(r).charAt(i-1));
                                    i=i-3;
                                }
                                break;
                       case '*' :
                                {
                                    char[] tmp = lines.get(r-1).toCharArray();
                                    Stack.add(tmp[i]);
                                    r=r-2;
                                    c=i;
                                    r_control=true;
                                    c_control=true;
                                    i=lines.get(r).length();
                                }
                                 break;
                       case '.':
                               {
                                   char[] tmp = lines.get(r+1).toCharArray();
                                   Stack.add(tmp[i]);
                                   r=r+2;
                                   c=i;
                                   r_control=true;
                                   c_control=true;
                                   i=lines.get(r).length();
                               }
                                break;
                       case '<' :
                                {
                                    int n=ReadNumber(i+1,lines.get(r).toCharArray(),"right");
                                    i=i-n-1;
                                }
                                break;
                       case '>' :
                                {
                                    int n=ReadNumber(i-1,lines.get(r).toCharArray(),"left");
                                    i=(i+n)-1;
                                }
                                break;
                       case '^' :
                                {
                                    int n=ReadUpDown(r+1,i,"down");
                                    r=r-n;
                                    c=i;
                                    r_control = true;
                                    c_control = true;
                                    i=lines.get(r).length();
                                }
                                break;
                       case 'v' :
                                {
                                    int n=ReadUpDown(r-1,i,"up");
                                    r=r+n;
                                    c=i;
                                    r_control = true;
                                    c_control = true;
                                    i=lines.get(r).length();
                                }
                       break;
                       default :
                           break;
                   }
                }
                if(!c_control) //Se la c non è modificata DENTRO
                {
                    c = 0;
                }
                else
                {
                    c_control=false;
                }
                if(!r_control)
                {
                    r++;
                }
                else
                {
                    r_control=false;
                }
            }
            System.out.println("Possible : "+flag);
    }

    private String reverse(String flag)
    {
        char[] ftochar = flag.toCharArray();
        String newflag = "";
        for(int i=ftochar.length-1;i>=0;i--)
        {
            newflag+=ftochar[i];
        }
        return newflag;
    }

    private int ReadUpDown(int riga, int c, String type)
    {
        String toparse="";
        boolean finish=false;
        while(!finish)
        {
            char[] line = lines.get(riga).toCharArray();
            if(lines.get(riga).charAt(c)>='0' && lines.get(riga).charAt(c)<='9')
            {
                toparse += lines.get(riga).charAt(c);
                if(type=="down")
                    riga++;
                else if(type=="up")
                    riga--;
            }
            else
            {
                finish=true;
            }
        }
        return Integer.parseInt(toparse);
    }

    private String DeleteFromCharArr(char[] tmp, int pos)
    {
        String res = "";
        for(int i=0;i<tmp.length;i++)
        {
            if(i!=pos)
            {
                res+=tmp[i];
            }
        }
        return res;
    }

    private int ReadNumber(int i,char[] line,String type)
    {
        int value = 0;
        String toparse = "";
        while(line[i]>='0' && line[i]<='9')
        {
            if(type=="right")
            {
                toparse += line[i];
                i++;
            }
            else if(type=="left")
            {
                toparse+=line[i];
                i--;
            }
        }
        value=Integer.parseInt(toparse);
        return value;
    }
}
