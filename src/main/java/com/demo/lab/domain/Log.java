package com.demo.lab.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Log.
 */
@Document(collection = "log")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("url")
    private String url;

    @Field("request_item")
    private String requestItem;

    @Field("response_item")
    private String responseItem;

    @Field("status")
    private Integer status;

    @Field("method")
    private String method;

    @Field("platform")
    private String platform;

    @Field("request_time")
    private String requestTime;

    @Field("response_time")
    private String responseTime;

    @Field("duration")
    private Integer duration;

    @Field("logged")
    private Boolean logged;

    @Field("user")
    private String user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public Log url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestItem() {
        return requestItem;
    }

    public Log requestItem(String requestItem) {
        this.requestItem = requestItem;
        return this;
    }

    public void setRequestItem(String requestItem) {
        this.requestItem = requestItem;
    }

    public String getResponseItem() {
        return responseItem;
    }

    public Log responseItem(String responseItem) {
        this.responseItem = responseItem;
        return this;
    }

    public void setResponseItem(String responseItem) {
        this.responseItem = responseItem;
    }

    public Integer getStatus() {
        return status;
    }

    public Log status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMethod() {
        return method;
    }

    public Log method(String method) {
        this.method = method;
        return this;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPlatform() {
        return platform;
    }

    public Log platform(String platform) {
        this.platform = platform;
        return this;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public Log requestTime(String requestTime) {
        this.requestTime = requestTime;
        return this;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public Log responseTime(String responseTime) {
        this.responseTime = responseTime;
        return this;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public Log duration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Boolean isLogged() {
        return logged;
    }

    public Log logged(Boolean logged) {
        this.logged = logged;
        return this;
    }

    public void setLogged(Boolean logged) {
        this.logged = logged;
    }

    public String getUser() {
        return user;
    }

    public Log user(String user) {
        this.user = user;
        return this;
    }

    public void setUser(String user) {
        this.user = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Log)) {
            return false;
        }
        return id != null && id.equals(((Log) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Log{" +
            "id=" + getId() +
            ", url='" + getUrl() + "'" +
            ", requestItem='" + getRequestItem() + "'" +
            ", responseItem='" + getResponseItem() + "'" +
            ", status=" + getStatus() +
            ", method='" + getMethod() + "'" +
            ", platform='" + getPlatform() + "'" +
            ", requestTime='" + getRequestTime() + "'" +
            ", responseTime='" + getResponseTime() + "'" +
            ", duration=" + getDuration() +
            ", logged='" + isLogged() + "'" +
            ", user='" + getUser() + "'" +
            "}";
    }
}
