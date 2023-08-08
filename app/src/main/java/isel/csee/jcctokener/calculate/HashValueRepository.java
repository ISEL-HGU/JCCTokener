package isel.csee.jcctokener.calculate;

public class HashValueRepository {
    private String kToken;
    private String hashValue;
    private String filePath;

    public String getkToken() {
        return kToken;
    }

    public void setkToken(String kToken) {
        this.kToken = kToken;
    }

    public String getHashValue() {
        return hashValue;
    }

    public void setHashValue(String hashValue) {
        this.hashValue = hashValue;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public HashValueRepository(String kToken, String hashValue) {
        this.kToken = kToken;
        this.hashValue = hashValue;
    }

    public HashValueRepository(String kToken, String hashValue, String filePath) {
        this.kToken = kToken;
        this.hashValue = hashValue;
        this.filePath = filePath;
    }
}
