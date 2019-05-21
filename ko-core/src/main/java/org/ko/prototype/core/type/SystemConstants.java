package org.ko.prototype.core.type;

/**
 * 系统常量类
 */
public final class SystemConstants {

    /**
     * 删除状态
     */
    public static final class AvailableStatus {
        /**
         * 删除
         */
        public static final Short Deleted = 0;

        //有效
        public static final Short Available = 1;

        private AvailableStatus () {}
    }

    private SystemConstants () {}

}
