package hello.controller;

import hello.constant.MetroConstant;
import hello.constant.PressureTypeEnum;
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
            Model model) {

        model.addAttribute( "lineCodeStr", lineCodeStr );
        model.addAttribute( "stationNameCode", stationNameCode );

        return "choose-part";
    }

    @GetMapping(value = "/toPressureAllTypeStatisticsPage")
    public String toPressureAllTypeStatisticsPage( Model model,@RequestParam(required = false) String per2Val,
                                                   @RequestParam(required = false) String per11Val ) {

        pressureCalculateService.calPressureLevelAvg("2", "11",
                Double.valueOf( per2Val ), Double.valueOf( per11Val ),
                (List<Double>) pressureCalculateService.getPlateformResultMap().get( MetroConstant.PREFFIX_LINE + 2 + MetroConstant.SUFFIX_LINE ),
                (List<Double>) pressureCalculateService.getPlateformResultMap().get( MetroConstant.PREFFIX_LINE + 11 + MetroConstant.SUFFIX_LINE),
                PressureTypeEnum.PLATEFORM);

        pressureCalculateService.calPressureLevelAvg("2", "11",
                Double.valueOf( per2Val ), Double.valueOf( per11Val ),
                (List<Double>) pressureCalculateService.getEscalatorResultMap().get( MetroConstant.PREFFIX_LINE + 2 + MetroConstant.SUFFIX_LINE ),
                (List<Double>) pressureCalculateService.getEscalatorResultMap().get( MetroConstant.PREFFIX_LINE + 11 + MetroConstant.SUFFIX_LINE),
                PressureTypeEnum.Escalator);

        pressureCalculateService.calPressureLevelAvg("2", "11",
                Double.valueOf( per2Val ), Double.valueOf( per11Val ),
                (List<Double>) pressureCalculateService.getGateResultMap().get( MetroConstant.PREFFIX_LINE + 2 + MetroConstant.SUFFIX_LINE ),
                (List<Double>) pressureCalculateService.getGateResultMap().get( MetroConstant.PREFFIX_LINE + 11 + MetroConstant.SUFFIX_LINE),
                PressureTypeEnum.GATE);

        pressureCalculateService.calPressureLevelAvg("2", "11",
                Double.valueOf( per2Val ), Double.valueOf( per11Val ),
                (List<Double>) pressureCalculateService.getEntranceResultMap().get( MetroConstant.PREFFIX_LINE + 2 + MetroConstant.SUFFIX_LINE ),
                (List<Double>) pressureCalculateService.getEntranceResultMap().get( MetroConstant.PREFFIX_LINE + 11 + MetroConstant.SUFFIX_LINE),
                PressureTypeEnum.ENTRANCE);

        pressureCalculateService.calPressureLevelAvg("2", "11",
                Double.valueOf( per2Val ), Double.valueOf( per11Val ),
                (List<Double>) pressureCalculateService.getTransferPassageResultMap().get( MetroConstant.PREFFIX_LINE + 2 + MetroConstant.SUFFIX_LINE ),
                (List<Double>) pressureCalculateService.getTransferPassageResultMap().get( MetroConstant.PREFFIX_LINE + 11 + MetroConstant.SUFFIX_LINE),
                PressureTypeEnum.TRANSFER_PASSAGE);

        model.addAttribute( "plateformResultMap", pressureCalculateService.getPlateformResultMap() );
        model.addAttribute( "escalatorResultMap", pressureCalculateService.getEscalatorResultMap() );
        model.addAttribute( "gateResultMap", pressureCalculateService.getGateResultMap() );
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
                                       @RequestParam( required = true) String stationNameCode) {

        String viewName = null;

        PressureTypeEnum pressureTypeEnum = null;


        if ( PressureTypeEnum.PLATEFORM.getCode() == pressureType ) {
            viewName = "result-part-zt";
            pressureTypeEnum = PressureTypeEnum.PLATEFORM;
        }
        else if ( PressureTypeEnum.ENTRANCE.getCode() == pressureType ) {
            viewName = "result-part-crk";
            pressureTypeEnum = PressureTypeEnum.ENTRANCE;
        }
        else if ( PressureTypeEnum.Escalator.getCode() == pressureType ) {
            viewName = "result-part-lft";
            pressureTypeEnum = PressureTypeEnum.Escalator;
        }
        else if ( PressureTypeEnum.GATE.getCode() == pressureType ) {
            viewName = "result-part-zj";
            pressureTypeEnum = PressureTypeEnum.GATE;
        }

        else if ( PressureTypeEnum.TRANSFER_PASSAGE.getCode() == pressureType ) {
            viewName = "result-part-hctd";
            pressureTypeEnum = PressureTypeEnum.TRANSFER_PASSAGE;
        }

        model.addAttribute( "per2Val", per2Val );
        model.addAttribute( "per11Val", per11Val );

        if ( !StringUtils.isEmpty( per2Val )) {

            if ( !StringUtils.isEmpty( lineCodeStr ) ) {

                String[] lineCodeArr = lineCodeStr.split( "," );

                if ( lineCodeArr.length == 2 ) {

                    // 计算加权平均值
                    Map<String, String> resultMap =
                        pressureLevelStatisticsService.calculateAvgLevelAndScore( lineCodeArr[0], lineCodeArr[1],
                                Double.valueOf( per2Val ), Double.valueOf( per11Val ),
                                stationNameCode, pressureTypeEnum);

                    model.addAttribute( "avgLevel", resultMap.get( "avgLevel") );
                    model.addAttribute( "avgScore", resultMap.get( "avgScore") );

                }

            }

        }

        return viewName;

    }

    @PostMapping(  value = "/calculateResult/{pressureType}" )
    public @ResponseBody Map<String, Object> calculateResult(
            @PathVariable( name = "pressureType") Integer pressureType
            ) {

        Map<String, Object> resultMap = null;

        if ( PressureTypeEnum.PLATEFORM.getCode() == pressureType )
            resultMap = pressureCalculateService.getPlateformResultMap();
        else if ( PressureTypeEnum.ENTRANCE.getCode() == pressureType )
            resultMap =  pressureCalculateService.getEntranceResultMap();
        else if ( PressureTypeEnum.Escalator.getCode() == pressureType)
            resultMap = pressureCalculateService.getEscalatorResultMap();
        else if ( PressureTypeEnum.GATE.getCode() == pressureType)
            resultMap = pressureCalculateService.getGateResultMap();
        else if ( PressureTypeEnum.TRANSFER_PASSAGE.getCode() == pressureType)
            resultMap = pressureCalculateService.getTransferPassageResultMap();

        return  resultMap;

    }

}
