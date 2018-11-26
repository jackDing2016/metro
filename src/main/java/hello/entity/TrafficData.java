package hello.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jack
 * @since 2018-11-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_traffic_data")
public class TrafficData extends Model<TrafficData> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String code;

    private String startTime;

    private String endTime;

    private Integer trafficNum;

    private Integer dataOrder;

    private String lineCode;

    private String stationName;

    private Integer trafficType;

    private Integer timeIntervalType;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
