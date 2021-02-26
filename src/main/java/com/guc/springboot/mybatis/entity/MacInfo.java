package com.guc.springboot.mybatis.entity;

/**
 * @Author: guc
 * @Description:
 * @Date: 2021/2/24 11:57
 * @Version: 1.0
 */
public class MacInfo {
    private String type;//login、logout
    private String secretKey;//共享密钥，用于API调用安全
    private String userName;//登录名，根据登录类型不同一般为手机号、用户名或MAC地址
    private String personalName;//用户姓名，仅在认证系统中录入了用户姓名时才会传递
    private String mobile;//用户手机号
    private String userIp;//用户上网 IP
    private String role;//用户角色，需要在认证服务器中为用户录入角色才会传递
    private String userMac;//用户MAC地址，以:分隔
    private String terminalPlatform;//上网终端类型  PC、iPhone、iPad、iPod、Android+Phone、others
    private String accessPointMac;//AP MAC，仅在AC将AP MAC传递给认证服务器情况下才能传递
    private String ssid;//SSID
    private String loginTime;//上线时间，仅在下线消息中包含，格式为yyyyMMddHHmmss
    private String flowInBytes;//登录上网流量（字节），仅在下线消息中包含 字节
    private String acMac;//上报的AC MAC地址

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPersonalName() {
        return personalName;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserMac() {
        return userMac;
    }

    public void setUserMac(String userMac) {
        this.userMac = userMac;
    }

    public String getTerminalPlatform() {
        return terminalPlatform;
    }

    public void setTerminalPlatform(String terminalPlatform) {
        this.terminalPlatform = terminalPlatform;
    }

    public String getAccessPointMac() {
        return accessPointMac;
    }

    public void setAccessPointMac(String accessPointMac) {
        this.accessPointMac = accessPointMac;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getFlowInBytes() {
        return flowInBytes;
    }

    public void setFlowInBytes(String flowInBytes) {
        this.flowInBytes = flowInBytes;
    }

    public String getAcMac() {
        return acMac;
    }

    public void setAcMac(String acMac) {
        this.acMac = acMac;
    }
}
