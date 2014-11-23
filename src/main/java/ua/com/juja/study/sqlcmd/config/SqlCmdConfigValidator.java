package ua.com.juja.study.sqlcmd.config;

/**
 * Created by VICTOR on 23.11.2014.
 */
public class SqlCmdConfigValidator {
    public static void validateCmdOption(SqlCmdConfig config) throws ValidationException {
        StringBuilder strError = new StringBuilder();
        boolean error = false;

        if ((config.getUserName() == null) || config.getUserName().isEmpty()) {
            error = true;
            strError.append("value arg user name is incorrect;");
        }

        if (config.getPassword() == null || config.getPassword().isEmpty()) {
            error = true;
            strError.append("value arg user password is incorrect;");
        }

        if (config.getDbUrl() == null || config.getDbUrl().isEmpty()) {
            error = true;
            strError.append("value arg db url is incorrect;");
        }

        if (config.getDriverName() == null || config.getDriverName().isEmpty()) {
            error = true;
            strError.append("value arg driver name is incorrect;");
        }

        if (error) {
            throw new ValidationException(strError.toString());
        }

    }
}
