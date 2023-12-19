package errors;

/**
 *
 * @author sergio
 */
public enum ReplayError {
    FileAlreadyExists,
    DataNotFound,
    IOException,
    SecurityError,
    Other;

    public int getResponseCode() {
        switch (this) {
            case FileAlreadyExists -> {
                return 409;
            }
            case DataNotFound -> {
                return 400;
            }
            case SecurityError -> {
                return 403;
            }
            case IOException -> {
                return 500;
            }
            case Other -> {
                return 512;
            }
            default -> {
                return 513;
            }
        }
    }

}
