package hello.controller;

import hello.constant.MetroConstant;
import hello.constant.PressureTimeTypeEnum;
import hello.constant.PressureTypeEnum;
import hello.entity.PressureLevelResult;
import hello.param.PressureCalculateParam;
import hello.service.PressureCalculateService;
import hello.service.PressureLevelResultService;
import hello.service.PressureLevelStatisticsService;
import org.python.antlr.ast.Str;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pressureCalculation")
public class PressureCalculationController {

    @Autowired
    private PressureCalculateService pressureCalculateService;

    @Autowired
    private PressureLevelStatisticsService pressureLevelStatisticsService;

    @Autowired
    private PressureLevelResultService pressureLevelResultService;

//    @PostMapping(value = "/toPressureTypeSelectPage",
//            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    public String toPressureTypeSelectPage(
//            Model model, @RequestBody MultiValueMap<String, String> formData) {
//        System.out.println( formData );
//        model.addAttribute( "dataStrArr", formData.get( "pressureCalculateParam" ) );
//        return "choose-part";
//    }

    @GetMapping(value = "/toPressureTypeSelectPage")
    public String toPressureTypeSelectPage(
            @RequestParam( required = true) String lineCodeStr,
            @RequestParam( required = true) String stationNameCode,
            @RequestParam( required = true) String stationName,
            Model model) {

        model.addAttribute( "lineCodeStr", lineCodeStr );
        model.addAttribute( "stationNameCode", stationNameCode );
        model.addAttribute( "stationName", stationName );

        return "choose-part";
    }

