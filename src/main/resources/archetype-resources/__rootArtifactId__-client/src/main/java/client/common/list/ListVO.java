#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.client.common.list;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

import ${package}.client.common.result.Result;
import ${package}.client.common.util.VerifyUtils;


/**
 * Created by zhangyan on 06/02/2018.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ListVO<Value> {
	
    private List<Value> rows;
    private Long totalCount;
    private Long currentPage;
    private Long pageSize;

    public static <T> ListVO<T> list(Result<List<T>> ret) {
        return list(ret.getValue(), ret.getRemark());
    }

    public static <T> ListVO<T> list(List<T> list, String remark) {
        return list(list, VerifyUtils.isInteger(remark) ? Long.parseLong(remark) : null);
    }

    public static <T> ListVO<T> list(List<T> list, Integer totalCount) {
        return list(list, totalCount == null ? null : totalCount.longValue());
    }

    public static <T> ListVO<T> list(List<T> list, Long totalCount) {
        return list(list, totalCount, null, null);
    }

    public static <T> ListVO<T> list(List<T> list, Long totalCount, Long currentPage, Long pageSize) {
        ListVO<T> vos = new ListVO<>();
        vos.setRows(list);
        vos.setTotalCount(totalCount != null ? totalCount : (vos.rows == null ? 0 : vos.rows.size()));
        vos.setCurrentPage(currentPage);
        vos.setPageSize(pageSize);
        return vos;
    }
}
