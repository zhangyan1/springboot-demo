#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dal.common.wrapper;

import ${package}.client.common.domain.Domain;
import ${package}.client.common.error.ErrorInfo;
import ${package}.client.common.list.ListVO;
import ${package}.client.common.query.Query;
import ${package}.client.common.result.Result;
import ${package}.dal.common.mapper.Mapper;

import java.util.List;

/**
 * Created by ${userName} on ${today}.
 */
public abstract class Wrapper<D extends Domain, Q extends Query> {
    protected abstract Mapper<D, Q> getMapper();

    public Result<Long> count(Q query) {
        return MapperWrapper.count(getMapper(), query);
    }

    public Result<Long> count(Q query, ErrorInfo error) {
        return MapperWrapper.count(getMapper(), query, error);
    }

    public Result<ListVO<D>> find(Q query) {
        return MapperWrapper.find(getMapper(), query);
    }

    public Result<ListVO<D>> find(Q query, ErrorInfo error) {
        return MapperWrapper.find(getMapper(), query, error);
    }

    public Result<D> get(Q query) {
        return MapperWrapper.get(getMapper(), query);
    }

    public Result<D> get(Q query, ErrorInfo error) {
        return MapperWrapper.get(getMapper(), query, error);
    }

    public Result<D> insert(D domain) {
        return MapperWrapper.insert(getMapper(), domain);
    }

    public Result<D> insert(D domain, ErrorInfo error) {
        return MapperWrapper.insert(getMapper(), domain, error);
    }

    public Result<Long> batchInsert(List<D> domains) {
        return MapperWrapper.batchInsert(getMapper(), domains);
    }

    public Result<Long> batchInsert(List<D> domains, ErrorInfo error) {
        return MapperWrapper.batchInsert(getMapper(), domains, error);
    }

    public Result<D> update(D domain) {
        return MapperWrapper.update(getMapper(), domain);
    }

    public Result<D> update(D domain, ErrorInfo error) {
        return MapperWrapper.update(getMapper(), domain, error);
    }

    public Result<D> delete(D domain) {
        return MapperWrapper.delete(getMapper(), domain);
    }

    public Result<D> delete(D domain, ErrorInfo error) {
        return MapperWrapper.delete(getMapper(), domain, error);
    }

}