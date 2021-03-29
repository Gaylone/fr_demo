package top.sonjong.system.domain.base;

import com.github.pagehelper.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


@SuppressWarnings({"rawtypes", "unchecked"})
@Data
public class PageEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**当前页的页码值 */
    private Integer pageCurrent=1;
    /**页面大小*/
    private Integer pageSize=15;
    /**总行数(通过查询获得)*/

    private Integer rowCount=0;
    /**总页数(通过计算获得)*/

    private Integer pageCount=0;
    /**当前页记录*/
    private List<T> records;
}
