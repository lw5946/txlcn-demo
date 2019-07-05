package org.txlcn.demo.common.enume;

/**
 * 抛异常枚举
 *
 * @author LiuWang
 * @date 2019/6/20
 */
public enum ExFlagEnum {
    DUBBO_B("1", ""),
    DUBBO_C_BEFORE("2", ""),
    DUBBO_C_AFTER("3", ""),
    SERVICE_A("4", ""),
    SERVICE_B("5", ""),
    SERVICE_C_BEFORE("6", ""),
    SERVICE_C_AFTER("7", "")
    ;
    private String flag;
    private String desc;

    ExFlagEnum(String flag, String desc) {
        this.flag = flag;
        this.desc = desc;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
