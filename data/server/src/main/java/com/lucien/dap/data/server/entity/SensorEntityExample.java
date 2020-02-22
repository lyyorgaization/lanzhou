package com.lucien.dap.data.server.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SensorEntityExample implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sensor
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sensor
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sensor
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sensor
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sensor
     *
     * @mbg.generated
     */
    protected Integer offset;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sensor
     *
     * @mbg.generated
     */
    protected Integer limit;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensor
     *
     * @mbg.generated
     */
    public SensorEntityExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensor
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensor
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensor
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensor
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensor
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensor
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensor
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensor
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensor
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensor
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensor
     *
     * @mbg.generated
     */
    public void setOffset(Integer offset) {
        this.offset=offset;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensor
     *
     * @mbg.generated
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensor
     *
     * @mbg.generated
     */
    public void setLimit(Integer limit) {
        this.limit=limit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensor
     *
     * @mbg.generated
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sensor
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria implements Serializable {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andSizeIsNull() {
            addCriterion("size is null");
            return (Criteria) this;
        }

        public Criteria andSizeIsNotNull() {
            addCriterion("size is not null");
            return (Criteria) this;
        }

        public Criteria andSizeEqualTo(String value) {
            addCriterion("size =", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotEqualTo(String value) {
            addCriterion("size <>", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThan(String value) {
            addCriterion("size >", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThanOrEqualTo(String value) {
            addCriterion("size >=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThan(String value) {
            addCriterion("size <", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThanOrEqualTo(String value) {
            addCriterion("size <=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLike(String value) {
            addCriterion("size like", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotLike(String value) {
            addCriterion("size not like", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeIn(List<String> values) {
            addCriterion("size in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotIn(List<String> values) {
            addCriterion("size not in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeBetween(String value1, String value2) {
            addCriterion("size between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotBetween(String value1, String value2) {
            addCriterion("size not between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andTemperatureIsNull() {
            addCriterion("temperature is null");
            return (Criteria) this;
        }

        public Criteria andTemperatureIsNotNull() {
            addCriterion("temperature is not null");
            return (Criteria) this;
        }

        public Criteria andTemperatureEqualTo(String value) {
            addCriterion("temperature =", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureNotEqualTo(String value) {
            addCriterion("temperature <>", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureGreaterThan(String value) {
            addCriterion("temperature >", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureGreaterThanOrEqualTo(String value) {
            addCriterion("temperature >=", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureLessThan(String value) {
            addCriterion("temperature <", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureLessThanOrEqualTo(String value) {
            addCriterion("temperature <=", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureLike(String value) {
            addCriterion("temperature like", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureNotLike(String value) {
            addCriterion("temperature not like", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureIn(List<String> values) {
            addCriterion("temperature in", values, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureNotIn(List<String> values) {
            addCriterion("temperature not in", values, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureBetween(String value1, String value2) {
            addCriterion("temperature between", value1, value2, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureNotBetween(String value1, String value2) {
            addCriterion("temperature not between", value1, value2, "temperature");
            return (Criteria) this;
        }

        public Criteria andProtectedLevelIsNull() {
            addCriterion("protected_level is null");
            return (Criteria) this;
        }

        public Criteria andProtectedLevelIsNotNull() {
            addCriterion("protected_level is not null");
            return (Criteria) this;
        }

        public Criteria andProtectedLevelEqualTo(String value) {
            addCriterion("protected_level =", value, "protectedLevel");
            return (Criteria) this;
        }

        public Criteria andProtectedLevelNotEqualTo(String value) {
            addCriterion("protected_level <>", value, "protectedLevel");
            return (Criteria) this;
        }

        public Criteria andProtectedLevelGreaterThan(String value) {
            addCriterion("protected_level >", value, "protectedLevel");
            return (Criteria) this;
        }

        public Criteria andProtectedLevelGreaterThanOrEqualTo(String value) {
            addCriterion("protected_level >=", value, "protectedLevel");
            return (Criteria) this;
        }

        public Criteria andProtectedLevelLessThan(String value) {
            addCriterion("protected_level <", value, "protectedLevel");
            return (Criteria) this;
        }

        public Criteria andProtectedLevelLessThanOrEqualTo(String value) {
            addCriterion("protected_level <=", value, "protectedLevel");
            return (Criteria) this;
        }

        public Criteria andProtectedLevelLike(String value) {
            addCriterion("protected_level like", value, "protectedLevel");
            return (Criteria) this;
        }

        public Criteria andProtectedLevelNotLike(String value) {
            addCriterion("protected_level not like", value, "protectedLevel");
            return (Criteria) this;
        }

        public Criteria andProtectedLevelIn(List<String> values) {
            addCriterion("protected_level in", values, "protectedLevel");
            return (Criteria) this;
        }

        public Criteria andProtectedLevelNotIn(List<String> values) {
            addCriterion("protected_level not in", values, "protectedLevel");
            return (Criteria) this;
        }

        public Criteria andProtectedLevelBetween(String value1, String value2) {
            addCriterion("protected_level between", value1, value2, "protectedLevel");
            return (Criteria) this;
        }

        public Criteria andProtectedLevelNotBetween(String value1, String value2) {
            addCriterion("protected_level not between", value1, value2, "protectedLevel");
            return (Criteria) this;
        }

        public Criteria andPrecisionIsNull() {
            addCriterion("precision is null");
            return (Criteria) this;
        }

        public Criteria andPrecisionIsNotNull() {
            addCriterion("precision is not null");
            return (Criteria) this;
        }

        public Criteria andPrecisionEqualTo(String value) {
            addCriterion("precision =", value, "precision");
            return (Criteria) this;
        }

        public Criteria andPrecisionNotEqualTo(String value) {
            addCriterion("precision <>", value, "precision");
            return (Criteria) this;
        }

        public Criteria andPrecisionGreaterThan(String value) {
            addCriterion("precision >", value, "precision");
            return (Criteria) this;
        }

        public Criteria andPrecisionGreaterThanOrEqualTo(String value) {
            addCriterion("precision >=", value, "precision");
            return (Criteria) this;
        }

        public Criteria andPrecisionLessThan(String value) {
            addCriterion("precision <", value, "precision");
            return (Criteria) this;
        }

        public Criteria andPrecisionLessThanOrEqualTo(String value) {
            addCriterion("precision <=", value, "precision");
            return (Criteria) this;
        }

        public Criteria andPrecisionLike(String value) {
            addCriterion("precision like", value, "precision");
            return (Criteria) this;
        }

        public Criteria andPrecisionNotLike(String value) {
            addCriterion("precision not like", value, "precision");
            return (Criteria) this;
        }

        public Criteria andPrecisionIn(List<String> values) {
            addCriterion("precision in", values, "precision");
            return (Criteria) this;
        }

        public Criteria andPrecisionNotIn(List<String> values) {
            addCriterion("precision not in", values, "precision");
            return (Criteria) this;
        }

        public Criteria andPrecisionBetween(String value1, String value2) {
            addCriterion("precision between", value1, value2, "precision");
            return (Criteria) this;
        }

        public Criteria andPrecisionNotBetween(String value1, String value2) {
            addCriterion("precision not between", value1, value2, "precision");
            return (Criteria) this;
        }

        public Criteria andSurveyRangeIsNull() {
            addCriterion("survey_range is null");
            return (Criteria) this;
        }

        public Criteria andSurveyRangeIsNotNull() {
            addCriterion("survey_range is not null");
            return (Criteria) this;
        }

        public Criteria andSurveyRangeEqualTo(String value) {
            addCriterion("survey_range =", value, "surveyRange");
            return (Criteria) this;
        }

        public Criteria andSurveyRangeNotEqualTo(String value) {
            addCriterion("survey_range <>", value, "surveyRange");
            return (Criteria) this;
        }

        public Criteria andSurveyRangeGreaterThan(String value) {
            addCriterion("survey_range >", value, "surveyRange");
            return (Criteria) this;
        }

        public Criteria andSurveyRangeGreaterThanOrEqualTo(String value) {
            addCriterion("survey_range >=", value, "surveyRange");
            return (Criteria) this;
        }

        public Criteria andSurveyRangeLessThan(String value) {
            addCriterion("survey_range <", value, "surveyRange");
            return (Criteria) this;
        }

        public Criteria andSurveyRangeLessThanOrEqualTo(String value) {
            addCriterion("survey_range <=", value, "surveyRange");
            return (Criteria) this;
        }

        public Criteria andSurveyRangeLike(String value) {
            addCriterion("survey_range like", value, "surveyRange");
            return (Criteria) this;
        }

        public Criteria andSurveyRangeNotLike(String value) {
            addCriterion("survey_range not like", value, "surveyRange");
            return (Criteria) this;
        }

        public Criteria andSurveyRangeIn(List<String> values) {
            addCriterion("survey_range in", values, "surveyRange");
            return (Criteria) this;
        }

        public Criteria andSurveyRangeNotIn(List<String> values) {
            addCriterion("survey_range not in", values, "surveyRange");
            return (Criteria) this;
        }

        public Criteria andSurveyRangeBetween(String value1, String value2) {
            addCriterion("survey_range between", value1, value2, "surveyRange");
            return (Criteria) this;
        }

        public Criteria andSurveyRangeNotBetween(String value1, String value2) {
            addCriterion("survey_range not between", value1, value2, "surveyRange");
            return (Criteria) this;
        }

        public Criteria andInterfaceIsNull() {
            addCriterion("interface is null");
            return (Criteria) this;
        }

        public Criteria andInterfaceIsNotNull() {
            addCriterion("interface is not null");
            return (Criteria) this;
        }

        public Criteria andInterfaceEqualTo(String value) {
            addCriterion("interface =", value, "interface");
            return (Criteria) this;
        }

        public Criteria andInterfaceNotEqualTo(String value) {
            addCriterion("interface <>", value, "interface");
            return (Criteria) this;
        }

        public Criteria andInterfaceGreaterThan(String value) {
            addCriterion("interface >", value, "interface");
            return (Criteria) this;
        }

        public Criteria andInterfaceGreaterThanOrEqualTo(String value) {
            addCriterion("interface >=", value, "interface");
            return (Criteria) this;
        }

        public Criteria andInterfaceLessThan(String value) {
            addCriterion("interface <", value, "interface");
            return (Criteria) this;
        }

        public Criteria andInterfaceLessThanOrEqualTo(String value) {
            addCriterion("interface <=", value, "interface");
            return (Criteria) this;
        }

        public Criteria andInterfaceLike(String value) {
            addCriterion("interface like", value, "interface");
            return (Criteria) this;
        }

        public Criteria andInterfaceNotLike(String value) {
            addCriterion("interface not like", value, "interface");
            return (Criteria) this;
        }

        public Criteria andInterfaceIn(List<String> values) {
            addCriterion("interface in", values, "interface");
            return (Criteria) this;
        }

        public Criteria andInterfaceNotIn(List<String> values) {
            addCriterion("interface not in", values, "interface");
            return (Criteria) this;
        }

        public Criteria andInterfaceBetween(String value1, String value2) {
            addCriterion("interface between", value1, value2, "interface");
            return (Criteria) this;
        }

        public Criteria andInterfaceNotBetween(String value1, String value2) {
            addCriterion("interface not between", value1, value2, "interface");
            return (Criteria) this;
        }

        public Criteria andVoltageIsNull() {
            addCriterion("Voltage is null");
            return (Criteria) this;
        }

        public Criteria andVoltageIsNotNull() {
            addCriterion("Voltage is not null");
            return (Criteria) this;
        }

        public Criteria andVoltageEqualTo(String value) {
            addCriterion("Voltage =", value, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageNotEqualTo(String value) {
            addCriterion("Voltage <>", value, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageGreaterThan(String value) {
            addCriterion("Voltage >", value, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageGreaterThanOrEqualTo(String value) {
            addCriterion("Voltage >=", value, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageLessThan(String value) {
            addCriterion("Voltage <", value, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageLessThanOrEqualTo(String value) {
            addCriterion("Voltage <=", value, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageLike(String value) {
            addCriterion("Voltage like", value, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageNotLike(String value) {
            addCriterion("Voltage not like", value, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageIn(List<String> values) {
            addCriterion("Voltage in", values, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageNotIn(List<String> values) {
            addCriterion("Voltage not in", values, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageBetween(String value1, String value2) {
            addCriterion("Voltage between", value1, value2, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageNotBetween(String value1, String value2) {
            addCriterion("Voltage not between", value1, value2, "voltage");
            return (Criteria) this;
        }

        public Criteria andPowerIsNull() {
            addCriterion("power is null");
            return (Criteria) this;
        }

        public Criteria andPowerIsNotNull() {
            addCriterion("power is not null");
            return (Criteria) this;
        }

        public Criteria andPowerEqualTo(String value) {
            addCriterion("power =", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerNotEqualTo(String value) {
            addCriterion("power <>", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerGreaterThan(String value) {
            addCriterion("power >", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerGreaterThanOrEqualTo(String value) {
            addCriterion("power >=", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerLessThan(String value) {
            addCriterion("power <", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerLessThanOrEqualTo(String value) {
            addCriterion("power <=", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerLike(String value) {
            addCriterion("power like", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerNotLike(String value) {
            addCriterion("power not like", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerIn(List<String> values) {
            addCriterion("power in", values, "power");
            return (Criteria) this;
        }

        public Criteria andPowerNotIn(List<String> values) {
            addCriterion("power not in", values, "power");
            return (Criteria) this;
        }

        public Criteria andPowerBetween(String value1, String value2) {
            addCriterion("power between", value1, value2, "power");
            return (Criteria) this;
        }

        public Criteria andPowerNotBetween(String value1, String value2) {
            addCriterion("power not between", value1, value2, "power");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andRemarkLikeInsensitive(String value) {
            addCriterion("upper(remark) like", value.toUpperCase(), "remark");
            return (Criteria) this;
        }

        public Criteria andSizeLikeInsensitive(String value) {
            addCriterion("upper(size) like", value.toUpperCase(), "size");
            return (Criteria) this;
        }

        public Criteria andTemperatureLikeInsensitive(String value) {
            addCriterion("upper(temperature) like", value.toUpperCase(), "temperature");
            return (Criteria) this;
        }

        public Criteria andProtectedLevelLikeInsensitive(String value) {
            addCriterion("upper(protected_level) like", value.toUpperCase(), "protectedLevel");
            return (Criteria) this;
        }

        public Criteria andPrecisionLikeInsensitive(String value) {
            addCriterion("upper(precision) like", value.toUpperCase(), "precision");
            return (Criteria) this;
        }

        public Criteria andSurveyRangeLikeInsensitive(String value) {
            addCriterion("upper(survey_range) like", value.toUpperCase(), "surveyRange");
            return (Criteria) this;
        }

        public Criteria andInterfaceLikeInsensitive(String value) {
            addCriterion("upper(interface) like", value.toUpperCase(), "interface");
            return (Criteria) this;
        }

        public Criteria andVoltageLikeInsensitive(String value) {
            addCriterion("upper(Voltage) like", value.toUpperCase(), "voltage");
            return (Criteria) this;
        }

        public Criteria andPowerLikeInsensitive(String value) {
            addCriterion("upper(power) like", value.toUpperCase(), "power");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sensor
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria implements Serializable {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sensor
     *
     * @mbg.generated
     */
    public static class Criterion implements Serializable {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}