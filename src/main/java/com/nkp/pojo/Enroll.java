package com.nkp.pojo;

import java.util.Date;

public class Enroll {

    private Work work;

    public void setWork(Work work) {
        this.work = work;
    }

    public Work getWork() {
        return work;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column enroll.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column enroll.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column enroll.phone
     *
     * @mbggenerated
     */
    private String phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column enroll.idCard
     *
     * @mbggenerated
     */
    private String idcard;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column enroll.purpose
     *
     * @mbggenerated
     */
    private Integer purpose;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column enroll.remarks
     *
     * @mbggenerated
     */
    private String remarks;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column enroll.createTime
     *
     * @mbggenerated
     */
    private Date createtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column enroll.id
     *
     * @return the value of enroll.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column enroll.id
     *
     * @param id the value for enroll.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column enroll.name
     *
     * @return the value of enroll.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column enroll.name
     *
     * @param name the value for enroll.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column enroll.phone
     *
     * @return the value of enroll.phone
     *
     * @mbggenerated
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column enroll.phone
     *
     * @param phone the value for enroll.phone
     *
     * @mbggenerated
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column enroll.idCard
     *
     * @return the value of enroll.idCard
     *
     * @mbggenerated
     */
    public String getIdcard() {
        return idcard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column enroll.idCard
     *
     * @param idcard the value for enroll.idCard
     *
     * @mbggenerated
     */
    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column enroll.purpose
     *
     * @return the value of enroll.purpose
     *
     * @mbggenerated
     */
    public Integer getPurpose() {
        return purpose;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column enroll.purpose
     *
     * @param purpose the value for enroll.purpose
     *
     * @mbggenerated
     */
    public void setPurpose(Integer purpose) {
        this.purpose = purpose;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column enroll.remarks
     *
     * @return the value of enroll.remarks
     *
     * @mbggenerated
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column enroll.remarks
     *
     * @param remarks the value for enroll.remarks
     *
     * @mbggenerated
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column enroll.createTime
     *
     * @return the value of enroll.createTime
     *
     * @mbggenerated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column enroll.createTime
     *
     * @param createtime the value for enroll.createTime
     *
     * @mbggenerated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}