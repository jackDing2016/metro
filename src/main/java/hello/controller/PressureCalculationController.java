package hello.controller;

import hello.constant.MetroConstant;
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
        String lineCodeSecond = lineCodeArr[1];


        Map<String, Object> plateformResultMap =
            pressureCalculateService.calPressureLevelAvg( lineCodeFirst, lineCodeSecond,
                    Double.valueOf( per2Val ), Double.valueOf( per11Val ),
                    pressureLevelResultService.getDataList( lineCodeFirst, stationNameCode, PressureTypeEnum.PLATEFORM ),
                    pressureLevelResultService.getDataList( lineCodeSecond, stationNameCode, PressureTypeEnum.PLATEFORM ),
                    PressureTypeEnum.PLATEFORM, stationNameCode);

        plateformResultMap.putAll( pressureLevelStatisticsService.calculateAvgLevelAndScore( lineCodeFirst, lineCodeSecond,
                Double.valueOf( per2Val ), Double.valueOf( per11Val), stationNameCode, PressureTypeEnum.PLATEFORM) );



        Map<String, Object> escalatorResultMap =
                    pressureCalculateService.calPressureLevelAvg( lineCodeFirst, lineCodeSecond,
                    Double.valueOf( per2Val ), Double.valueOf( per11Val ),
                    pressureLevelResultService.getDataList( lineCodeFirst, stationNameCode, PressureTypeEnum.Escalator  ),
                    pressureLevelResultService.getDataList( lineCodeSecond, stationNameCode, PressureTypeEnum.Escalator  ),
                    PressureTypeEnum.Escalator, stationNameCode );

        escalatorResultMap.putAll( pressureLevelStatisticsService.calculateAvgLevelAndScore( lineCodeFirst, lineCodeSecond,
                Double.valueOf( per2Val ), Double.valueOf( per11Val), stationNameCode, PressureTypeEnum.Escalator ) );

        Map<String, Object> gateResultMap =
                    pressureCalculateService.calPressureLevelAvg( lineCodeFirst, lineCodeSecond,
                    Double.valueOf( per2Val ), Double.valueOf( per11Val ),
                    pressureLevelResultService.getDataList( lineCodeFirst, stationNameCode, PressureTypeEnum.GATE  ),
                    pressureLevelResultService.getDataList( lineCodeSecond, stationNameCode, PressureTypeEnum.GATE  ),
                    PressureTypeEnum.GATE, stationNameCode );

        gateResultMap.putAll( pressureLevelStatisticsService.calculateAvgLevelAndScore( lineCodeFirst, lineCodeSecond,
                Double.valueOf( per2Val ), Double.valueOf( per11Val), stationNameCode, PressureTypeEnum.GATE ) );



        model.addAttribute( "plateformResultMap", plateformResultMap );
        model.addAttribute( "escalatorResultMap", escalatorResultMap );
        model.addAttribute( "gateResultMap", gateResultMap );
        model.addAttribute( "entranceResultMap", pressureCalculateService.getEntranceResultMap() );
        model.addAttribute( "transferPassageResultMap", pressureCalculateService.getTransferPassageResultMap() );

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


                Map<String, String> resultMap = null;

                if ( lineCodeArr.length == 2 ) {

                    // 计算加权平均值
                    resultMap =
                        pressureLevelStatisticsService.calculateAvgLevelAndScore( lineCodeArr[0], lineCodeArr[1],
                                Double.valueOf( per2Val ), Double.valueOf( per11Val ),
                                stationNameCode, pressureTypeEnum);
                }
                else {
                    resultMap =
                            pressureLevelStatisticsService.calculateAvgLevelAndScore( lineCodeArr[0], null,
                                    Double.valueOf( per2Val ), null,
                                    stationNameCode, pressureTypeEnum);
                }

                model.addAttribute( "avgLevel", resultMap.get( "avgLevel") );
                model.addAttribute( "avgScore", resultMap.get( "avgScore") );

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
            @RequestParam( required = true ) String stationNameCode
            ) {

        Map<String, Object> resultMap = null;

        String[] lineCodeArr = lineCodeStr.split( "," );

        PressureTypeEnum pressureTypeEnum =
                PressureTypeEnum.getByCode( pressureType );

        resultMap = pressureLevelResultService.getDataLevelListMap( lineCodeArr,
                stationNameCode, pressureTypeEnum);

        return  resultMap;

    }

    @GetMapping(  value = "/calculateResultForTransferPassage")
    public @ResponseBody List<String> calculateResultForTransferPassage(
            @RequestParam( required = true) String lineCodeStr,
            @RequestParam( required = true ) String stationNameCode
    ) {

        Map<String, Object> resultMap = null;

        String[] lineCodeArr = lineCodeStr.split( "," );

        List<Double> dataListFirst = pressureLevelResultService.getDataList( lineCodeArr[0],
                stationNameCode, PressureTypeEnum.TRANSFER_PASSAGE  );

        List<Double> dataListSecond = pressureLevelResultService.getDataList( lineCodeArr[1],
                stationNameCode, PressureTypeEnum.TRANSFER_PASSAGE);

        List<PressureLevelResult> pressureLevelResultList =
            pressureCalculateService.calAvgTransferPassage( dataListFirst, dataListSecond, stationNameCode );

        List<String> resultLevelList = new ArrayList<>( pressureLevelResultList.size() );

        for ( PressureLevelResult pressureLevelResult : pressureLevelResultList ) {
            resultLevelList.add( pressureLevelResult.getResultLevel() );
        }


        return  resultLevelList;

    }

}
