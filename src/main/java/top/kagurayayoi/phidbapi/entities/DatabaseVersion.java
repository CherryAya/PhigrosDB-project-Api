package top.kagurayayoi.phidbapi.entities;

public class DatabaseVersion {

    private int X;  // 主版本号
    private int Y;  // 次版本号
    private int Z;  // 修订版本号

    public DatabaseVersion(int x, int y, int z) {
        X = x;
        Y = y;
        Z = z;
    }

    public int getX() {
        return X;
    }

    public DatabaseVersion setX(int x) {
        X = x;
        return this;
    }

    public int getY() {
        return Y;
    }

    public DatabaseVersion setY(int y) {
        Y = y;
        return this;
    }

    public int getZ() {
        return Z;
    }

    public DatabaseVersion setZ(int z) {
        Z = z;
        return this;
    }

    @Override
    public String toString() {
        return getX() + "." + getY() + "." + getZ();
    }
}
