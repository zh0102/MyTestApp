package com.zh.ktapp.bean

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore

@Entity(tableName = "CustomTitle")
class CustomTitleBean {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Long = 0

    @ColumnInfo(name = "num")
    var num //序号
            = 0

    @ColumnInfo(name = "num_type")
    var numType //机器类型
            = 0

    @ColumnInfo(name = "default_name")
    var defaultName //原始名称
            : String? = null

    @ColumnInfo(name = "custom_name")
    var customName //自定义名称
            : String? = null

    constructor() {}//构造方法

    @Ignore
    constructor(num: Int, numType: Int, defaultName: String?, customName: String?) {
        this.num = num
        this.numType = numType
        this.defaultName = defaultName
        this.customName = customName
    }

    override fun toString(): String {
        return "CustomTitleBean{" +
                "id=" + id +
                ", num=" + num +
                ", type='" + numType + '\'' +
                ", defaultName='" + defaultName + '\'' +
                ", customName='" + customName + '\'' +
                '}'
    }
}