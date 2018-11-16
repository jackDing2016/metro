package hello.controller;

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
        // 数据
        Integer[] importNumberArr = new Integer[]{ 650, 733, 640, 584, 608, 508, 414, 333  };

        Integer[] transferIntoNumberArr = new Integer[] {3636, 3636, 3636, 3636, 3636, 3636, 3636, 3636 };

        Integer[] exportNumberArr = new Integer[]{ 1098, 1360, 1649, 2073, 1513, 1230, 972, 747 };

        Integer[] transferOutNumberArr = new Integer[]{ 868, 868, 868, 868, 868, 868, 868, 868};

        Integer[] importNumberArr2 = new Integer[]{ 525, 548, 462, 383, 414, 334, 252, 217  };

        Integer[] transferIntoNumberArr2 = new Integer[]{ 868, 868, 868, 868, 868, 868, 868, 868};

        Integer[] exportNumberArr2 = new Integer[]{ 1061, 1184, 1611, 1897, 1425, 1183, 679, 564 };

        Integer[] transferOutNumberArr2 = new Integer[] {3636, 3636, 3636, 3636, 3636, 3636, 3636, 3636 };

        // 站台压力 2号线  start
        pressureCalculateService.calPlateform( "2", 2083.2,
                Arrays.asList(importNumberArr), Arrays.asList(transferIntoNumberArr),
                Arrays.asList(exportNumberArr), Arrays.asList(transferOutNumberArr));
        // 站台压力 2号线  end

        // 站台压力 11 号线 start

        pressureCalculateService.calPlateform( "11", 2083.2,
                Arrays.asList(importNumberArr2), Arrays.asList(transferIntoNumberArr2),
                Arrays.asList(exportNumberArr2), Arrays.asList(transferOutNumberArr2));
        // 站台压力 11 号线 end

        Map<String, Object> plateformMap = pressureCalculateService.getPlateformResultMap();
        System.out.println( plateformMap );

        // 楼扶梯压力
        // 2号线
        pressureCalculateService.calEscalator("2", 4,
                Arrays.asList(exportNumberArr), Arrays.asList(transferOutNumberArr),
                0.5, 130.0);
        // 11号线
        pressureCalculateService.calEscalator("11", 4,
                Arrays.asList(exportNumberArr2), Arrays.asList(transferOutNumberArr2),
                0.5, 130.0);


        // 出入口压力
        pressureCalculateService.calEntranceAll( stationAddParam );


        // 闸机压力
        Gate gate = stationAddParam.getGate();

        if ( gate != null ) {
            List<Double> gateImportResList =
                    pressureCalculateService.calGateImport( stationAddParam.getPlateform().getLineCode() + "",
                            gate.getAvgArrIntTime(), gate.getAvgSecTime(),
                            gate.getSecQueueArea(), stationAddParam.getGateStatisticsList());

            List<Double> gateExportResList =
                    pressureCalculateService.calGateExport( stationAddParam.getPlateform().getLineCode() + "",
                            gate.getExitGateNum(), gate.getGateWidth(), gate.getMaxQueueLength(),
                            gate.getAvgPassExitGateTime(), gate.getAvgArrIntTimeExp(),
                            stationAddParam.getGateStatisticsList());

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
