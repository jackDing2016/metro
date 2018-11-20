package hello.controller;

import hello.constant.MetroConstant;
import hello.entity.*;
import hello.param.StationAddParam;
import hello.service.PressureCalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/station")
public class StationController {


    @Autowired
    private PressureCalculateService pressureCalculateService;


    @GetMapping("/toAddDetailPage")
    public  String greeting() {
        return "part-detail-2";
    }

    @PostMapping(value = "/saveData",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String saveData(
            @RequestBody StationAddParam stationAddParam) {


        System.out.println( "saveData method" );
        System.out.println( "saveData method over" );
        return "success";
    }


    @PostMapping(value = "/calculateData",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String calculateData(
            @RequestBody StationAddParam stationAddParam) {

        // 站台压力  start
        // plateformArea:2083
        Plateform plateform =  stationAddParam.getPlateform();
        String lineCode = plateform.getLineCode() + "";
        String stationName = "jiangsuRoad";

        Map<String, Object> lineDataMap =
            pressureCalculateService.getLineDataMap( lineCode );

        pressureCalculateService.calPlateform( lineCode, plateform.getEffectiveArea(),
                Arrays.asList( ( Integer[] ) lineDataMap.get( MetroConstant.KEY_IMPORTNUMBERARR + stationName ) ),
                Arrays.asList( ( Integer[] ) lineDataMap.get( MetroConstant.KEY_TRANSFERINTONUMBERARR + stationName ) ),
                Arrays.asList( ( Integer[] ) lineDataMap.get( MetroConstant.KEY_EXPORTNUMBERARR + stationName ) ),
                Arrays.asList( ( Integer[] ) lineDataMap.get( MetroConstant.KEY_TRANSFEROUTNUMBERARR + stationName ) ));

        // 站台压力  end


        // 楼扶梯压力
        pressureCalculateService.calEscalator(lineCode, 4,
                Arrays.asList( ( Integer[] ) lineDataMap.get( MetroConstant.KEY_EXPORTNUMBERARR + stationName ) ),
                Arrays.asList( ( Integer[] ) lineDataMap.get( MetroConstant.KEY_TRANSFEROUTNUMBERARR + stationName ) ),
                0.5, 130.0);


        // 出入口压力
        pressureCalculateService.calEntranceAll( stationAddParam );


        // 闸机压力
        // 5分钟进出站数据
        Integer[] fiveMinImportData = new Integer[]{
                229, 244, 264, 285, 245, 248, 251, 255, 235, 190, 129, 154, 157, 181, 199, 181, 184, 162, 178, 130, 144, 150, 103, 108
        };

        Integer[] fiveMinExportData = new Integer[]{
                209, 331, 324, 381, 401, 332, 469, 567, 508, 426, 548, 540, 400, 471, 409, 428, 340, 342, 253, 302, 253, 230, 241, 230
        };


        Gate gate = stationAddParam.getGate();

        if ( gate != null ) {
            // 进站安检
            List<Double> gateImportResList =
                    pressureCalculateService.calGateImportExport( stationAddParam.getPlateform().getLineCode() + "",
                            Arrays.asList( fiveMinImportData ), 2, 1.5);

            // 出闸机
            List<Double> gateExportResList =
                    pressureCalculateService.calGateImportExport( stationAddParam.getPlateform().getLineCode() + "",
                            Arrays.asList( fiveMinExportData ), 8, 1.77);

            pressureCalculateService.calGate( stationAddParam.getPlateform().getLineCode() + "",
                    gateImportResList, gateExportResList,
                    gate.getWeitghEntrance(), gate.getWeightExit());
        }

        // 换乘通道压力

        List<TransferPassage> transferPassageList = stationAddParam.getTransferPassages();

        if ( transferPassageList != null && transferPassageList.size() > 0 ) {

            // 目前就算一个
            TransferPassage transferPassage = transferPassageList.get(0);

            Double b = 5.30227;
            Double n = 0.43253;
            Double tCommon = 130.0;

            pressureCalculateService.calTransferPassage( stationAddParam.getPlateform().getLineCode() + "",
                    transferPassage.getPassageLength(), tCommon, b, n, transferPassage.getTransferPassageFlowList()  );

        }




        return "success";
    }



}