    @GetMapping(value = "/toPressureAllTypeStatisticsPage")
    public String toPressureAllTypeStatisticsPage( Model model,
                                                   @RequestParam(required = false) String per2Val,
                                                   @RequestParam(required = false) String per11Val,
                                                   @RequestParam String lineCodeStr,
                                                   @RequestParam String stationNameCode) {

        String[] lineCodeArr = lineCodeStr.split( "," );

        String lineCodeFirst = lineCodeArr[0];
        String lineCodeSecond = "";

        // 只有一条线路的情况
        if ( lineCodeArr.length > 1 )
            lineCodeSecond = lineCodeArr[1];
        if (org.apache.commons.lang3.StringUtils.isEmpty( per11Val ))
            per11Val = "0";

        Map<String, Object> plateformResultMap =
            pressureCalculateService.calPressureLevelAvg( lineCodeFirst, lineCodeSecond,
                    Double.valueOf( per2Val ), Double.valueOf( per11Val ),
                    pressureLevelResultService.getDataList( lineCodeFirst, stationNameCode, PressureTypeEnum.PLATEFORM ,
                            PressureTimeTypeEnum.WEEKDAY),
                    pressureLevelResultService.getDataList( lineCodeSecond, stationNameCode, PressureTypeEnum.PLATEFORM,
                            PressureTimeTypeEnum.WEEKDAY),
                    PressureTypeEnum.PLATEFORM, stationNameCode);

        plateformResultMap.putAll( pressureLevelStatisticsService.calculateAvgLevelAndScore( lineCodeFirst, lineCodeSecond,
                Double.valueOf( per2Val ), Double.valueOf( per11Val), stationNameCode, PressureTypeEnum.PLATEFORM,
                PressureTimeTypeEnum.WEEKDAY) );



        Map<String, Object> escalatorResultMap =
                    pressureCalculateService.calPressureLevelAvg( lineCodeFirst, lineCodeSecond,
                    Double.valueOf( per2Val ), Double.valueOf( per11Val ),
                    pressureLevelResultService.getDataList( lineCodeFirst, stationNameCode, PressureTypeEnum.Escalator,
                            PressureTimeTypeEnum.WEEKDAY  ),
                    pressureLevelResultService.getDataList( lineCodeSecond, stationNameCode, PressureTypeEnum.Escalator,
                            PressureTimeTypeEnum.WEEKDAY),
                    PressureTypeEnum.Escalator, stationNameCode );

        escalatorResultMap.putAll( pressureLevelStatisticsService.calculateAvgLevelAndScore( lineCodeFirst, lineCodeSecond,
                Double.valueOf( per2Val ), Double.valueOf( per11Val), stationNameCode, PressureTypeEnum.Escalator ,
                PressureTimeTypeEnum.WEEKDAY) );

        Map<String, Object> gateResultMap =
                    pressureCalculateService.calPressureLevelAvg( lineCodeFirst, lineCodeSecond,
                    Double.valueOf( per2Val ), Double.valueOf( per11Val ),
                    pressureLevelResultService.getDataList( lineCodeFirst, stationNameCode, PressureTypeEnum.GATE,
                            PressureTimeTypeEnum.WEEKDAY),
                    pressureLevelResultService.getDataList( lineCodeSecond, stationNameCode, PressureTypeEnum.GATE ,
                            PressureTimeTypeEnum.WEEKDAY),
                    PressureTypeEnum.GATE, stationNameCode );

        gateResultMap.putAll( pressureLevelStatisticsService.calculateAvgLevelAndScore( lineCodeFirst, lineCodeSecond,
                Double.valueOf( per2Val ), Double.valueOf( per11Val), stationNameCode, PressureTypeEnum.GATE ,
                PressureTimeTypeEnum.WEEKDAY) );

        // 换乘通道 start
        Map<String, Object> transferPassageResultMap = null;
        if ( lineCodeArr.length > 1 ) {
            List<Double> dataListFirst = pressureLevelResultService.getDataList( lineCodeArr[0],
                    stationNameCode, PressureTypeEnum.TRANSFER_PASSAGE , PressureTimeTypeEnum.WEEKDAY );
            List<Double> dataListSecond = pressureLevelResultService.getDataList( lineCodeArr[1],
                    stationNameCode, PressureTypeEnum.TRANSFER_PASSAGE, PressureTimeTypeEnum.WEEKDAY);
            List<PressureLevelResult> pressureLevelResultList =
                    pressureCalculateService.calAvgTransferPassage( dataListFirst, dataListSecond, stationNameCode );
            List<String> resultLevelList = new ArrayList<>( pressureLevelResultList.size() );

            Double transferPassageAvgScore = 0.0;
            String transferPassageAvgLevel = "";

            for ( PressureLevelResult pressureLevelResult : pressureLevelResultList ) {
                resultLevelList.add( pressureLevelResult.getResultLevel() );
                transferPassageAvgScore += pressureLevelResult.getResultValue();
            }
            transferPassageAvgLevel =
                    pressureCalculateService.calTransferPassageLevel( transferPassageAvgScore );

            transferPassageAvgScore /= pressureLevelResultList.size() ;
            transferPassageAvgScore *= transferPassageAvgScore * 100;


            transferPassageResultMap = new HashMap<>();
            transferPassageResultMap.put( "dataList", resultLevelList );
            transferPassageResultMap.put( "avgScore",  transferPassageAvgScore);
            transferPassageResultMap.put( "avgLevel",  transferPassageAvgLevel);
        }

        // 换乘通道 end

        // 出入口 start
        List<Double> entranceDatas =
        pressureLevelResultService.getDataList( lineCodeFirst,
                stationNameCode, PressureTypeEnum.ENTRANCE, PressureTimeTypeEnum.WEEKDAY );

        Map<String, Object> entranceResultMap = new HashMap<>();



        List<String> entranceLevelList = new ArrayList<>();

        for ( Double dataValue : entranceDatas ) {
            entranceLevelList.add( pressureCalculateService.calLevel( dataValue ) );
        }

        entranceResultMap.put( "dataList",  entranceLevelList );
        entranceResultMap.put( "avgScore",  0 );
        entranceResultMap.put( "avgLevel",  "E" );



        model.addAttribute( "plateformResultMap", plateformResultMap );
        model.addAttribute( "escalatorResultMap", escalatorResultMap );
        model.addAttribute( "gateResultMap", gateResultMap );
        model.addAttribute( "entranceResultMap", entranceResultMap );
        model.addAttribute( "transferPassageResultMap", transferPassageResultMap );

        return "result-new";
    }

    @GetMapping(value = "/toPressureImgtatisticsPage")
    public String toPressureImgtatisticsPage() {
        return "result-imgsample";
    }



