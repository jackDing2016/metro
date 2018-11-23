package hello.service.impl;

import hello.constant.MetroConstant;
import hello.service.LineDataService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LineDataServiceImpl implements LineDataService {

    private static Map<String, Object> lineData_2 = new HashMap<>();

    private static Map<String, Object> lineData_11 = new HashMap<>();

    public static Map<String, Object> lineData_all = new HashMap<>();

    static {


        lineData_2.put( MetroConstant.KEY_IMPORTNUMBERARR  + "jiangsuRoad",
                new Integer[]{ 650, 733, 640, 584, 608, 508, 414, 333  } );

        lineData_2.put( MetroConstant.KEY_TRANSFERINTONUMBERARR  + "jiangsuRoad",
                new Integer[] {3636, 3636, 3636, 3636, 3636, 3636, 3636, 3636 });

        lineData_2.put( MetroConstant.KEY_EXPORTNUMBERARR  + "jiangsuRoad",
                new Integer[]{ 1098, 1360, 1649, 2073, 1513, 1230, 972, 747 });

        lineData_2.put( MetroConstant.KEY_TRANSFEROUTNUMBERARR  + "jiangsuRoad",
                new Integer[]{ 868, 868, 868, 868, 868, 868, 868, 868});

        lineData_2.put( MetroConstant.KEY_5MINIMPORTNUMBERARR + "jiangsuRoad",
                new Integer[]{
                        229, 244, 264, 285, 245, 248, 251, 255, 235, 190, 129, 154, 157, 181, 199, 181, 184, 162, 178, 130, 144, 150, 103, 108
                });

        lineData_2.put( MetroConstant.KEY_5MINEXPORTNUMBERARR + "jiangsuRoad",
                new Integer[]{
                        209, 331, 324, 381, 401, 332, 469, 567, 508, 426, 548, 540, 400, 471, 409, 428, 340, 342, 253, 302, 253, 230, 241, 230
                });


        lineData_11.put( MetroConstant.KEY_IMPORTNUMBERARR  + "jiangsuRoad",
                new Integer[]{ 525, 548, 462, 383, 414, 334, 252, 217  } );

        lineData_11.put( MetroConstant.KEY_TRANSFERINTONUMBERARR  + "jiangsuRoad",
                new Integer[]{ 868, 868, 868, 868, 868, 868, 868, 868});

        lineData_11.put( MetroConstant.KEY_EXPORTNUMBERARR  + "jiangsuRoad",
                new Integer[]{ 1061, 1184, 1611, 1897, 1425, 1183, 679, 564 });

        lineData_11.put( MetroConstant.KEY_TRANSFEROUTNUMBERARR  + "jiangsuRoad",
                new Integer[] {3636, 3636, 3636, 3636, 3636, 3636, 3636, 3636 });

        lineData_11.put( MetroConstant.KEY_5MINIMPORTNUMBERARR + "jiangsuRoad",
                new Integer[]{ 178, 163, 170, 166, 184, 121, 149, 151, 141, 114, 113, 105, 91, 118, 101, 140, 116, 137, 113, 103, 122, 86, 110, 89 });

        lineData_11.put( MetroConstant.KEY_5MINEXPORTNUMBERARR + "jiangsuRoad",
                new Integer[]{ 293, 379, 480, 429, 458, 551, 606, 657, 738, 675, 652, 565, 468, 450, 453, 408, 306, 208, 267, 228, 234, 195, 217, 165 });

        lineData_all.put( "lineData_2", lineData_2 );
        lineData_all.put( "lineData_11", lineData_11);

    }

    @Override
    public Map<String, Object> getLineDataMap(String lineCode) {
        return (Map<String, Object>) lineData_all.get( MetroConstant.KEY_LINEDATA + lineCode );
    }
}
