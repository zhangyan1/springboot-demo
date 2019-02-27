#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dal.common.wrapper;

import ${package}.client.common.domain.Domain;
import ${package}.client.common.error.ErrorInfo;
import ${package}.client.common.exception.DatabaseSqlExecuteException;
import ${package}.client.common.exception.ParamterInvalidException;
import ${package}.client.common.list.ListVO;
import ${package}.client.common.query.Query;
import ${package}.client.common.result.Result;
import ${package}.client.common.result.Result;
import ${package}.dal.common.mapper.Mapper;
import ${package}.client.common.list.ListVO;
import ${package}.client.common.result.Result;

import java.util.List;
import java.util.Collections;

/**
 * Created by ${userName} on ${today}.
 */
public class MapperWrapper {
    public static < D extends Domain, Q extends Query> Result<Long> count(Mapper<D, Q> mapper, Q query) {
        return count(mapper, query, null);
    }

    public static <D extends Domain, Q extends Query> Result<Long> count(Mapper<D, Q> mapper, Q query, ErrorInfo error) {
        if (mapper == null) {
            throw new ParamterInvalidException(new ErrorInfo(10000, "MAPPER_NULL_COUNT", "mapper is null"));
        }
        if (query == null) {
            throw new ParamterInvalidException(new ErrorInfo(10001, "QUERY_NULL_COUNT", "query is null"));
        }
        try {
            return Result.success(mapper.count(query));
        } catch (Throwable e) {
            if (error == null) {
                error = new ErrorInfo(10002, "SQL_ERROR_COUNT", "sql failed execute");
            }
            throw new DatabaseSqlExecuteException(error, e);
        }
    }

    public static <D extends Domain, Q extends Query> Result<ListVO<D>> find(Mapper<D, Q> mapper, Q query) {
        return find(mapper, query, null);
    }

    public static <D extends Domain, Q extends Query> Result<ListVO<D>> find(Mapper<D, Q> mapper, Q query, ErrorInfo error) {
        if (mapper == null) {
            throw new ParamterInvalidException(new ErrorInfo(10003, "MAPPER_NULL_FIND", "mapper is null"));
        }
        if (query == null) {
            throw new ParamterInvalidException(new ErrorInfo(10004, "QUERY_NULL_FIND", "query is null"));
        }
        try {
            if (query.isPageEnable()) {
                long count = mapper.count(query);
                if (count < 1) {
                    return Result.success(ListVO.list(Collections.emptyList(), 0));
                }
                query.putTotalCount(count);
            }
            return Result.success(ListVO.list(mapper.find(query), query.getTotalCount(), query.getCurrentPage(), query.getPageSize()));
        } catch (Throwable e) {
            if (error == null) {
                error = new ErrorInfo(10005, "SQL_ERROR_FIND", "sql failed execute");
            }
            throw new DatabaseSqlExecuteException(error, e);
        }
    }

    public static <D extends Domain, Q extends Query> Result<D> get(Mapper<D, Q> mapper, Q query) {
        return get(mapper, query, null);
    }

    public static <D extends Domain, Q extends Query> Result<D> get(Mapper<D, Q> mapper, Q query, ErrorInfo error) {
        if (mapper == null) {
            throw new ParamterInvalidException(new ErrorInfo(10006, "MAPPER_NULL_GET", "mapper is null"));
        }
        if (query == null) {
            throw new ParamterInvalidException(new ErrorInfo(10007, "QUERY_NULL_GET", "query is null"));
        }
        try {
            return Result.success(mapper.get(query), error);
        } catch (Throwable e) {
            if (error == null) {
                error = new ErrorInfo(10008, "SQL_ERROR_GET", "sql failed execute");
            }
            throw new DatabaseSqlExecuteException(error, e);
        }
    }

    public static <D extends Domain, Q extends Query> Result<D> insert(Mapper<D, Q> mapper, D domain) {
        return insert(mapper, domain, null);
    }

