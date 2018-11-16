package hello.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.URL;

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
@TableName("t_entrance")
public class Entrance extends Model<Entrance> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String code;

    private String name;

    private Double entranceWidth;

    private Integer plateformCode;

    private transient List<EntranceStatistics> entranceStatisticsList;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
