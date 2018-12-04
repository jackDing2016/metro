package hello.pyTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WithParam {

    public static void main(String[] args) {
//        int[] a = new int[]{18, 12};
//        int a = 18;
        int b = 23;
        String a = "229,244,264,285,245,248,251,255,235,190,129,154,157,181,199,181,184,162,178,130,144,150,103,108";
        String c = "11,66,55,12,110,1100,110,55,110,110,110,55,110,110,88,110,110,110,11,110,22,110,33,22";
        try {
            String[] args1 = new String[] { "E:\\Python27\\python", "E:\\learn\\java\\jyni\\churukou1107.py",
                    String.valueOf(a), String.valueOf(c) };

//            String[] args1 = new String[] { "python3", "E:\\learn\\java\\jyni\\churukou.py", String.valueOf(a) };
            Process proc = Runtime.getRuntime().exec(args1);// 执行py文件

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
