package hello.controller;

import hello.constant.MetroConstant;
import hello.entity.*;
import hello.param.StationAddParam;
import hello.service.LineDataService;
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

    @Autowired
    private LineDataService lineDataService;


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


        Plateform plateform =  stationAddParam.getPlateform();
        String lineCode = plateform.getLineCode() + "";
        String stationName = "jiangsuRoad";

        Map<String, Object> lineDataMap =
            lineDataService.getLineDataMap( lineCode );

        // 站台压力  start
        // plateformArea:2083
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

        Gate gate = stationAddParam.getGate();

        if ( gate != null ) {
            // 进站安检
            List<Double> gateImportResList =
                    pressureCalculateService.calGateImportExport( lineCode,
                            Arrays.asList( ( Integer[] ) lineDataMap.get( MetroConstant.KEY_5MINIMPORTNUMBERARR + stationName ) ),
                            gate.getSecNum(), gate.getAvgSecTime());

            // 出闸机
            List<Double> gateExportResList =
                    pressureCalculateService.calGateImportExport( lineCode,
                            Arrays.asList( ( Integer[] ) lineDataMap.get( MetroConstant.KEY_5MINEXPORTNUMBERARR + stationName ) ),
                            gate.getExitGateNum(), gate.getAvgPassExitGateTime());

            pressureCalculateService.calGate( lineCode,
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
