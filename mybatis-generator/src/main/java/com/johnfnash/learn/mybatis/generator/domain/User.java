package com.johnfnash.learn.mybatis.generator.domain;

public class User {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users.id
     *
     * @mbg.generated Sun Apr 14 00:35:08 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users.NAME
     *
     * @mbg.generated Sun Apr 14 00:35:08 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users.age
     *
     * @mbg.generated Sun Apr 14 00:35:08 CST 2019
     */
    private Integer age;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.id
     *
     * @return the value of users.id
     *
     * @mbg.generated Sun Apr 14 00:35:08 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.id
     *
     * @param id the value for users.id
     *
     * @mbg.generated Sun Apr 14 00:35:08 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.NAME
     *
     * @return the value of users.NAME
     *
     * @mbg.generated Sun Apr 14 00:35:08 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.NAME
     *
     * @param name the value for users.NAME
     *
     * @mbg.generated Sun Apr 14 00:35:08 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.age
     *
     * @return the value of users.age
     *
     * @mbg.generated Sun Apr 14 00:35:08 CST 2019
     */
    public Integer getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.age
     *
     * @param age the value for users.age
     *
     * @mbg.generated Sun Apr 14 00:35:08 CST 2019
     */
    public void setAge(Integer age) {
        this.age = age;
    }
}