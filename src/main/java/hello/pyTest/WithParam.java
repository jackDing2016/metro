package hello.pyTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WithParam {

    public static void main(String[] args) {
//        int[] a = new int[]{18, 12};
//        int a = 18;
        int b = 23;
        String a = "758,723,866,913,926,914,789,854,833,720,643,692,748,702,572,387,806,932,1042,953,1070,945,610,606";
        String c = "11,66,866,913,926,914,789,55,833,720,643,55,748,702,88,22,806,932,11,953,22,945,33,22";
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
