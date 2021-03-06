package com.nkp.pojo;

import java.util.Date;

public class Work {
    private City cityname;

    public void setCityname(City cityname) {
        this.cityname = cityname;
    }

    public City getCityname() {
        return cityname;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work.type
     *
     * @mbggenerated
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work.city
     *
     * @mbggenerated
     */
    private Integer city;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work.thumbnail
     *
     * @mbggenerated
     */
    private String thumbnail;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work.workType
     *
     * @mbggenerated
     */
    private String worktype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work.sex
     *
     * @mbggenerated
     */
    private Integer sex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work.age
     *
     * @mbggenerated
     */
    private String age;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work.number
     *
     * @mbggenerated
     */
    private Integer number;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work.wages
     *
     * @mbggenerated
     */
    private String wages;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work.jtf
     *
     * @mbggenerated
     */
    private String jtf;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work.tjf
     *
     * @mbggenerated
     */
    private String tjf;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work.shf
     *
     * @mbggenerated
     */
    private String shf;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work.recommend
     *
     * @mbggenerated
     */
    private String recommend;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work.ennum
     *
     * @mbggenerated
     */
    private Integer ennum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work.createTime
     *
     * @mbggenerated
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work.rank
     *
     * @mbggenerated
     */
    private Integer rank;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work.id
     *
     * @return the value of work.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work.id
     *
     * @param id the value for work.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work.type
     *
     * @return the value of work.type
     *
     * @mbggenerated
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work.type
     *
     * @param type the value for work.type
     *
     * @mbggenerated
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work.city
     *
     * @return the value of work.city
     *
     * @mbggenerated
     */
    public Integer getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work.city
     *
     * @param city the value for work.city
     *
     * @mbggenerated
     */
    public void setCity(Integer city) {
        this.city = city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work.thumbnail
     *
     * @return the value of work.thumbnail
     *
     * @mbggenerated
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work.thumbnail
     *
     * @param thumbnail the value for work.thumbnail
     *
     * @mbggenerated
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail == null ? null : thumbnail.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work.name
     *
     * @return the value of work.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work.name
     *
     * @param name the value for work.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work.workType
     *
     * @return the value of work.workType
     *
     * @mbggenerated
     */
    public String getWorktype() {
        return worktype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work.workType
     *
     * @param worktype the value for work.workType
     *
     * @mbggenerated
     */
    public void setWorktype(String worktype) {
        this.worktype = worktype == null ? null : worktype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work.sex
     *
     * @return the value of work.sex
     *
     * @mbggenerated
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work.sex
     *
     * @param sex the value for work.sex
     *
     * @mbggenerated
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work.age
     *
     * @return the value of work.age
     *
     * @mbggenerated
     */
    public String getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work.age
     *
     * @param age the value for work.age
     *
     * @mbggenerated
     */
    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work.number
     *
     * @return the value of work.number
     *
     * @mbggenerated
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work.number
     *
     * @param number the value for work.number
     *
     * @mbggenerated
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work.wages
     *
     * @return the value of work.wages
     *
     * @mbggenerated
     */
    public String getWages() {
        return wages;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work.wages
     *
     * @param wages the value for work.wages
     *
     * @mbggenerated
     */
    public void setWages(String wages) {
        this.wages = wages == null ? null : wages.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work.jtf
     *
     * @return the value of work.jtf
     *
     * @mbggenerated
     */
    public String getJtf() {
        return jtf;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work.jtf
     *
     * @param jtf the value for work.jtf
     *
     * @mbggenerated
     */
    public void setJtf(String jtf) {
        this.jtf = jtf == null ? null : jtf.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work.tjf
     *
     * @return the value of work.tjf
     *
     * @mbggenerated
     */
    public String getTjf() {
        return tjf;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work.tjf
     *
     * @param tjf the value for work.tjf
     *
     * @mbggenerated
     */
    public void setTjf(String tjf) {
        this.tjf = tjf == null ? null : tjf.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work.shf
     *
     * @return the value of work.shf
     *
     * @mbggenerated
     */
    public String getShf() {
        return shf;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work.shf
     *
     * @param shf the value for work.shf
     *
     * @mbggenerated
     */
    public void setShf(String shf) {
        this.shf = shf == null ? null : shf.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work.recommend
     *
     * @return the value of work.recommend
     *
     * @mbggenerated
     */
    public String getRecommend() {
        return recommend;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work.recommend
     *
     * @param recommend the value for work.recommend
     *
     * @mbggenerated
     */
    public void setRecommend(String recommend) {
        this.recommend = recommend == null ? null : recommend.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work.ennum
     *
     * @return the value of work.ennum
     *
     * @mbggenerated
     */
    public Integer getEnnum() {
        return ennum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work.ennum
     *
     * @param ennum the value for work.ennum
     *
     * @mbggenerated
     */
    public void setEnnum(Integer ennum) {
        this.ennum = ennum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work.createTime
     *
     * @return the value of work.createTime
     *
     * @mbggenerated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work.createTime
     *
     * @param createtime the value for work.createTime
     *
     * @mbggenerated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work.rank
     *
     * @return the value of work.rank
     *
     * @mbggenerated
     */
    public Integer getRank() {
        return rank;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work.rank
     *
     * @param rank the value for work.rank
     *
     * @mbggenerated
     */
    public void setRank(Integer rank) {
        this.rank = rank;
    }
}