    @GetMapping( "/toPressureCalculationResultPage/{pressureType}" )
    public String toPressureResultPage(@PathVariable(name = "pressureType") Integer pressureType, Model model,
                                       @RequestParam( required = false ) String per2Val,
                                       @RequestParam( required = false ) String per11Val,
                                       @RequestParam( required = true) String lineCodeStr,
                                       @RequestParam( required = true) String stationNameCode,
                                       @RequestParam( required = true) String stationName) {

        String viewName = null;

        PressureTypeEnum pressureTypeEnum = null;

        if ( PressureTypeEnum.PLATEFORM.getCode() == pressureType ||
                PressureTypeEnum.Escalator.getCode() == pressureType ||
                PressureTypeEnum.GATE.getCode() == pressureType ||
                PressureTypeEnum.ENTRANCE.getCode() == pressureType) {
            viewName = "result-part-zt";
            pressureTypeEnum = PressureTypeEnum.getByCode( pressureType );
        }

        else if ( PressureTypeEnum.TRANSFER_PASSAGE.getCode() == pressureType ) {
            viewName = "result-part-hctd";
            pressureTypeEnum = PressureTypeEnum.TRANSFER_PASSAGE;
        }

        model.addAttribute( "lineCodeArr", lineCodeStr.split( "," ) );
        model.addAttribute( "lineCodeStr", lineCodeStr );
        model.addAttribute( "stationNameCode", stationNameCode );
        model.addAttribute( "stationName", stationName );
        model.addAttribute( "pressureTypeName", PressureTypeEnum.getByCode( pressureType ).getName() );
        model.addAttribute( "pressureTypeCode", pressureType );

        if ( !StringUtils.isEmpty( per2Val )) {

            if ( !StringUtils.isEmpty( lineCodeStr ) ) {

                String[] lineCodeArr = lineCodeStr.split( "," );

                Map<String, Double> lineWeightMap = new HashMap<>();

                // 暂时从简
                Integer index = 0;
                for ( String lineCode : lineCodeArr ) {

                    if ( index ==  0 )
                        lineWeightMap.put( lineCode, Double.valueOf( per2Val ) );
                    else
                        lineWeightMap.put( lineCode, Double.valueOf( per11Val ) );

                    index++;

                }

                model.addAttribute( "lineWeightMap", lineWeightMap );

            }

        }

        return viewName;

    }

    /**
     * 用于站台、楼扶梯、闸机、出入口
     * @param pressureType
     * @param lineCodeStr
     * @param stationNameCode
     * @return
     */
    @GetMapping(  value = "/calculateResult/{pressureType}")
    public @ResponseBody Map<String, Object> calculateResult(
            @PathVariable( name = "pressureType") Integer pressureType,
            @RequestParam( required = true) String lineCodeStr,
            @RequestParam( required = true ) String stationNameCode,
            @RequestParam( required = false ) String per2Val,
            @RequestParam( required = false ) String per11Val,
            @RequestParam( required = true ) Integer pressureTimeType
            ) {

        Map<String, Object> dataResultMap = null;

        String[] lineCodeArr = lineCodeStr.split( "," );

        PressureTimeTypeEnum pressureTimeTypeEnum = PressureTimeTypeEnum.getByCode( pressureTimeType );

        PressureTypeEnum pressureTypeEnum =
                PressureTypeEnum.getByCode( pressureType );

        dataResultMap = pressureLevelResultService.getDataLevelListMap( lineCodeArr,
                stationNameCode, pressureTypeEnum, pressureTimeTypeEnum);

        Map<String, String> avgScoreLevelMap = null;

        if ( lineCodeArr.length == 2 ) {

            // 计算加权平均值
            avgScoreLevelMap =
                    pressureLevelStatisticsService.calculateAvgLevelAndScore( lineCodeArr[0], lineCodeArr[1],
                            Double.valueOf( per2Val ), Double.valueOf( per11Val ),
                            stationNameCode, pressureTypeEnum, pressureTimeTypeEnum);
        }
        else {
            avgScoreLevelMap =
                    pressureLevelStatisticsService.calculateAvgLevelAndScore( lineCodeArr[0], null,
                            Double.valueOf( per2Val ), null,
                            stationNameCode, pressureTypeEnum, pressureTimeTypeEnum);
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put( "dataResultMap", dataResultMap );
        resultMap.put( "avgScoreLevelMap", avgScoreLevelMap );

        return  resultMap;

    }

    @GetMapping(  value = "/calculateResultForTransferPassage")
    public @ResponseBody List<String> calculateResultForTransferPassage(
            @RequestParam( required = true) String lineCodeStr,
            @RequestParam( required = true ) String stationNameCode
    ) {

        String[] lineCodeArr = lineCodeStr.split( "," );

        List<Double> dataListFirst = pressureLevelResultService.getDataList( lineCodeArr[0],
                stationNameCode, PressureTypeEnum.TRANSFER_PASSAGE , PressureTimeTypeEnum.WEEKDAY );

        List<Double> dataListSecond = pressureLevelResultService.getDataList( lineCodeArr[1],
                stationNameCode, PressureTypeEnum.TRANSFER_PASSAGE, PressureTimeTypeEnum.WEEKDAY);

        List<PressureLevelResult> pressureLevelResultList =
            pressureCalculateService.calAvgTransferPassage( dataListFirst, dataListSecond, stationNameCode );

        List<String> resultLevelList = new ArrayList<>( pressureLevelResultList.size() );

        for ( PressureLevelResult pressureLevelResult : pressureLevelResultList ) {
            resultLevelList.add( pressureLevelResult.getResultLevel() );
        }




        return  resultLevelList;

    }

}
