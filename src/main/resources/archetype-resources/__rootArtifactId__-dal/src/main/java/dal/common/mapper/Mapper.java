#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dal.common.mapper;

import ${package}.client.common.domain.Domain;
import ${package}.client.common.query.Query;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ${userName} on ${today}.
 */
public interface Mapper<D extends Domain, Q extends Query> {
    long count(Q query);

    long insert(D value);

    long batchInsert(List<D> value);

    long update(D value);

    long delete(D value);
    
    List<D> find(Q query);
    
    D get(Q query);
}
