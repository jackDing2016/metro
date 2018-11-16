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
 * @since 2018-10-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_gate")
public class Gate extends Model<Gate> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String code;

    private Double weitghEntrance;

    private Double weightExit;

    // 安检入口排队区域面积
    private Double secQueueArea;

    // 平均过检时间
    private Double avgSecTime;

    // 平均到达间隔时间
    private Double avgArrIntTime;



    // 出站闸机数量
    private Integer exitGateNum;

    // 出站平均过闸时间
    private Double avgPassExitGateTime;

    // 闸机宽度
    private Double gateWidth;

    // 闸机后可供排队长度
    private Double maxQueueLength;

    // 平均到达间隔时间(出闸)
    private Double avgArrIntTimeExp;



    private Integer plateformCode;



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
