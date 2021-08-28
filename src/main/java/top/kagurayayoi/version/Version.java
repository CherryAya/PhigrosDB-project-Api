package top.kagurayayoi.version;

public class Version {

    private int X;  // 主版本号
    private int Y;  // 次版本号
    private int Z;  // 修订版本号
    private PublishType Type; // 发布类型

    public Version(int x, int y, int z, PublishType type) {
        X = x;
        Y = y;
        Z = z;
        Type = type;
    }

    public int getX() {
        return X;
    }

    public Version setX(int x) {
        X = x;
        return this;
    }

    public int getY() {
        return Y;
    }

    public Version setY(int y) {
        Y = y;
        return this;
    }

    public int getZ() {
        return Z;
    }

    public Version setZ(int z) {
        Z = z;
        return this;
    }

    public PublishType getType() {
        return Type;
    }

    public Version setType(PublishType type) {
        Type = type;
        return this;
    }

    @Override
    public String toString() {
        return getX() + "." + getY() + "." + getZ() + "-" + getType();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Version))
            return false;
        if (((Version) obj).X != this.X)
            return false;
        if (((Version) obj).Y != this.Y)
            return false;
        if (((Version) obj).Z != this.X)
            return false;
        if (((Version) obj).Type != this.Type)
            return false;
        return true;
    }
}

