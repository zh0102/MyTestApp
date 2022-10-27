package com.zh.ktapp.bean.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zh.ktapp.bean.Student

/**
 * @ClassName: StudentDao
 * @Description: TODO
 * @Author: ZHW
 * @Date: 2022/10/25 下午3:36
 */
@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(element: Student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(element: MutableList<Student>)

    @Query("select * from Student")
    fun getAllStudents():MutableList<Student>

    @Query("select * from Student where studentID = :studentID")
    fun getStudnet(studentID:Int):Student

    @Query("select * from Student order by studentID desc ")
    fun getAllByDateDesc():MutableList<Student>

    @Query("delete from Student")
    fun deleteAll()

    @Delete
    fun delete(element: Student)
}