    public static <D extends Domain, Q extends Query> Result<D> insert(Mapper<D, Q> mapper, D domain, ErrorInfo error) {
        if (mapper == null) {
            throw new ParamterInvalidException(new ErrorInfo(10009, "MAPPER_NULL_INSERT", "mapper is null"));
        }
        if (domain == null) {
            throw new ParamterInvalidException(new ErrorInfo(10010, "ENTITY_NULL_INSERT", "entity is null"));
        }
        try {
            mapper.insert(domain);
            return Result.success(domain);
        } catch (Throwable e) {
            if (error == null) {
                error = new ErrorInfo(10011, "SQL_ERROR_INSERT", "sql failed execute");
            }
            throw new DatabaseSqlExecuteException(error, e);
        }
    }

    public static <D extends Domain, Q extends Query> Result<Long> batchInsert(Mapper<D, Q> mapper, List<D> entities) {
        return batchInsert(mapper, entities, null);
    }

    public static <D extends Domain, Q extends Query> Result<Long> batchInsert(Mapper<D, Q> mapper, List<D> entities, ErrorInfo error) {
        if (mapper == null) {
            throw new ParamterInvalidException(new ErrorInfo(10012, "MAPPER_NULL_BATCH_INSERT", "mapper is null"));
        }
        if (entities == null) {
            throw new ParamterInvalidException(new ErrorInfo(10013, "ENTITY_NULL_BATCH_INSERT", "entities is null"));
        }
        try {
            return Result.success(mapper.batchInsert(entities));
        } catch (Throwable e) {
            if (error == null) {
                error = new ErrorInfo(10014, "SQL_ERROR_INSERT", "sql failed execute");
            }
            throw new DatabaseSqlExecuteException(error, e);
        }
    }

    public static <D extends Domain, Q extends Query> Result<D> update(Mapper<D, Q> mapper, D entity) {
        return update(mapper, entity, null);
    }

    public static <D extends Domain, Q extends Query> Result<D> update(Mapper<D, Q> mapper, D entity, ErrorInfo error) {
        if (mapper == null) {
            throw new ParamterInvalidException(new ErrorInfo(10015, "MAPPER_NULL_UPDATE", "mapper is null"));
        }
        if (entity == null) {
            throw new ParamterInvalidException(new ErrorInfo(10016, "ENTITY_NULL_UPDATE", "entity is null"));
        }
        try {
            long result = mapper.update(entity);
            if (result < 1) {
                if (error == null) {
                    return Result.success(entity);
                }
                return Result.success(error);
            }
            return Result.success(entity);
        } catch (Throwable e) {
            if (error == null) {
                error = new ErrorInfo(10017, "SQL_ERROR_UPDATE", "sql failed execute");
            }
            throw new DatabaseSqlExecuteException(error, e);
        }
    }

    public static <D extends Domain, Q extends Query> Result<D> delete(Mapper<D, Q> mapper, D entity) {
        return delete(mapper, entity, null);
    }

    public static <D extends Domain, Q extends Query> Result<D> delete(Mapper<D, Q> mapper, D entity, ErrorInfo error) {
        if (mapper == null) {
            throw new ParamterInvalidException(new ErrorInfo(10018, "MAPPER_NULL_DELETE", "mapper is null"));
        }
        if (entity == null) {
            throw new ParamterInvalidException(new ErrorInfo(10019, "ENTITY_NULL_DELETE", "entity is null"));
        }
        try {
            long result = mapper.delete(entity);
            if (result < 1) {
                if (error == null) {
                    return Result.success(entity);
                }
                return Result.success(error);
            }
            return Result.success(entity);
        } catch (Throwable e) {
            if (error == null) {
                error = new ErrorInfo(10020, "SQL_ERROR_DELETE", "sql failed execute");
            }
            throw new DatabaseSqlExecuteException(error, e);
        }
    }
}
