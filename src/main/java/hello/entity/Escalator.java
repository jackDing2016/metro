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
 * @since 2018-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_escalator")
public class Escalator extends Model<Escalator> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String code;

    private Double plateEffLength;

    // 站台楼扶梯组数
    private Integer plateEscalatorNum;

    // 楼梯总宽度
    private Double floorWidth;

    // 上行扶梯总宽度
    private Double upEscalatorWidth;

    private Integer plateformCode;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
