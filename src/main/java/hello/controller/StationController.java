package hello.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hello.constant.*;
import hello.entity.*;
import hello.param.StationAddParam;
import hello.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @Autowired
    private TrafficDataService trafficDataService;

    @Autowired
    private TransferDataService transferDataService;

    @Autowired
    private GateService gateService;


    @GetMapping("/toAddDetailPage")
    public  String toAddDetailPage(Model model,
                                   @RequestParam String stationName,
                                   @RequestParam String stationNameCode,
                                   @RequestParam String lineCodeStr ) {
        model.addAttribute( "stationName", stationName );
        model.addAttribute( "stationNameCode", stationNameCode );

        String[] lineCodeArr = lineCodeStr.split( "," );

        model.addAttribute( "lineCodeFirst", lineCodeArr[0] );
        if ( lineCodeArr.length > 1 )
            model.addAttribute( "lineCodeSecond", lineCodeArr[1] );

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
        // 比如jiangsulu
        String stationNameCode = plateform.getStationNameCode();

        boolean isNeedCalculatHolidayData = false;

        // 如果是8号线中华艺术宫站 还要计算活动日的数据
        if ( "8".equals( lineCode ) && "zhonghuayishugong".equals( stationNameCode ) )
            isNeedCalculatHolidayData = true;


        // 站台压力  start

        pressureCalculateService.calPlateform( lineCode, plateform.getEffectiveArea(),
                trafficDataService.getDataList( TrafficTypeEnum.IMPORT, TimeIntervalTypeEnum.FIFTEEN_MINUTE,
                        3, 26, lineCode, stationNameCode, PressureTimeTypeEnum.WEEKDAY),
                transferDataService.getDataList( lineCode, stationNameCode, TransferTypeEnum.TRANSFER_IN, PressureTimeTypeEnum.WEEKDAY),
                trafficDataService.getDataList( TrafficTypeEnum.EXPORT, TimeIntervalTypeEnum.FIFTEEN_MINUTE,
                        3, 26, lineCode, stationNameCode, PressureTimeTypeEnum.WEEKDAY),
                transferDataService.getDataList( lineCode, stationNameCode, TransferTypeEnum.TRANSFER_OUT, PressureTimeTypeEnum.WEEKDAY),
                stationNameCode, plateform.getHeadWay(), PressureTimeTypeEnum.WEEKDAY
                );

        // 如果是8号线中华艺术宫站 还要计算活动日的数据
        if ( isNeedCalculatHolidayData ) {

            pressureCalculateService.calPlateform( lineCode, plateform.getEffectiveArea(),
                    trafficDataService.getDataList( TrafficTypeEnum.IMPORT, TimeIntervalTypeEnum.FIFTEEN_MINUTE,
                            3, 26, lineCode, stationNameCode, PressureTimeTypeEnum.HOLIDAY),
                    transferDataService.getDataList( lineCode, stationNameCode, TransferTypeEnum.TRANSFER_IN, PressureTimeTypeEnum.HOLIDAY),
                    trafficDataService.getDataList( TrafficTypeEnum.EXPORT, TimeIntervalTypeEnum.FIFTEEN_MINUTE,
                            3, 26, lineCode, stationNameCode, PressureTimeTypeEnum.HOLIDAY),
                    transferDataService.getDataList( lineCode, stationNameCode, TransferTypeEnum.TRANSFER_OUT, PressureTimeTypeEnum.HOLIDAY),
                    stationNameCode, plateform.getHeadWay(), PressureTimeTypeEnum.HOLIDAY
            );

        }

        // 站台压力  end


        // 楼扶梯压力

        Escalator escalator = stationAddParam.getEscalator();

        if ( escalator != null ) {

            pressureCalculateService.calEscalator(lineCode, escalator.getPlateEscalatorNum(),
                    trafficDataService.getDataList( TrafficTypeEnum.EXPORT,  TimeIntervalTypeEnum.FIFTEEN_MINUTE,
                            null, null, lineCode, stationNameCode, PressureTimeTypeEnum.WEEKDAY ),
                    transferDataService.getDataList( lineCode, stationNameCode, TransferTypeEnum.TRANSFER_OUT ,
                            PressureTimeTypeEnum.WEEKDAY) ,
                    0.5, escalator.getPlateEffLength(), escalator.getUpEscalatorWidth(), escalator.getFloorWidth(),
                    stationNameCode, PressureTimeTypeEnum.WEEKDAY);

            if ( isNeedCalculatHolidayData ) {

                pressureCalculateService.calEscalator(lineCode, escalator.getPlateEscalatorNum(),
                        trafficDataService.getDataList( TrafficTypeEnum.EXPORT,  TimeIntervalTypeEnum.FIFTEEN_MINUTE,
                                null, null, lineCode, stationNameCode, PressureTimeTypeEnum.HOLIDAY ),
                        transferDataService.getDataList( lineCode, stationNameCode, TransferTypeEnum.TRANSFER_OUT ,
                                PressureTimeTypeEnum.HOLIDAY) ,
                        0.5, escalator.getPlateEffLength(), escalator.getUpEscalatorWidth(), escalator.getFloorWidth(),
                        stationNameCode, PressureTimeTypeEnum.HOLIDAY);

            }


        }

        // 出入口压力
        pressureCalculateService.calEntranceAll( stationAddParam );

        // 闸机压力
        Gate gate = stationAddParam.getGate();

        if ( gate != null ) {
            // 进站安检
            List<Double> gateImportResList =
                    pressureCalculateService.calGateImportExport( lineCode,
                            trafficDataService.getDataList(TrafficTypeEnum.IMPORT, TimeIntervalTypeEnum.FIVE_MINUTE,
                                    3, 26, lineCode, stationNameCode, PressureTimeTypeEnum.WEEKDAY),
                            gate.getSecNum(), gate.getAvgSecTime(), PressureTimeTypeEnum.WEEKDAY);
            // 出闸机
            List<Double> gateExportResList =
                    pressureCalculateService.calGateImportExport( lineCode,
                            trafficDataService.getDataList(TrafficTypeEnum.EXPORT, TimeIntervalTypeEnum.FIVE_MINUTE,
                                    null, null, lineCode, stationNameCode, PressureTimeTypeEnum.WEEKDAY),
                            gate.getExitGateNum(), gate.getAvgPassExitGateTime(), PressureTimeTypeEnum.WEEKDAY);
            // 计算闸机
            pressureCalculateService.calGate( lineCode,
                    gateImportResList, gateExportResList,
                    gate.getWeitghEntrance(), gate.getWeightExit(), stationNameCode, PressureTimeTypeEnum.WEEKDAY );

            if ( isNeedCalculatHolidayData ) {

                // 进站安检
                List<Double> gateImportResList2 =
                        pressureCalculateService.calGateImportExport( lineCode,
                                trafficDataService.getDataList(TrafficTypeEnum.IMPORT, TimeIntervalTypeEnum.FIVE_MINUTE,
                                        3, 26, lineCode, stationNameCode, PressureTimeTypeEnum.HOLIDAY),
                                gate.getSecNum(), gate.getAvgSecTime(), PressureTimeTypeEnum.HOLIDAY);
                // 出闸机
                List<Double> gateExportResList2 =
                        pressureCalculateService.calGateImportExport( lineCode,
                                trafficDataService.getDataList(TrafficTypeEnum.EXPORT, TimeIntervalTypeEnum.FIVE_MINUTE,
                                        null, null, lineCode, stationNameCode, PressureTimeTypeEnum.HOLIDAY),
                                gate.getExitGateNum(), gate.getAvgPassExitGateTime(), PressureTimeTypeEnum.HOLIDAY);
                // 计算闸机
                pressureCalculateService.calGate( lineCode,
                        gateImportResList2, gateExportResList2,
                        gate.getWeitghEntrance(), gate.getWeightExit(), stationNameCode, PressureTimeTypeEnum.HOLIDAY );

            }


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
                    transferPassage.getPassageLength(), tCommon, b, n, transferPassage.getTransferPassageFlowList(), stationNameCode  );

        }

        return "success";
    }

    @GetMapping( value = "/testSaveGate")
    @ResponseBody
    public String testSaveGate() {

        Gate gate = new Gate();

        gate.setWeitghEntrance( 0.5 );

        gateService.save( gate );

        return "save success";

    }

}
