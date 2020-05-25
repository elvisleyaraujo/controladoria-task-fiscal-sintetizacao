package br.com.ithappens.controladoria.mapper.typehandler;

import br.com.ithappens.controladoria.model.enums.StatusValidacao;
import br.com.ithappens.controladoria.model.enums.TipoOperacao;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusValidacaoTypeHandler extends BaseTypeHandler<StatusValidacao> {

    public StatusValidacaoTypeHandler() {

    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, StatusValidacao statusValidacao, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, statusValidacao.getId());
    }

    @Override
    public StatusValidacao getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        StatusValidacao statusValidacao = StatusValidacao.valueOfId(resultSet.getInt(columnName));
        return resultSet.wasNull() ? null : statusValidacao;
    }

    @Override
    public StatusValidacao getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        StatusValidacao statusValidacao = StatusValidacao.valueOfId(resultSet.getInt(columnIndex));
        return resultSet.wasNull() ? null : statusValidacao;
    }

    @Override
    public StatusValidacao getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        StatusValidacao statusValidacao = StatusValidacao.valueOfId(callableStatement.getInt(columnIndex));
        return callableStatement.wasNull() ? null : statusValidacao;
    }
    
}
