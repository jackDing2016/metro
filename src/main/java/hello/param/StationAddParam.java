package hello.param;

import hello.entity.*;
import lombok.Data;

import java.util.List;

@Data
public class StationAddParam {

    private Plateform plateform;

    private Escalator escalator;

    private List<PlateformDevice> plateformDevices;

    private Gate gate;

    private List<Entrance> entrances;

    private List<TransferPassage> transferPassages;

    private List<GateStatistics> gateStatisticsList;

}
