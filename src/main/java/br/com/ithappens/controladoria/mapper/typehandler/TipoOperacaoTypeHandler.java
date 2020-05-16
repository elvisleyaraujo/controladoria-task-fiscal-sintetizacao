package br.com.ithappens.controladoria.mapper.typehandler;

import br.com.ithappens.controladoria.model.enums.TipoOperacao;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TipoOperacaoTypeHandler extends BaseTypeHandler<TipoOperacao> {

  public TipoOperacaoTypeHandler() {

  }

  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i, TipoOperacao tipoOperacao, JdbcType jdbcType) throws SQLException {
    preparedStatement.setInt(i, tipoOperacao.getId());
  }

  @Override
  public TipoOperacao getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
    TipoOperacao tipoOperacao = TipoOperacao.valueOfId(resultSet.getInt(columnName));
    return resultSet.wasNull() ? null : tipoOperacao;
  }

  @Override
  public TipoOperacao getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
    TipoOperacao tipoOperacao = TipoOperacao.valueOfId(resultSet.getInt(columnIndex));
    return resultSet.wasNull() ? null : tipoOperacao;
  }

  @Override
  public TipoOperacao getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
    TipoOperacao tipoOperacao = TipoOperacao.valueOfId(callableStatement.getInt(columnIndex));
    return callableStatement.wasNull() ? null : tipoOperacao;
  }
}
