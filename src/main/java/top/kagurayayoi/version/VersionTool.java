package top.kagurayayoi.version;

public class VersionTool {

    public static boolean ControlVersion(int x, int y, int z, PublishType type, Version nowVersion) {
        return ControlVersion(new Version(x, y, z, type), nowVersion);
    }

    public static boolean ControlVersion(Version specifiedVersion, Version nowVersion) {
        // 现主版本号 高于 指定最低主版本号
        if (nowVersion.getX() > specifiedVersion.getX())
            return true;
        // 现主版本号 低于 指定主版本号
        else if (nowVersion.getX() < specifiedVersion.getX())
            return false;
        // 现主版本号 等于 指定最低主版本号
        else {
            // 现次版本号 高于 指定次版本号
            if (nowVersion.getY() > specifiedVersion.getY())
                return true;
            // 现次版本号 低于 指定次版本号
            else if (nowVersion.getY() < specifiedVersion.getY())
                return false;
            // 现次版本号 等于 指定次版本号
            else {
                // 现修订版本号 高于 指定修订版本号
                if (nowVersion.getZ() > specifiedVersion.getZ())
                    return true;
                // 现修订版本号 低于 指定修订版本号
                else if (nowVersion.getZ() < specifiedVersion.getZ())
                    return false;
                // 现修订版本号 等于 指定修订版本号
                else {
                    // 现发布类型 等于 指定发布类型
                    if (nowVersion.getType() == specifiedVersion.getType())
                        return true;
                    // 现发布类型 高于 指定发布类型 ( RELEASE > SNAPSHOT )
                    else if (nowVersion.getType().ordinal() > specifiedVersion.getType().ordinal())
                        return true;
                    // 现发布类型 低于 指定发布类型 ( SNAPSHOT > RELEASE )
                    else
                        return false;
                }
            }
        }
    }

}
