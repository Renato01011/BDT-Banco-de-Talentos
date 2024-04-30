package com.fractal.bancodetalentos.util;

import com.fractal.bancodetalentos.Config.DataSourceConfig;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class ValidationUtil {

    public static <T, R> void setNonNullValue(R value, Consumer<T> setter) {
        if (value != null) {
            setter.accept( (T) value);
        }
    }

    public static List<List<Object[]>> multiDataFromDb(String sp, boolean isParameter, DataSourceConfig dataSourceConfig, Object... params) {
        List<List<Object[]>> results = new ArrayList<>();
        try {
            try (Connection connection = dataSourceConfig.dataSource().getConnection()) {
                try (CallableStatement statement = connection.prepareCall(sp)) {
                    if (isParameter) {
                        for (int i = 0; i < params.length; i++) {
                            statement.setObject(i + 1, params[i]);
                        }
                    }
                    boolean isResultSet = statement.execute();
                    while (isResultSet) {
                        ResultSet resultSet = statement.getResultSet();
                        List<Object[]> singleResultSet = new ArrayList<>();
                        int columnCount = resultSet.getMetaData().getColumnCount();
                        while (resultSet.next()) {
                            Object[] row = new Object[columnCount];
                            for (int i = 0; i < columnCount; i++) {
                                row[i] = resultSet.getObject(i + 1);
                            }
                            singleResultSet.add(row);
                        }
                        results.add(singleResultSet);
                        resultSet.close();
                        isResultSet = statement.getMoreResults();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }


}
