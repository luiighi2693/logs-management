package com.demo.lab.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.demo.lab.domain.Log} entity.
 */
public class LogDTO implements Serializable {
    
    private String id;

    private String url;

    private String requestItem;

    private String responseItem;

    private Integer status;

    private String method;

    private String platform;

    private String requestTime;

    private String responseTime;

    private Integer duration;

    private Boolean logged;

    private String user;

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestItem() {
        return requestItem;
    }

    public void setRequestItem(String requestItem) {
        this.requestItem = requestItem;
    }

    public String getResponseItem() {
        return responseItem;
    }

    public void setResponseItem(String responseItem) {
        this.responseItem = responseItem;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Boolean isLogged() {
        return logged;
    }

    public void setLogged(Boolean logged) {
        this.logged = logged;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LogDTO logDTO = (LogDTO) o;
        if (logDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), logDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LogDTO{" +
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
