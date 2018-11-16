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
@TableName("t_gate_statistics")
public class GateStatistics extends Model<GateStatistics> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String code;

    // 最大排队人数（安检）
    private Integer maxSecQueueNumber;

    // 最大排队时间 (安检)
    private Double maxSecQueueTime;

    // 最大排队人数 (出闸) "12,65,12"
    private String maxExitNumber;

    // 最大排队时间 (出闸) "12,11,67"
    private String maxExitQueueTime;

    private Integer gateCode;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
