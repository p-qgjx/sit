package com.nkp.dao;

import com.nkp.pojo.Enroll;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface EnrollMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enroll
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enroll
     *
     * @mbggenerated
     */
    int insert(Enroll record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enroll
     *
     * @mbggenerated
     */
    int insertSelective(Enroll record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enroll
     *
     * @mbggenerated
     */
    Enroll selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enroll
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Enroll record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enroll
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Enroll record);

    List<Enroll> selLike(@Param("name") String name, @Param("createTime") Date date);
    @Select("select count(1) from enroll where purpose=#{id}")
    Integer selnum(Integer id);
}