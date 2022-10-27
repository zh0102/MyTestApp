package com.zh.ktapp.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * @ClassName: Student
 * @Description: TODO
 * @Author: ZHW
 * @Date: 2022/10/25 下午3:34
 */
@Entity(tableName = "Student")
data class Student (
    @PrimaryKey(autoGenerate = true)
    var studentID: Int?,

    @ColumnInfo(name = "s_name")
    var studentName: String?,
    @ColumnInfo(name = "s_type")
    var studentType: String?
)
