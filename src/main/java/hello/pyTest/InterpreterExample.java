package hello.pyTest;

import org.python.core.PyInstance;
import org.python.util.PythonInterpreter;

import java.util.Properties;

public class InterpreterExample
{

    PythonInterpreter interpreter = null;


    public InterpreterExample()
    {
//        PythonInterpreter.initialize(System.getProperties(),
//                System.getProperties(), new String[0]);

        Properties props = new Properties();

        props.put("python.import.site", "false");

//        Properties preprops = System.getProperties();

        PythonInterpreter.initialize(System.getProperties(), props, new String[0]);


        this.interpreter = new PythonInterpreter();
    }

    void execfile( final String fileName )
    {
        this.interpreter.execfile(fileName);
    }

    PyInstance createClass(final String className, final String opts )
    {
        return (PyInstance) this.interpreter.eval(className + "(" + opts + ")");
    }

    public static void main( String gargs[] )
    {
//        InterpreterExample ie = new InterpreterExample();
//
//        ie.execfile("hello.py");
//
//        PyInstance hello = ie.createClass("Hello", "None");
//
//        hello.invoke("run");

        Properties props = new Properties();

        props.put("python.import.site", "false");

        Properties preprops = System.getProperties();

        PythonInterpreter.initialize(preprops, props, new String[0]);
        PythonInterpreter interpreter = new PythonInterpreter();
//        interpreter.exec("days=('mod','Tue','Wed','Thu','Fri','Sat','Sun'); ");
//        interpreter.exec("print days[1];");

        interpreter.exec("\n" +
                "import numpy as np\n" +
                "import math\n" +
                "from matplotlib import pyplot as plt\n" +
                "from scipy.special import comb\n" +
                "\n" +
                "lst=[758\t,723\t,866\t,913\t,926\t,914\t,789\t,854\t,833\t,720\t,643\t,692\t,748\t,702\t,572\t,387\t,806\t,932\t,1042\t,953\t,1070\t,945\t,610\t,606\t]\n" +
                "\n" +
                "\n" +
                "lst=lst/np.ones(24)/2 \n" +
                "\n" +
                "print(\"len= %d\"%len(lst))\n" +
                "def sum1(list):  \n" +
                "    s = 0\n" +
                "      \n" +
                "    for x in list:  \n" +
                "        s += x  \n" +
                "    return s  \n" +
                "def average(list):  \n" +
                "    avg = 0  \n" +
                "    avg = sum1(lst)/float(len(lst)*1.0)\n" +
                "    return avg \n" +
                "\n" +
                "\n" +
                "def var(list,avg):\n" +
                "    var1=0\n" +
                "    for i in list:\n" +
                "        var1+=float((i-avg)**2*1.0)\n" +
                "    var2=(math.sqrt(var1/(len(lst)-1)*1.0))\n" +
                "    return var2*var2\n" +
                "\n" +
                "avg=average(lst)\n" +
                "\n" +
                "var2=var(lst,avg)\n" +
                "\n" +
                "print(\"sum= %d\"%sum1(lst))\n" +
                "print(\"avg = %f\"%average(lst)) \n" +
                "print(\"VAR = %f\"%var(lst,average(lst)))\n" +
                "\n" +
                "\n" +
                "def myfact(n):  \n" +
                "    assert n >= 0, \"Factorial not definied for negative values.\"  \n" +
                "    if n < 2:  \n" +
                "        return 1  \n" +
                "    else:  \n" +
                "        return n * myfact(n-1)\n" +
                "    \n" +
                "        \n" +
                "\n" +
                "def minusbio_pdf(x,p,r):\n" +
                "\n" +
                "    C=comb(x+r-1, r-1)\n" +
                "    return C*(p**r)*((1-p)**(x))  \n" +
                "\n" +
                "def manusbio_pdf(x,p,r):\n" +
                "\n" +
                "    C=comb(r, x)\n" +
                "\n" +
                "    return C*(p**x)*((1-p)**(r-x))  \n" +
                "\n" +
                "if var2>avg:\n" +
                "    r=1.0*avg*avg/(var2-avg)\n" +
                "    p=1.0*avg/(var2)\n" +
                "elif var2<avg:\n" +
                "    r=1.0*avg*avg/(avg-var2)\n" +
                "    p=1.0*(avg-var2)/avg\n" +
                "    \n" +
                "else:\n" +
                "    b=avg\n" +
                "    p=avg\n" +
                "print r, p\n" +
                "\n" +
                "\n" +
                "xx=0\n" +
                "for i in range(1,480):  \n" +
                "    xx=minusbio_pdf(i,p,r)+xx  \n" +
                "\n" +
                "    print(xx, p**r*(1-p)**(i))\n" +
                "xx=1-xx               \n" +
                "print(\"precent = %f\"%xx)   \n");

    }
}
