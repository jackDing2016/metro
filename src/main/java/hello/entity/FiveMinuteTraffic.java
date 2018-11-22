package hello.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalTime;
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
 * @since 2018-11-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_five_minute_traffic")
public class FiveMinuteTraffic extends Model<FiveMinuteTraffic> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String code;

    private LocalTime startTime;

    private LocalTime endTime;

    private Integer trafficNum;

    private Integer dataOrder;

    private String lineCode;

    private String stationName;

    private Integer trafficType;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
